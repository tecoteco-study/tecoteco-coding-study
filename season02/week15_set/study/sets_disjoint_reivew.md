# 집합(Set)과 상호배타적(Disjoint) 집합 개념 리뷰

## 1. 집합(Set)이란?
수학에서 **집합(Set)** 이란, 어떠한 조건을 만족하는 원소(요소)들의 모임을 의미합니다.  
알고리즘/자료구조 영역에서도 “중복을 허용하지 않는 원소의 모임”으로 흔히 다루어집니다.

### 1.1 집합의 표기
- 일반적으로 중괄호(`{ }`)로 감싸서 표현합니다.
- 예:
    - \( A = \{1, 2, 3\} \)
    - \( B = \{2, 4, 6, 8\} \)

### 1.2 집합의 특징
1. **원소의 중복을 허용하지 않는다.**  
   \( \{1,1,2\} \)는 집합 내에서 1이 하나만 존재하므로, 결국 \( \{1,2\} \)와 동일합니다.
2. **원소들의 순서는 상관이 없다.**  
   \( \{2,4,6\} \)과 \( \{4,6,2\} \)는 집합으로서는 동일합니다.
3. **원소가 특정 조건을 만족하면 포함되고, 만족하지 않으면 포함되지 않는다.**

### 1.3 부분집합(Subset)과 진부분집합(Proper Subset)
- 부분집합: \( A \subseteq B \)는 A의 모든 원소가 B에 속할 때 참입니다.
- 진부분집합: \( A \subset B \)는 A가 B의 부분집합이면서, A ≠ B일 때 성립합니다.

## 2. 상호배타적(Disjoint) 집합이란?
**상호배타적 집합(Disjoint Set)** 은 두 집합이 **공통 원소를 전혀 갖지 않는** 경우를 말합니다.

- 예:
    - \( A = \{1, 2, 3\} \)
    - \( B = \{4, 5, 6\} \)  
      이 때, A와 B는 공통 원소가 없으므로 상호배타적 집합입니다.

### 2.1 상호배타적 집합의 수학적 표현
- \( A \cap B = \emptyset \) (A와 B의 교집합이 공집합이면 상호배타적)

### 2.2 여러 개의 집합에서의 상호배타성
- 세 집합 \( A, B, C \) 가 전부 상호배타적이려면  
  \( A \cap B = \emptyset, B \cap C = \emptyset, A \cap C = \emptyset \)  
  이 모두 만족해야 합니다.



## 3. 어디에 쓸까?

집합이라고 하면 조금 추상적으로 느껴질 수 있지만, 사실 일상 속 곳곳에서 쉽게 찾아볼 수 있는 개념이다.

예를 들어, 소셜 미디어에서 여러 해시태그를 붙여서 글을 올린다거나, 친구 목록을 관리한다거나 할 때도 “집합”과 “상호배타적(서로 겹치지 않는) 집합” 개념을 유용하게 사용할 수 있다.

### **Q1. 알고리즘에서 왜 집합을 배울까?**
A1. 집합은 **“중복이 없는 원소들의 모임”** 이라는 특징을 가지고 있다.
- 예를 들어, 어떤 문제를 풀 때 데이터 안에 중복된 값들이 너무 많으면 처리가 불편해질 것이다. 이럴 때 **집합 자료구조**를 사용하면, 중복된 원소들은 알아서 제거되고 유일한 원소들만 남아 효율적인 처리가 가능하다.
- 또한 “두 집합이 교집합을 갖는지(겹치는 원소가 있는지) 없는지”를 빠르게 확인할 수 있다.
- **상호배타적(Disjoint) 집합**인 경우, 교집합이 없으므로 손쉽게 “겹치는 것이 전혀 없다는” 결론을 낼 수 있다.


### **Q2. 일상에서 집합이 어디에 숨어있을까?**
A2. 많은 예시가 있지만, 여기서는 대표적으로 **해시태그**와 **친구 목록**을 꼽아볼 수 있겠다.

#### 1) **사례 1: 해시태그(Hashtag) 예시**
- 인스타그램이나 트위터 등에서 글을 올릴 때, 글의 특징을 표현하기 위해 **#맛집, #주말, #파스타**와 같은 해시태그를 쓰곤 한다.
- 한 게시물에 달린 해시태그들의 모임을 **“집합”**으로 볼 수 있다.
    - 예를 들어,

