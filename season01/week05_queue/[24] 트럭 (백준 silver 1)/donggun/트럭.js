// https://www.acmicpc.net/problem/13335

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution([info, truckWeights]) {
  const [_, bridgeLength, maxWeight] = info.split(" ").map(Number);
  const weights = truckWeights.split(" ").map(Number);

  let time = 0;
  let bridgeWeight = 0;
  const bridge = Array(bridgeLength).fill(0);

  while (weights.length > 0 || bridgeWeight > 0) {
    bridgeWeight -= bridge.shift();
    time++;

    if (weights.length == 0) continue;

    if (bridgeWeight + weights[0] <= maxWeight) {
      const truckWeight = weights.shift();
      bridge.push(truckWeight);
      bridgeWeight += truckWeight;
    } else {
      bridge.push(0);
    }
  }

  return time;
}

console.log(solution(input));
