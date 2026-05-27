import os, glob, json, time, requests
from requests.adapters import HTTPAdapter

ES = "http://localhost:9200"
BASE = "Assignment2/cs589assignment1/dataset/javascript"
RATING_DIR = os.path.join(BASE, "rating")
INDICES = [
    ("js_bm25",      "BM25"),
    ("js_tfidf",     "TF-IDF"),
    ("js_dirichlet", "Dirichlet"),
]
ANALYZER = "my_analyzer"
K = 10
CHUNK = 300
SLEEP_BETWEEN = 0.05

session = requests.Session()
session.headers.update({"Content-Type": "application/json", "Connection": "close"})
session.mount("http://", HTTPAdapter(pool_connections=2, pool_maxsize=2, max_retries=0))

def load_ratings(path, target_index):
    items = json.load(open(path, encoding="utf-8"))
    for it in items:
        it["_index"] = target_index
    return items

def fetch_title(index, qid1):
    r = session.get(f"{ES}/{index}/_doc/{qid1}")
    if r.status_code != 200:
        return None
    return r.json().get("_source", {}).get("title", "")

def build_req(qid1, qtitle, ratings):
    return {
        "id": str(qid1),
        "request": {
            "query": {
                "bool": {
                    "must_not": { "match": { "_id": qid1 } },
                    "should": [
                        { "match": { "title": { "query": qtitle, "boost": 3.0, "analyzer": ANALYZER } } },
                        { "match": { "body":  { "query": qtitle, "boost": 0.5, "analyzer": ANALYZER } } }
                    ]
                }
            }
        },
        "ratings": ratings
    }

def rank_eval_chunk(index, reqs):
    body = { "requests": reqs, "metric": { "dcg": { "k": K, "normalize": True } } }
    r = session.post(f"{ES}/{index}/_rank_eval", data=json.dumps(body))
    r.raise_for_status()
    return r.json()

def evaluate_index(index_name, pretty):
    rating_files = sorted(glob.glob(os.path.join(RATING_DIR, "ratings_*.json")))
    if not rating_files:
        print(f"[{pretty}] No ratings in {RATING_DIR}")
        return

    all_requests, skipped = [], 0
    for rp in rating_files:
        qid1 = os.path.splitext(os.path.basename(rp))[0].replace("ratings_", "")
        ratings = load_ratings(rp, index_name)
        qtitle = fetch_title(index_name, qid1)
        if not qtitle:
            skipped += 1
            continue
        all_requests.append(build_req(qid1, qtitle, ratings))

    if not all_requests:
        print(f"[{pretty}] No evaluable queries (skipped={skipped}).")
        return

    total, score_sum, zeros = 0, 0.0, 0
    for i in range(0, len(all_requests), CHUNK):
        chunk = all_requests[i:i+CHUNK]
        try:
            res = rank_eval_chunk(index_name, chunk)
        except requests.exceptions.RequestException as e:
            print(f"[{pretty}] _rank_eval chunk {i//CHUNK+1} failed: {e}")
            time.sleep(SLEEP_BETWEEN)
            continue

        details = res.get("details", {})
        for _, info in details.items():
            s = float(info.get("metric_score", 0.0))
            score_sum += s
            zeros += (1 if s == 0.0 else 0)
            total += 1

        time.sleep(SLEEP_BETWEEN)

    if total == 0:
        print(f"[{pretty}] No successful results.")
        return

    avg = score_sum / total
    print(f"[{pretty}] Index: {index_name}")
    print(f"[{pretty}] Queries evaluated: {total} | Skipped: {skipped}")
    print(f"[{pretty}] Zero metric_scores: {zeros} ({zeros/total:.1%})")
    print(f"[{pretty}] NDCG@{K}: {avg:.4f}\n")

def main():
    for idx, label in INDICES:
        # quick warning if index is empty
        try:
            c = session.get(f"{ES}/{idx}/_count").json().get("count", 0)
        except Exception:
            c = 0
        if c == 0:
            print(f"[{label}] WARNING: index '{idx}' has 0 docs. If needed, clone from a filled one.")
        evaluate_index(idx, label)

if __name__ == "__main__":
    main()
