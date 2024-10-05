// https://www.acmicpc.net/problem/11899

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const brackets = input[0];
  let openBracketCount = 0;
  let purchasedBracketCount = 0;

  for (const bracket of brackets) {
    if (bracket == "(") {
      openBracketCount++;
      continue;
    }

    if (openBracketCount) openBracketCount--;
    else purchasedBracketCount++;
  }

  return openBracketCount + purchasedBracketCount;
}

console.log(solution(input));
