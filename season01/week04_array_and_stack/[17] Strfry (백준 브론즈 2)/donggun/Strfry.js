// https://www.acmicpc.net/problem/11328

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const count = input[0];

  for (let i = 1; i <= count; i++) {
    const [firstString, secondString] = input[i].split(" ");

    if (isStrfryable(firstString, secondString)) {
      console.log("Possible");
    } else {
      console.log("Impossible");
    }
  }
}

function isStrfryable(firstString, secondString) {
  if (firstString.length !== secondString.length) {
    return false;
  }

  const alphabetMap = new Array(26).fill(0);

  for (let i = 0; i < firstString.length; i++) {
    alphabetMap[firstString[i].charCodeAt() - 97]++;
    alphabetMap[secondString[i].charCodeAt() - 97]--;
  }

  return alphabetMap.every((val) => val == 0);
}

solution(input);
