const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const [nodeCount, leafCount] = input[0].split(" ").map(Number);

  const diffCount = nodeCount - leafCount;

  for (let i = 0; i < diffCount; i++) {
    console.log(i + " " + (i + 1));
  }

  for (let i = diffCount; i < nodeCount - 1; i++) {
    console.log(diffCount + " " + (i + 1));
  }
}

solution(input);