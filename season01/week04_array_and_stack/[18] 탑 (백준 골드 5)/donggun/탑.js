// https://www.acmicpc.net/problem/2493

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = Number(input[0]);
  const heights = input[1].split(" ").map(Number);
  const answer = new Array(N).fill(0);
  const stack = [];

  for (let i = N - 1; i >= 0; i--) {
    while (stack.length > 0 && heights[stack[stack.length - 1]] < heights[i]) {
      const index = stack.pop();

      answer[index] = i + 1;
    }

    stack.push(i);
  }

  return answer.join(" ");
}

console.log(solution(input));
