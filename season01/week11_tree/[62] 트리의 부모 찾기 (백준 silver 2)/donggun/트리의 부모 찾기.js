const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

function solution(input) {
  const N = Number(input.shift());
  const adjacencyList = Array.from({ length: N + 1 }, () => []);
  const parent = new Array(N + 1);

  input.forEach((nodes) => {
    const [a, b] = nodes.split(" ").map(Number);
    adjacencyList[a].push(b);
    adjacencyList[b].push(a);
  });

  const queue = [1];

  while (queue.length) {
    const current = queue.shift();

    for (const next of adjacencyList[current]) {
      if (!parent[next]) {
        parent[next] = current;
        queue.push(next);
      }
    }
  }

  console.log(parent.slice(2).join("\n"));
}

solution(input);
