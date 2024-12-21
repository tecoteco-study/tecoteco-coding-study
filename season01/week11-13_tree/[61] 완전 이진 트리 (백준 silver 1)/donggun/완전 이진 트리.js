const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const answer = [];
  const treeHeight = Number(input[0]);
  const traversalResult = input[1].split(" ").map(Number);

  for (let level = 0; level < treeHeight; level++) {
    const nodes = [];
    const startingIdx = 2 ** (treeHeight - 1 - level) - 1;
    const nodeInterval = 2 ** (treeHeight - level);

    for (let j = 0; j < 2 ** level; j++) {
      nodes.push(traversalResult[startingIdx + j * nodeInterval]);
    }

    answer.push(nodes);
  }

  return answer;
}

solution(input).forEach((level) => console.log(level.join(" ")));
