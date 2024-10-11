const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class Queue {
  data = [];
  _front = 0;
  _back = 0;

  front() {
    if (this.empty()) return -1;

    return this.data[this._front];
  }

  back() {
    if (this.empty()) return -1;

    return this.data[this._back - 1];
  }

  empty() {
    return this._front == this._back;
  }

  push(x) {
    this.data.push(x);
    this._back++;
  }

  pop() {
    if (this.empty()) return -1;

    this._front++;
    return this.data[this._front - 1];
  }

  size() {
    return this._back - this._front;
  }
}

function solution(input) {
  const n = input[0];
  const queue = new Queue();
  const answer = [];

  for (let i = 1; i <= n; i++) {
    const command = input[i].split(" ");

    if (command[0] == "push") {
      queue.push(command[1]);
    } else if (command[0] == "pop") {
      answer.push(queue.pop(command[1]) + "");
    } else if (command[0] == "front") {
      answer.push(queue.front() + "");
    } else if (command[0] == "back") {
      answer.push(queue.back() + "");
    } else if (command[0] == "size") {
      answer.push(queue.size() + "");
    } else if (command[0] == "empty") {
      answer.push(queue.empty() ? "1" : "0");
    }
  }

  return answer.join("\n");
}

console.log(solution(input));
