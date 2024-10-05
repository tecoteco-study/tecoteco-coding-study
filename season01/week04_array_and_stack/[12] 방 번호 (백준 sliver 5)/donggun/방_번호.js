// https://www.acmicpc.net/problem/1475

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = input[0];
  const numberCount = new Array(10).fill(0);

  // 6 & 9는 0.5로 계산해서 6에다가 담기
  for (const num of N) {
    if (num == 6 || num == 9) {
      numberCount[6] += 0.5;
    } else {
      numberCount[num]++;
    }
  }

  return Math.max(...numberCount.map(Math.ceil));
}

console.log(solution(input));
