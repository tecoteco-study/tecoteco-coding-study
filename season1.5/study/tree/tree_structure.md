아래 예시는 **이진 트리**를 예로 들어서 설명합니다.  
트리 구성 방식을 크게 두 가지로 볼 수 있는데,
1) **배열(Array)** 기반의 방식
2) **노드(Node) 연결** 기반의 방식(Linked List와 유사)  
   으로 나눌 수 있습니다.

---

## 1. 배열 기반 트리 구현

### 1.1 특징
- **완전 이진 트리(Complete Binary Tree)** 형태일 때 인덱스 계산이 편리
- 노드 인덱스가 `i`일 때,
    - **왼쪽 자식** 인덱스: `2 * i + 1`
    - **오른쪽 자식** 인덱스: `2 * i + 2`
    - (루트 인덱스는 일반적으로 `0`)

### 1.2 시각화 예시

```plaintext
인덱스 0  |  인덱스 1  |  인덱스 2  |  인덱스 3  |  인덱스 4  |  인덱스 5  |  인덱스 6
     (A)  |       (B)  |       (C)  |       (D)  |       (E)  |       (F)  |       (G)

     A (0)
     /    \
   B(1)   C(2)
   / \    / \
 D(3) E(4)F(5)G(6)
```

- 루트 노드 A는 인덱스 `0`
- B는 A의 왼쪽 자식(인덱스 1), C는 A의 오른쪽 자식(인덱스 2)
- D = B의 왼쪽 자식(인덱스 3), E = B의 오른쪽 자식(인덱스 4), 등등

### 1.3 예시 코드 (Java)

```java
public class ArrayBinaryTree {
    private String[] tree;
    private int size;

    public ArrayBinaryTree(int capacity) {
        tree = new String[capacity];
        size = 0;
    }

    // 루트 삽입 (예제 단순화: 첫 번째 삽입은 루트로 지정)
    public void setRoot(String value) {
        tree[0] = value;
        size = Math.max(size, 1);
    }

    // 왼쪽 자식 삽입: parent의 인덱스 기반
    public void setLeftChild(int parentIndex, String value) {
        int leftIndex = 2 * parentIndex + 1;
        if (leftIndex < tree.length) {
            tree[leftIndex] = value;
            size = Math.max(size, leftIndex + 1);
        }
    }

    // 오른쪽 자식 삽입
    public void setRightChild(int parentIndex, String value) {
        int rightIndex = 2 * parentIndex + 2;
        if (rightIndex < tree.length) {
            tree[rightIndex] = value;
            size = Math.max(size, rightIndex + 1);
        }
    }

    // 간단한 출력 (배열 형태)
    public void printTreeArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayBinaryTree arrayTree = new ArrayBinaryTree(10);

        // 트리 구성 (예: A, B, C, D, E, F, G)
        arrayTree.setRoot("A");
        arrayTree.setLeftChild(0, "B");
        arrayTree.setRightChild(0, "C");
        arrayTree.setLeftChild(1, "D");
        arrayTree.setRightChild(1, "E");
        arrayTree.setLeftChild(2, "F");
        arrayTree.setRightChild(2, "G");

        arrayTree.printTreeArray();
        // 출력 예시: A B C D E F G
    }
}
```

#### 배열 기반 방식의 장점
- **인덱스 계산**으로 간단하게 접근 가능
- 부모 → 자식, 자식 → 부모 인덱스 참조가 쉬움

#### 배열 기반 방식의 단점
- **완전 이진 트리** 형태가 아닌 경우, 메모리 낭비가 발생
- 동적 크기 조정이 번거롭고, 트리가 한쪽으로 편향되면 비효율적

---

## 2. Linked List(노드 연결) 기반 트리 구현

### 2.1 특징
- 각 노드는 **값(value)**, **왼쪽 자식(left)**, **오른쪽 자식(right)** 포인터(참조)로 구성
- 노드를 객체로 생성하고, 연결을 통해 트리를 구성
- **모든 형태의 트리**(편향 트리, 완전 트리 등)에 두루 적합
- 동적으로 **노드 삽입/삭제**에 유연

### 2.2 시각화 예시

```plaintext
     (A)
     /  \
   (B)  (C)
   / \  / \
 (D)(E)(F)(G)
```

- A, B, C, D, E, F, G가 각각 노드 객체
- A의 `left` = B, A의 `right` = C, 등등

### 2.3 예시 코드 (Java)

```java
// 노드 정의
class Node {
    String value;
    Node left;
    Node right;

    public Node(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class LinkedBinaryTree {
    private Node root;

    public LinkedBinaryTree(String rootValue) {
        this.root = new Node(rootValue);
    }

    public Node getRoot() {
        return root;
    }

    // 간단하게: 주어진 parent 노드에 왼쪽 자식 연결
    public void setLeftChild(Node parent, String value) {
        parent.left = new Node(value);
    }

    // 오른쪽 자식 연결
    public void setRightChild(Node parent, String value) {
        parent.right = new Node(value);
    }

    // 전위 순회 예시
    public void preorderTraverse(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preorderTraverse(node.left);
        preorderTraverse(node.right);
    }

    public static void main(String[] args) {
        // 트리 구성 (A, B, C, D, E, F, G)
        LinkedBinaryTree linkedTree = new LinkedBinaryTree("A");

        Node root = linkedTree.getRoot();
        linkedTree.setLeftChild(root, "B");
        linkedTree.setRightChild(root, "C");

        linkedTree.setLeftChild(root.left, "D");
        linkedTree.setRightChild(root.left, "E");
        linkedTree.setLeftChild(root.right, "F");
        linkedTree.setRightChild(root.right, "G");

        // 전위 순회 출력 (예: A B D E C F G)
        linkedTree.preorderTraverse(linkedTree.getRoot());
    }
}
```

#### LinkedList 기반 방식의 장점
- **동적 할당**이므로 트리의 크기가 유연
- 불필요한 공간 낭비가 적음
- **임의 구조**(편향/균형/완전 등)에 쉽게 대응

#### LinkedList 기반 방식의 단점
- **인덱스 기반 접근**이 불가하므로, 원하는 노드로 가기 위해서는 구조적 탐색이 필요
- 구현이 조금 더 복잡할 수 있음(노드 생성/연결)

---

## 3. 정리

1. **배열 기반 트리**
    - 주로 **완전 이진 트리**에서 편리
    - 인덱스 계산으로 빠른 자식/부모 접근 가능
    - 공간 낭비와 유연성 제한 가능

2. **노드(LinkedList) 기반 트리**
    - **동적 구조**로 삽입/삭제 유연
    - 완전 이진 트리가 아니어도 손쉽게 구성
    - 인덱스 접근 불가, 탐색을 통해 노드를 찾아야 함

코딩 테스트나 실무에서 트리를 구현할 때는 **LinkedList 기반**을 더 자주 사용하지만,  
**Heap(힙)** 등은 배열 기반으로 구현하는 경우가 많습니다.  
상황과 용도에 맞추어 방식을 선택하는 것이 중요합니다.