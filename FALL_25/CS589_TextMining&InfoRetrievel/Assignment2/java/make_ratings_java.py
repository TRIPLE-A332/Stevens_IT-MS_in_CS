import os, csv, json

BASE = r"Assignment2\cs589assignment1\dataset\java"
COSIDF = os.path.join(BASE, "java_cosidf.txt")
RATING_DIR = os.path.join(BASE, "rating")
INDEX_NAME = "java_bm25"  # <- evaluating using this index

os.makedirs(RATING_DIR, exist_ok=True)

def dump(qid1, items):
    outp = os.path.join(RATING_DIR, f"ratings_{qid1}.json")
    with open(outp, "w", encoding="utf-8") as f:
        json.dump(items, f, ensure_ascii=False, indent=2)
    print(f"Wrote {len(items)} ratings -> {outp}")

with open(COSIDF, "r", encoding="utf-8", errors="ignore") as f:
    reader = csv.reader(f, delimiter="\t")
    header = next(reader, None)  
    current_q, buf = None, []
    for row in reader:
        if not row or len(row) < 4:
            continue
        qid1, qid2, _score, label = row[0].strip(), row[1].strip(), row[2].strip(), row[3].strip()
        if current_q is None:
            current_q = qid1
        if qid1 != current_q:
            if buf:
                dump(current_q, buf[:30])  # 30 per spec
            current_q, buf = qid1, []
        buf.append({"_index": INDEX_NAME, "_id": qid2, "rating": int(float(label))})
    if current_q is not None and buf:
        dump(current_q, buf[:30])
