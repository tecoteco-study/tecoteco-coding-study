const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const [N, M] = input.shift().split(" ").map(Number);
  const words = input.filter((word) => word.length >= M);
  const wordCountMap = new Map();

  words.forEach((word) =>
    wordCountMap.set(word, (wordCountMap.get(word) || 0) + 1)
  );
  const uniqueWords = [...new Set(words)];

  uniqueWords.sort(
    (a, b) =>
      wordCountMap.get(b) - wordCountMap.get(a) ||
      b.length - a.length ||
      a.localeCompare(b)
  );

  return uniqueWords;
}

console.log(solution(input).join("\n"));