const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const T = Number(input[0]);
  let currentLine = 1;

  for (let i = 0; i < T; i++) {
    const [n, m] = input[currentLine].split(" ").map(Number);
    currentLine += (m + 1);
    console.log(n - 1);
  }
}

solution(input);