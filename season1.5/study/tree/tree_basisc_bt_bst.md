
# 트리, 이진 트리, 이진 탐색 트리

## 1. 트리(Tree)의 기본 개념
트리는 **계층적 구조**를 표현하기 위해 사용되는 자료구조로, 노드(Node)와 노드들을 연결하는 간선(Edge)들로 구성됩니다. 일반적으로 최상위 노드를 **루트(Root)** 라고 부르며, 루트부터 하위 노드들로 확장되어가는 형태를 띕니다.

### 트리의 주요 특징
1. **루트(Root) 노드**: 트리의 시작점이 되는 노드 (부모가 없음)
2. **부모(Parent)-자식(Child) 관계**: 루트로부터 이어지는 연결 관계
3. **리프(Leaf) 노드**: 자식이 없는 노드
4. **사이클(Cycle)이 존재하지 않음**: 트리는 무방향 그래프 중 사이클이 없는 연결 그래프

트리는 **이진 트리**, **이진 탐색 트리**, **힙**, **AVL 트리**, **레드-블랙 트리** 등 다양한 변형 형태로 사용되며, 코딩 테스트에서 기초적인 이진 트리와 이진 탐색 트리는 자주 등장합니다.

---

## 2. 이진 트리(Binary Tree)
이진 트리는 **각 노드가 최대 두 개의 자식을 가질 수 있는 트리**입니다. 자식을 `Left Child`와 `Right Child`로 구분하는 특징이 있습니다.

### 2.1 이진 트리의 종류
- **완전 이진 트리(Complete Binary Tree)**:
  - 마지막 레벨 전까지 모든 레벨이 노드로 가득 차 있으며, 마지막 레벨도 가능한 왼쪽부터 채워진 이진 트리
- **포화 이진 트리(Full Binary Tree)**:
  - 모든 노드가 0개 또는 2개의 자식을 갖는 이진 트리 (모든 리프가 동일한 레벨에 존재)
- **전이진 트리(Perfect Binary Tree)**:
  - 모든 내부 노드가 2개의 자식을 가지고, 모든 리프 노드가 같은 깊이를 가짐
  - 포화 이진 트리와 유사하게 정의되기도 함

### 2.2 이진 트리의 순회(Traversal)
이진 트리는 보통 **순회**(Traversal)를 통해 데이터를 처리합니다. 대표적인 순회 방법은 다음과 같습니다.

1. **전위 순회(Preorder Traversal)**: (루트) - (왼쪽 서브트리) - (오른쪽 서브트리)
2. **중위 순회(Inorder Traversal)**: (왼쪽 서브트리) - (루트) - (오른쪽 서브트리)
3. **후위 순회(Postorder Traversal)**: (왼쪽 서브트리) - (오른쪽 서브트리) - (루트)
4. **레벨 순회(Level Order Traversal)**: 같은 레벨(깊이) 순으로 순회 (BFS 방식)

### 2.3 이진 트리 예시 코드 (Java)

다음 코드는 간단한 이진 트리를 구성하고 순회하는 예시입니다.

```java
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }
}

public class BinaryTreeExample {
    public static void main(String[] args) {
        // 간단한 이진 트리 구성
        //         1
        //        / \
        //       2   3
        //      / \   \
        //     4   5   6

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        // 이진 트리 순회
        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(root);
        System.out.println();
    }

    // 전위 순회 (Root -> Left -> Right)
    public static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    // 중위 순회 (Left -> Root -> Right)
    public static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    // 후위 순회 (Left -> Right -> Root)
    public static void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }
}
```

---

## 3. 이진 탐색 트리(Binary Search Tree, BST)
이진 탐색 트리는 **이진 트리의 특수한 형태**로서, 다음과 같은 추가적인 **정렬** 특성을 만족합니다.

1. 모든 노드의 **왼쪽 서브트리**에 있는 모든 노드들의 값은 **현재 노드의 값보다 작음**
2. 모든 노드의 **오른쪽 서브트리**에 있는 모든 노드들의 값은 **현재 노드의 값보다 큼**

이러한 특성 덕분에 **탐색, 삽입, 삭제** 등의 연산을 **평균적으로 O(log N)에** 수행할 수 있습니다.

### 3.1 BST 삽입(Insert) 과정
1. 루트에서 시작한다.
2. 삽입하려는 값이 현재 노드의 값보다 작으면 **왼쪽** 서브트리로 이동한다.
3. 삽입하려는 값이 현재 노드의 값보다 크면 **오른쪽** 서브트리로 이동한다.
4. 이동할 곳에 노드가 없으면 새 노드를 삽입한다.

### 3.2 BST 탐색(Search) 과정
1. 루트에서 시작한다.
2. 찾으려는 값이 현재 노드의 값과 같으면 탐색 성공.
3. 작으면 왼쪽, 크면 오른쪽 서브트리로 이동한다.
4. 더 이상 이동할 노드가 없으면 탐색 실패.

### 3.3 이진 탐색 트리 예시 코드 (Java)

```java
class BSTNode {
    int key;
    BSTNode left, right;

    public BSTNode(int item) {
        key = item;
        left = right = null;
    }
}

public class BinarySearchTree {
    BSTNode root;

    // 노드 삽입
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // 재귀적으로 삽입
    private BSTNode insertRec(BSTNode root, int key) {
        // 비어있다면 새 노드를 반환
        if (root == null) {
            root = new BSTNode(key);
            return root;
        }
        
        // 탐색하여 삽입 위치 찾기
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        // key == root.key인 경우 보통 중복 처리가 필요(여기서는 처리 생략)
        
        return root;
    }

    // 노드 검색
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(BSTNode root, int key) {
        if (root == null) return false;
        if (root.key == key) return true;
        return (key < root.key) ? searchRec(root.left, key) : searchRec(root.right, key);
    }

    // 중위 순회(Inorder) - 오름차순으로 정렬된 결과
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(BSTNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* 
          예: 삽입 순서: 50, 30, 20, 40, 70, 60, 80
          
                   50
                  /  \
                30    70
               /  \   / \
             20   40 60 80
        */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // 트리 중위 순회 결과 확인: 20 30 40 50 60 70 80
        tree.inorder();

        // 검색
        System.out.println(tree.search(30)); // true
        System.out.println(tree.search(90)); // false
    }
}
```

### 3.4 이진 탐색 트리의 시간 복잡도
- **평균**: 삽입, 삭제, 탐색 모두 O(log N)
- **최악(Worst-case)**: 트리가 편향되어 있는 경우(Linked List 형태) O(N)
    - 따라서 탐색 트리는 균형 잡히도록 관리(예: AVL, Red-Black Tree 등)하는 것이 중요

---

## 4. 요약
- **트리(Tree)**: 루트 노드부터 하위 노드로 연결된 계층 구조, 사이클 없음
- **이진 트리(Binary Tree)**: 모든 노드가 최대 2개의 자식을 가짐
    - 전위, 중위, 후위, 레벨 순회 등 다양한 순회 방법 존재
- **이진 탐색 트리(BST)**: 왼쪽 서브트리는 현재 노드보다 작은 값, 오른쪽 서브트리는 큰 값을 갖도록 정렬된 형태
    - 평균적으로 빠른 삽입, 검색, 삭제 가능
    - 하지만 트리가 편향되면 시간 복잡도가 떨어짐 (O(N))

트리에 대한 기초 개념을 익히고, 이진 트리 및 이진 탐색 트리의 **구조**, **삽입**, **탐색** 과정을 직접 코드로 작성해보며 이해하는 것이 코딩 테스트에서 매우 중요합니다.
