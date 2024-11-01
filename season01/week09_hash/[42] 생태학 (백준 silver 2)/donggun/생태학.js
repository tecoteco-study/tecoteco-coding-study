const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const treeMap = new Map();
  input.sort().forEach((tree) => treeMap.set(tree, (treeMap.get(tree) || 0) + 1));

  return [...new Set(input)].map((tree) => `${tree} ${((treeMap.get(tree) / input.length) * 100).toFixed(4)}`);
}

console.log(solution(input).join("\n"));