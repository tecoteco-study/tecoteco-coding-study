const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = Number(input[0]);
  const cardMap = new Map();
  let maxCount = 0;
  let maxCountCard = 0;

  for (let i = 1; i <= N; i++) {
    const card = BigInt(input[i]);
    const count = cardMap.has(card) ? cardMap.get(card) + 1 : 1;

    if (count == maxCount && card < maxCountCard) {
      maxCountCard = card;
    } else if (count > maxCount) {
      maxCount = count;
      maxCountCard = card;
    }

    cardMap.set(card, count);
  }

  return maxCountCard.toString();
}

console.log(solution(input));
