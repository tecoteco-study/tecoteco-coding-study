const fs = require("fs");
const filePath = process.platform === "linux" ? "dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class UnionFind {
  constructor(n) {
    this.parent = Array(n)
      .fill()
      .map((_, i) => i);
    this.rank = Array(n).fill(0);
  }

  find(x) {
    if (this.parent[x] !== x) {
      this.parent[x] = this.find(this.parent[x]);
    }
    return this.parent[x];
  }

  union(x, y) {
    let rootX = this.find(x);
    let rootY = this.find(y);

    if (rootX === rootY) return false;

    if (this.rank[rootX] < this.rank[rootY]) {
      [rootX, rootY] = [rootY, rootX];
    }

    this.parent[rootY] = rootX;
    if (this.rank[rootX] === this.rank[rootY]) {
      this.rank[rootX]++;
    }

    return true;
  }
}

function getDistance(p1, p2) {
  const dx = p1[0] - p2[0];
  const dy = p1[1] - p2[1];
  return Math.sqrt(dx * dx + dy * dy);
}

function solution(input) {
  const n = Number(input.shift());
  const coordinates = input.map((c) => c.split(" ").map(Number));

  const edges = [];
  for (let i = 0; i < n; i++) {
    for (let j = i + 1; j < n; j++) {
      edges.push({
        from: i,
        to: j,
        weight: getDistance(coordinates[i], coordinates[j]),
      });
    }
  }

  edges.sort((a, b) => a.weight - b.weight);

  const uf = new UnionFind(n);
  let totalWeight = 0;
  let edgeCount = 0;

  for (const edge of edges) {
    if (uf.union(edge.from, edge.to)) {
      totalWeight += edge.weight;
      edgeCount++;
      if (edgeCount === n - 1) break;
    }
  }

  return totalWeight.toFixed(2);
}

console.log(solution(input));