$$
\text{게시물1 해시태그} = \{\#맛집, \#파스타, \#주말\}
$$

$$
\text{게시물2 해시태그} = \{\#맛집, \#데이트\}
$$




```
   ┌───────┐         ┌────────┐
   │게시물1 │         │게시물2 │
   └───┬───┘         └───┬────┘
       │                 │
       ▼                 ▼
   ┌───────────────────────────┐
   │     #맛집     #파스타      │  →  {#맛집, #파스타, #주말}
   │                 #주말      │      {#맛집, #데이트}
   │                           │
   └───────────────────────────┘

게시물1과 게시물2 모두 #맛집을 갖고 있으므로
교집합: {#맛집}
```

- 이렇게 표현해두면, 예를 들어 게시물1과 게시물2는 **“\#맛집”** 이라는 해시태그를 **공통 원소**로 갖고 있다.
    - 교집합 ($\cap$)으로 표현하면

$$
\{\#맛집, \#파스타, \#주말\} \cap \{\#맛집, \#데이트\} = \{\#맛집\}
$$

- 즉, 두 게시물은 **완전히 상호배타적**이지 않고, **\#맛집**이라는 해시태그로 이어져 있음을 알 수 있다.


#### 2) **사례 2: 친구 목록 예시**
- 서로 다른 사람 두 명의 **“친구 목록”** 을 살펴볼 때, 그 목록을 각각의 **집합**으로 생각할 수 있다.
- 예를 들어,

$$
A(\text{영희}) = \{\text{현수}, \text{민지}, \text{수빈}\}, \quad
B(\text{철수}) = \{\text{현수}, \text{도현}, \text{나영}\}
$$

- 위 예시에서 보면, 영희와 철수의 친구 집합은 **“현수”** 라는 공통 친구를 갖는다.
    - 교집합:

$$
A \cap B = \{\text{현수}\}
$$


```
   ┌────────┐    ┌─────────┐
   │  영희  │    │   철수   │
   └────────┘    └─────────┘
        |               |
        ▼               ▼
   ┌───────────┐  ┌───────────┐
   │ 현수 민지  │  │ 현수 도현  │
   │    수빈    │  │     나영   │
   └───────────┘  └───────────┘

영희의 친구 집합: {현수, 민지, 수빈}
철수의 친구 집합: {현수, 도현, 나영}

공통(교집합): {현수}
```

- 만약 어떤 두 사람의 친구 목록이 전혀 겹치지 않는다면(교집합이 없다면), 그 두 친구 목록은 **상호배타적 집합**이라고 말할 수 있을 것이다.
- 이러한 개념은 **소셜 네트워크**에서 “연결 관계가 있는지”, “서로 전혀 연결된 사람이 아닌지” 등을 빠르게 파악하는 데 유용하게 쓰일 수 있다.




### **알고리즘적으로 유용할까?**

> **“집합과 상호배타적 집합을 아는 것이 중요할까?”**
>
> - **중복 없는 데이터** 관리:
    >   - 중복을 제거해야 하는 상황(예: 특정 조건을 만족하는 유일한 값들만 빠르게 골라내기)에 집합 구조가 편리하다.
> - **빠른 원소 검색**:
    >   - 보통 프로그래밍에서 집합은 해시(Hash) 구조를 이용해 구현되므로, 어떤 값이 존재하는지 **빠르게** 확인할 수 있다.
> - **교집합, 합집합, 차집합을 이용한 문제 해결**:
    >   - 서로 다른 데이터 집합 간에 겹치는 부분이 있는지(교집합), 전부 합쳐서 보면 어떤 모습인지(합집합), 어떤 것들만 단독으로 존재하는지(차집합)를 효율적으로 계산할 수 있다.
> - **상호배타적 집합(Disjoint Set) 알고리즘**:
    >   - 그래프나 네트워크에서 **연결**을 찾거나, **군집(클러스터)** 을 분리할 때 자주 쓰이는 기법이다. 유니온 파인드(Union-Find) 구조 등이 대표적 예이다.









## 4. 요약
- **집합**은 중복 없는 원소들의 모임이며, 순서는 고려하지 않는다.
- **상호배타적(Disjoint) 집합**은 서로 교집합이 없어 공통 원소를 갖지 않는 집합이다.
- 알고리즘 영역에서, 상호배타적 집합 구조는 **유니온-파인드(Union-Find) 알고리즘** 구현에 많이 사용된다.
