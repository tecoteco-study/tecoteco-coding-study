const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = Number(input[0]);
  const fullRounds = Array(N).fill(0);
  const requiredPizzas = input[1].split(" ").map(Number);
  const hungryMembers = Array.from({ length: N }, (_, idx) => idx);
  let currentRound = 0;

  while (hungryMembers.length) {
    const hungryMember = hungryMembers.shift();
    currentRound++;

    if (--requiredPizzas[hungryMember] > 0) {
      hungryMembers.push(hungryMember);
    } else {
      fullRounds[hungryMember] = currentRound;
    }
  }

  return fullRounds.join(" ");
}

console.log(solution(input));