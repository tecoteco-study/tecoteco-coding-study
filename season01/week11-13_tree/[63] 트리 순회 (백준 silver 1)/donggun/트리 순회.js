const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
const tree = {};

function solution(input) {
  input.shift();
  input.forEach((nodes) => {
    const [root, left, right] = nodes.split(" ");
    tree[root] = [left, right];
  });

  preorder("A");
  console.log();
  inorder("A");
  console.log();
  postorder("A");
}

function preorder(node) {
  if (node == ".") return;

  process.stdout.write(node);
  preorder(tree[node][0]);
  preorder(tree[node][1]);
}

function inorder(node) {
  if (node == ".") return;

  inorder(tree[node][0]);
  process.stdout.write(node);
  inorder(tree[node][1]);
}

function postorder(node) {
  if (node == ".") return;

  postorder(tree[node][0]);
  postorder(tree[node][1]);
  process.stdout.write(node);
}

solution(input);
