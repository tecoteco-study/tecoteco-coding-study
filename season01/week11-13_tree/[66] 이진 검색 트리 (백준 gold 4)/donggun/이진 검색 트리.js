const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

class Node {
  constructor(value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }
}

class BinarySearchTree {
  constructor(value) {
    this.root = new Node(value);
  }

  insert(value) {
    let currentNode = this.root;

    while (this.isParentNode(currentNode, value) == false) {
      if (currentNode.value > value) currentNode = currentNode.left;
      else currentNode = currentNode.right;
    }

    if (currentNode.value > value) currentNode.left = new Node(value);
    else currentNode.right = new Node(value);
  }

  isParentNode(node, value) {
    if (node.value > value && node.left == null) return true;
    if (node.value < value && node.right == null) return true;

    return false;
  }

  postorder(node) {
    if (node == null) return;

    this.postorder(node.left);
    this.postorder(node.right);
    console.log(node.value)
  }

  printPostorder() {
    this.postorder(this.root);
  }
}

function solution(input) {
  const rootValue = Number(input.shift());
  const tree = new BinarySearchTree(rootValue);

  input.forEach((value) => tree.insert(Number(value)));
  tree.printPostorder();
}

solution(input);