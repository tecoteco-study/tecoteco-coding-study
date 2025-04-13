
# 코드 리뷰 문서

## 1. 개요
- **목적**: 주어진 `Solution` 클래스는 프로그래머스의 “섬 연결하기” 문제(MST 문제)를 **Kruskal 알고리즘**으로 해결하는 코드입니다.
- **핵심 포인트**: 최소 스패닝 트리를 찾기 위해,
    1. `Connection` 객체를 이용해 간선 정보를 정렬(오름차순)
    2. Union-Find(Disjoint Set)를 이용해 사이클 여부를 확인
    3. 연결 가능하면 union을 수행하고 비용을 누적

이를 통해 모든 섬(노드)을 최소 비용으로 연결합니다.

---

## 2. 코드 구조

### 2.1. 클래스 및 메서드
1. **`Solution` 클래스**
    - `solution(int n, int[][] costs)`: 메인 로직을 담고 있는 메서드
    - `union(int[] parent, int a, int b)`: Disjoint Set 자료구조에서 두 집합을 병합
    - `findParent(int[] parent, int a)`: 해당 노드의 루트(대표자)를 찾음

2. **`Connection` 내부 클래스**
    - MST의 간선 정보를 담기 위한 데이터 클래스
    - `Comparable<Connection>`을 구현하여 `cost` 기준으로 비교 가능
    - `compareTo()`에서 `this.cost`와 `o.cost`를 비교하여 오름차순 정렬을 지원

3. **테스트 클래스** (`WordChainTests`)
    - JUnit을 이용해 예제 테스트케이스가 정상 동작하는지 검증

### 2.2. 주요 로직

```java
// 1) parent 배열 초기화
int[] parent = new int[n];
for (int i = 0; i < n; i++) {
    parent[i] = i;
}

// 2) 간선 정보 connections 리스트에 저장
List<Connection> connections = new ArrayList<>();
for (int[] cost : costs) {
    connections.add(new Connection(cost[0], cost[1], cost[2]));
}

// 3) 간선을 cost 오름차순으로 정렬 (Comparable 이용)
Collections.sort(connections);

// 4) MST 생성: 간선을 하나씩 확인하며 findParent() == 다르면 union
int totalCost = 0;
for (Connection connection : connections) {
    if (findParent(parent, connection.from) != findParent(parent, connection.to)) {
        union(parent, connection.from, connection.to);
        totalCost += connection.cost;
    }
}
return totalCost;
```

> **Kruskal 알고리즘**의 전형적인 구현 흐름을 잘 따르고 있습니다.

---

## 3. 장점 / 잘 구현된 점

1. **Kruskal 알고리즘의 정석적 구현**
    - 간선을 비용 순으로 정렬 후, Union-Find로 사이클 판별하는 전형적인 방식이라 명확하고 직관적입니다.
    - `Comparable<Connection>` 구현으로 정렬 부분이 간결해졌습니다.

2. **가독성과 책임 분리**
    - `union()`과 `findParent()` 메서드가 **각각의 기능**을 잘 분리하고 있어, 코드를 읽고 이해하기가 쉽습니다.
    - `Connection` 클래스가 간선 정보를 캡슐화해, 코드 전반의 **표현력**을 높였습니다.

3. **적절한 자료구조 사용**
    - MST 문제에서 가장 일반적으로 사용하는 **Union-Find**를 직관적으로 구현하여, 불필요한 복잡성이 없습니다.

4. **테스트 코드 작성**
    - `@Test`를 이용해 대표 케이스에 대한 검증을 수행하고 있어, 코드가 의도한 대로 동작하는지 쉽게 확인 가능합니다.

---

## 4. 개선/보충이 가능한 부분

1. **주석 및 문서화**
    - 현재 코드 자체로도 이해가 가능하지만, **짧은 주석**을 추가하면 초심자나 협업자 입장에서 더 빠르게 이해할 수 있습니다.
    - 예: `// parent 배열 초기화` , `// Kruskal 알고리즘: 비용 오름차순 정렬` 등

2. **Union-Find 경로 압축 최적화**
    - `findParent()` 메서드 안에서 이미 `parent[a] = findParent(parent, parent[a])` 와 같은 **경로 압축**을 수행하고 있습니다.
    - 현재 구조도 충분히 효율적이지만, 필요하다면 **rank**(또는 size) 기준으로 union할 수도 있습니다. (`unionByRank` 기법)
    - 다만 섬 개수(n<=100)로는 현재 방식도 충분히 빠르므로 크게 문제될 일은 없을 듯합니다.

3. **추가 예외 처리**
    - 문제 설명상 “연결할 수 없는 섬은 주어지지 않는다” 라고 하지만, 만약 주어질 수 있다면 모든 섬이 연결 가능한지 여부를 반환하기 전 검증해야 합니다.
    - 이 로직에선 별도 예외처리는 필요 없으나, `n`이나 `costs`가 엣지 케이스일 때 동작을 어떻게 할지 고민해볼 수 있습니다.

4. **테스트 케이스 확장**
    - 단일 테스트 케이스 외에, 경계 조건(예: n=1, n=2, 모든 섬이 이미 최소 간선만으로 연결된 경우 등)을 추가해보면 좋습니다.

---

## 5. 성능 분석

- **시간 복잡도**
    - Kruskal 알고리즘의 복잡도는 일반적으로 **O(E log E)** (간선 수 E에 대한 정렬) + **Union-Find 연산**(사실상 거의 O(1)로 간주).
    - 문제에서 n ≤ 100이므로, 최대 간선 수는 대략 n(n-1)/2 = 4,950 정도. 정렬로 인한 오버헤드도 충분히 작아 **성능상 문제 없음**.

- **공간 복잡도**
    - `parent` 배열: O(n)
    - `connections` 리스트: O(E)
    - n과 E가 크지 않으므로 메모리 사용은 매우 안정적입니다.

---

## 6. 결론 및 요약

- **결론**: 본 코드는 “섬 연결하기” 문제를 **정석적인 Kruskal MST 알고리즘**으로 잘 해결하고 있으며,
    - 가독성, 자료구조의 적절성, 코드 구조 등에서 전반적으로 **우수**하게 작성되었습니다.
- **개선점**: 주석, 추가 최적화(Union By Rank), 다양한 테스트 케이스 확장 등을 고려하면 더욱 견고하고 협업에 좋은 코드가 될 것입니다.

