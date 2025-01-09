const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const map = new Map();
  const referenceNumbers = input[1].split(" ").map(Number);
  const givenNumbers = input[3].split(" ").map(Number);

  referenceNumbers.forEach((num) => map.set(num, true));

  return givenNumbers.map((num) => (map.has(num) ? 1 : 0));
}

console.log(solution(input).join("\n"));
