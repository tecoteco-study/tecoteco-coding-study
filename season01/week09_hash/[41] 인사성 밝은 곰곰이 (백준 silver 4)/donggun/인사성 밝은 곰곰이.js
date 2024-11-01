const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = Number(input[0]);
  let gomgomUsers = new Set();
  let answer = 0;

  for (let i = 1; i <= N; i++) {
    const chat = input[i];

    if (chat == "ENTER") {
      answer += gomgomUsers.size;
      gomgomUsers.clear();
    } else {
      gomgomUsers.add(chat);
    }
  }

  return answer + gomgomUsers.size;
}

console.log(solution(input));