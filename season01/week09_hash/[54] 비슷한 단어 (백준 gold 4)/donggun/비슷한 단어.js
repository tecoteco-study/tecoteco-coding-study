const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const prefixMap = new Map();
  let maxCount = 0;
  let answer = [];

  for (let i = 1; i < input.length; i++) {
    for (let j = 0; j < input[i].length; j++) {
      const prefix = input[i].slice(0, j + 1);
      const index = prefixMap.get(prefix);
      const shouldUpdate = index && input[index] !== input[i] && (prefix.length > maxCount || (prefix.length == maxCount && answer[0] > index));

      if (shouldUpdate) {
        maxCount = prefix.length;
        answer = [index, i];
      }

      if (!index) prefixMap.set(prefix, i);
    }
  }

  return answer;
}

console.log(solution(input).map((idx) => input[idx]).join("\n"));