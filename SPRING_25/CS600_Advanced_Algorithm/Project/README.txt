
Search Engine Project

====================
PROJECT DESCRIPTION:
====================
This project implements a simplified search engine using Tries as outlined in Section 23.6.4.
It indexes a small set of HTML documents, excluding common stop words, and allows keyword-based search with basic ranking.

=====================
ALGORITHMS & APPROACH:
=====================
1. Tokenization and Stop Word Removal:
   - HTML pages are parsed using BeautifulSoup.
   - Text content is tokenized and cleaned.
   - Stop words (e.g., 'a', 'the', 'of') are removed.

2. Data Structures:
   - Trie (Prefix Tree): For indexing all valid words.
   - Dictionary: Maps words to document occurrence and frequency.
   - Set: Stores common stop words.

3. Indexing:
   - Words from all input HTML documents are inserted into the trie.
   - Each TrieNode keeps track of which documents the word appeared in and how many times.

4. Search:
   - Searches for a given word and retrieves documents it appears in.
   - Basic frequency-based ranking is applied to rank documents.

5. Input/Output:
   - Input: HTML documents with internal links.
   - Output: Ranked search results written to a file.

==============
RANKING METHOD:
==============
Documents are ranked based on the frequency of the keyword in each document.

==================
BOUNDARY TEST CASES:
==================
- Searching for a word that doesn't exist.
- Words with multiple document hits.
- Empty query string.
- Stop word queries (should return nothing).

==================
LIBRARIES USED:
==================
- BeautifulSoup (bs4): For HTML parsing.
- collections, re, os: Standard library utilities.
