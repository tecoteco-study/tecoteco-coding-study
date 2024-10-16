const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = Number(input.shift());
  const passwordMap = new Map();

  for (let i = 0; i < N; i++) {
    const password = input[i];
    const reversed = password.split("").reverse().join("");

    if (
      password == reversed ||
      passwordMap.has(password) ||
      passwordMap.has(reversed)
    ) {
      return `${password.length} ${password[Math.floor(password.length / 2)]}`;
    } else {
      passwordMap.set(password, true);
      passwordMap.set(reversed, true);
    }
  }
}

console.log(solution(input));
