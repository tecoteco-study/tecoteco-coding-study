const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const FRUIT_ENUM = {
  STRAWBERRY: 0,
  BANANA: 1,
  LIME: 2,
  PLUM: 3,
};

const fruitCount = [0, 0, 0, 0];

function solution(input) {
  const N = Number(input[0]);

  for (let i = 1; i <= N; i++) {
    const [fruit, count] = input[i].split(" ");

    fruitCount[FRUIT_ENUM[fruit]] += +count;
  }

  return fruitCount.some((count) => count == 5) ? "YES" : "NO";
}

console.log(solution(input));
