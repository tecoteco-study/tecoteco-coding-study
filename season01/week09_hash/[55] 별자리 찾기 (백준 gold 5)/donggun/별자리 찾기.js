const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const M = Number(input[0]);
  const diffCoordinates = [[0, 0]];

  // 별자리 좌표 차
  for (let i = 2; i <= M; i++) {
    const prevCoordinate = input[i - 1].split(" ").map(Number);
    const currCoordinate = input[i].split(" ").map(Number);
    diffCoordinates.push([
      currCoordinate[0] - prevCoordinate[0],
      currCoordinate[1] - prevCoordinate[1],
    ]);
  }

  const N = Number(input[M + 1]);
  const starCoordinates = new Set();

  for (let i = M + 2; i < M + N + 2; i++) {
    starCoordinates.add(input[i]);
  }

  for (const coordinate of starCoordinates) {
    let currentCoordinate = coordinate.split(" ").map(Number);
    let isSatisfied = true;

    for (const diffCoordinate of diffCoordinates) {
      currentCoordinate = [
        currentCoordinate[0] + diffCoordinate[0],
        currentCoordinate[1] + diffCoordinate[1],
      ];

      if (!starCoordinates.has(`${currentCoordinate[0]} ${currentCoordinate[1]}`)) {
        isSatisfied = false;
        break;
      }
    }

    if (isSatisfied) {
      const firstCoordinate = input[1].split(" ").map(Number);
      const targetCoordinate = coordinate.split(" ").map(Number);

      return `${targetCoordinate[0] - firstCoordinate[0]} ${targetCoordinate[1] - firstCoordinate[1]}`;
    }
  }
}

console.log(solution(input));