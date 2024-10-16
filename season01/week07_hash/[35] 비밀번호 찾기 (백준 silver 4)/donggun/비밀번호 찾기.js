const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const [N, M] = input[0].split(" ").map(Number);
  const passwordMap = new Map();
  const answer = [];

  for (let i = 1; i <= N; i++) {
    const [url, password] = input[i].split(" ");
    passwordMap.set(url, password);
  }

  for (let i = N + 1; i <= N + M; i++) {
    answer.push(passwordMap.get(input[i]));
  }

  return answer;
}

console.log(solution(input).join("\n"));
