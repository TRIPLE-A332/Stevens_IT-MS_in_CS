import os, csv, json, sys

BASE = r"Assignment2\cs589assignment1\dataset"
TARGET_MAX_BYTES = 90 * 1024 * 1024  # keep batches under ~90MB

def open_new_batch(out_prefix, batch_idx):
    path = f"{out_prefix}{batch_idx:04d}.json"
    fout = open(path, "w", encoding="utf-8")
    return fout, path, 0, 0  # file handle, path, bytes, docs

def main():
    if len(sys.argv) != 3:
        print("Usage: python make_bulk_batches.py <lang> <target_index>")
        sys.exit(1)

    lang = sys.argv[1].strip().lower()        # 'python' | 'java' | 'javascript'
    index_name = sys.argv[2].strip()

    lang_dir = os.path.join(BASE, lang)
    src = os.path.join(lang_dir, f"{lang}_qid2all.txt")
    if not os.path.exists(src):
        print(f"Not found: {src}")
        sys.exit(2)

    out_prefix = os.path.join(lang_dir, f"{lang}_bulk_")
    os.makedirs(lang_dir, exist_ok=True)

    batch_idx = 1
    fout, current_path, current_bytes, doc_count = open_new_batch(out_prefix, batch_idx)
    total_docs = 0

    with open(src, "r", encoding="utf-8", errors="ignore") as fin:
        reader = csv.reader(fin, delimiter="\t")
        for row in reader:
            if not row:
                continue
            qid = row[0].strip()
            title = row[1].strip() if len(row) > 1 else ""
            body  = row[2].strip() if len(row) > 2 else ""

            action = { "index": { "_index": index_name, "_id": qid } }
            doc = { "title": title }
            if body:
                doc["body"] = body

            a = json.dumps(action, ensure_ascii=False) + "\n"
            d = json.dumps(doc,    ensure_ascii=False) + "\n"
            add_size = len(a.encode("utf-8")) + len(d.encode("utf-8"))

            # rotate batch if adding this doc would exceed target size
            if current_bytes > 0 and current_bytes + add_size > TARGET_MAX_BYTES:
                fout.close()
                print(f"Wrote {doc_count} docs -> {current_path} ({current_bytes/1024/1024:.2f} MB)")
                batch_idx += 1
                fout, current_path, current_bytes, doc_count = open_new_batch(out_prefix, batch_idx)

            fout.write(a); fout.write(d)
            current_bytes += add_size
            doc_count += 1
            total_docs += 1

    fout.close()
    print(f"Wrote {doc_count} docs -> {current_path} ({current_bytes/1024/1024:.2f} MB)")
    print(f"TOTAL docs prepared for {index_name}: {total_docs}")

if __name__ == "__main__":
    main()
