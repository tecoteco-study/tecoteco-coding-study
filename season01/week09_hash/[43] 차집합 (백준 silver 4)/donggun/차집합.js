const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const A = input[1].split(" ").map(Number);
  const B = new Set(input[2].split(" ").map(Number));
  const diff = A.filter((a) => !B.has(a)).sort((a, b) => a - b);

  if (diff.length) console.log(diff.join(" "));
}

solution(input);