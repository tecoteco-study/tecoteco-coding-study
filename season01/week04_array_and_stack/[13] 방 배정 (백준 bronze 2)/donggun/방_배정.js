// https://www.acmicpc.net/problem/13300

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const students = new Array(13).fill(0);
  const [N, K] = input[0].split(" ").map(Number);

  for (let i = 1; i <= N; i++) {
    const [sex, grade] = input[i].split(" ").map(Number);

    students[sex * 6 + grade]++;
  }

  return students.reduce((prev, curr) => prev + Math.ceil(curr / K));
}

console.log(solution(input));
