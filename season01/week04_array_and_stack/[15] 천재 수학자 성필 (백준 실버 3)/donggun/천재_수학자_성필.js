// https://www.acmicpc.net/problem/15815

const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const expression = input[0];
  const stack = [];

  for (const char of expression) {
    if (!isNaN(char)) {
      stack.push(Number(char));
      continue;
    }

    const curr = stack.pop();
    const prev = stack.pop();

    stack.push(calculate(prev, curr, char));
  }

  return stack.pop();
}

function calculate(prev, curr, operator) {
  if (operator == "+") return sum(prev, curr);
  else if (operator == "-") return substract(prev, curr);
  else if (operator == "*") return multiply(prev, curr);
  else if (operator == "/") return divide(prev, curr);
}

function sum(prev, curr) {
  return prev + curr;
}

function substract(prev, curr) {
  return prev - curr;
}

function multiply(prev, curr) {
  return prev * curr;
}

function divide(prev, curr) {
  return prev / curr;
}

console.log(solution(input));
