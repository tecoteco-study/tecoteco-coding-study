// https://www.acmicpc.net/problem/2161

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim();

function solution(input) {
  const N = parseInt(input);
  const cards = Array.from({ length: N }, (_, idx) => N - idx);
  const deletedCards = [];

  while (cards.length) {
    deletedCards.push(cards.pop());

    if (cards.length) cards.unshift(cards.pop());
  }

  return deletedCards.join(" ");
}

console.log(solution(input));
