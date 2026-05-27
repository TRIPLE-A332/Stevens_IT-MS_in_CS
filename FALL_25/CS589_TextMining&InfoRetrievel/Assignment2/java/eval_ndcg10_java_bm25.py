import os, glob, json, time, requests

# ---- config---------------------------------
ES = "http://localhost:9200"
BASE = "Assignment2/cs589assignment1/dataset/java"
RATING_DIR = os.path.join(BASE, "rating")      # ratings_<qid1>.json
INDICES = [                                     # (index_name, pretty_label)
    ("java_bm25",      "BM25"),
    ("java_tfidf",     "TF-IDF"),
    ("java_dirichlet", "Dirichlet"),
]
ANALYZER = "my_analyzer"
K = 10
CHUNK = 300            # queries per _rank_eval request
SLEEP_BETWEEN = 0.05   # seconds between chunk calls
# ----------------------------------------------------------------------

session = requests.Session()
session.headers.update({"Content-Type": "application/json"})
from requests.adapters import HTTPAdapter
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

def rank_eval_chunk(index, requests_payload):
    body = {
        "requests": requests_payload,
        "metric": { "dcg": { "k": K, "normalize": True } }
    }
    r = session.post(f"{ES}/{index}/_rank_eval", data=json.dumps(body))
    r.raise_for_status()
    return r.json()

def evaluate_index(index_name, pretty):
    rating_files = sorted(glob.glob(os.path.join(RATING_DIR, "ratings_*.json")))
    if not rating_files:
        print(f"[{pretty}] No rating files found in: {RATING_DIR}")
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

    
    total = 0
    score_sum = 0.0
    zeros = 0
    for i in range(0, len(all_requests), CHUNK):
        chunk = all_requests[i:i+CHUNK]
        try:
            res = rank_eval_chunk(index_name, chunk)
        except requests.exceptions.RequestException as e:
            print(f"[{pretty}] _rank_eval chunk {i//CHUNK+1} failed: {e}")
            # skip this chunk but continue
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
        evaluate_index(idx, label)

if __name__ == "__main__":
    main()
