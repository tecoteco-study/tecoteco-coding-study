const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = Number(input[0]);
  const rainbowDancers = { ChongChong: true };

  for (let i = 1; i <= N; i++) {
    const [first, second] = input[i].split(" ");

    if (rainbowDancers[first] || rainbowDancers[second]) {
        rainbowDancers[first] = true;
        rainbowDancers[second] = true;
    }
  }

  return Object.keys(rainbowDancers).length
}

console.log(solution(input));
