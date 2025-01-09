const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const [N, M] = input.shift().split(" ").map(Number);
  const melodyMap = new Map();

  for (let i = 0; i < N; i++) {
    const [_, title, first, second, third] = input.shift().split(" ");
    const melody = first + second + third;

    if (melodyMap.has(melody)) melodyMap.set(melody, "?");
    else melodyMap.set(melody, title);
  }

  for (let i = 0; i < M; i++) {
    const melody = input.shift().replaceAll(" ", "");

    console.log(`${melodyMap.get(melody) ?? "!"}`);
  }
}

solution(input);
