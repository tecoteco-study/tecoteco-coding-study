// https://www.acmicpc.net/problem/1158

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim();

function solution(input) {
  const [N, K] = input.split(" ").map(Number);
  const people = Array.from({ length: N }, (_, idx) => idx + 1);
  const deletedPeople = [];
  let currentIdx = 0;

  while (people.length) {
    currentIdx = (currentIdx + K - 1) % people.length;

    deletedPeople.push(people.splice(currentIdx, 1));
  }

  return `<${deletedPeople.join(", ")}>`;
}

console.log(solution(input));
