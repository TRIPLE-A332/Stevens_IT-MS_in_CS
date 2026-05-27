import os, csv, json

BASE = "Assignment2/cs589assignment1/dataset/javascript"
COSIDF = os.path.join(BASE, "javascript_cosidf.txt")
RATING_DIR = os.path.join(BASE, "rating")

os.makedirs(RATING_DIR, exist_ok=True)

def dump(qid1, items):
    outp = os.path.join(RATING_DIR, f"ratings_{qid1}.json")
    with open(outp, "w", encoding="utf-8") as f:
        json.dump(items, f, ensure_ascii=False, indent=2)
    print(f"Wrote {len(items)} ratings -> {outp}")

with open(COSIDF, "r", encoding="utf-8", errors="ignore") as f:
    reader = csv.reader(f, delimiter="\t")
    header = next(reader, None)  # skip header
    current, buf = None, []
    for row in reader:
        if not row or len(row) < 4:
            continue
        qid1, qid2, _score, label = row[0].strip(), row[1].strip(), row[2].strip(), row[3].strip()
        if current is None:
            current = qid1
        if qid1 != current:
            if buf:
                dump(current, buf[:30])  # 30 per spec
            current, buf = qid1, []
        # placeholder _index is fine; eval script will overwrite _index per model
        buf.append({"_index": "js_bm25", "_id": qid2, "rating": int(float(label))})
    if current is not None and buf:
        dump(current, buf[:30])
