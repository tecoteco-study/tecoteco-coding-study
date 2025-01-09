const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class TrieNode {
  constructor(key) {
    this.key = key;
    this.children = {};
  }
}

class Trie {
  constructor() {
    this.root = new TrieNode(null);
  }

  insert(word) {
    let node = this.root;

    for (let i = 0; i < word.length; i++) {
      if (!node.children[word[i]]) {
        node.children[word[i]] = new TrieNode(word[i]);
      }

      node = node.children[word[i]];
    }
  }

  contains(word) {
    let node = this.root;

    for (const element of word) {
      if (node.children[element]) node = node.children[element];
      else return false;
    }

    return true;
  }
}

function solution(input) {
  const [N, M] = input.shift().split(" ").map(Number);
  const trie = new Trie();
  let answer = 0;

  for (let i = 0; i < N; i++) {
    trie.insert(input[i]);
  }

  for (let i = N; i < N + M; i++) {
    if (trie.contains(input[i])) answer++;
  }

  return answer;
}

console.log(solution(input));
