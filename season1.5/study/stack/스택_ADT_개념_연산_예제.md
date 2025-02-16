
# 스택(Stack) 이론 리뷰

## 1. ADT (Abstract Data Type)

**ADT**란 '추상 자료형(Abstract Data Type)'의 줄임말로, 자료 구조를 추상화하여 **어떤 연산을 하고, 어떤 결과를 내야 하는지**에만 집중하는 개념입니다. 즉, 내부 구현 방식과는 무관하게, 자료 구조가 갖춰야 할 연산과 그 연산들의 수행 결과를 정의해 놓은 것입니다.

- 예를 들어, 리스트(배열, 연결 리스트), 스택, 큐 등 여러 자료 구조가 존재하고, 각각의 ADT에는 삽입, 삭제, 검색, 순회 등 자료 구조의 동작을 정의합니다.
- 실제 프로그래밍 언어에서 어떤 자료구조를 어떻게 구현하는지는 언어적/환경적 제약(메모리 구조, 라이브러리 등)에 따라 달라지지만, ADT 자체는 이와 관계없이 **개념적인 연산과 그 동작을 정의**하는 것입니다.


![abstract-data-types-in-cpp-1-1671495709.webp](stack%2Fabstract-data-types-in-cpp-1-1671495709.webp)
(https://www.naukri.com/code360/library/abstract-data-types-in-cpp)

## 2. 스택(Stack) ADT

스택은 **후입선출(LIFO, Last In First Out)** 구조의 추상 자료형입니다. 한 쪽 끝에서만 자료를 넣고(pop, push), 확인할 수 있는 자료 구조입니다.

스택 ADT의 기본 연산은 다음과 같이 정의됩니다:

1. **push(item)**: 스택의 top(가장 위)에 새로운 원소 `item`을 삽입한다.
2. **pop()**: 스택의 top에 있는 원소를 제거하고 그 값을 반환한다.
3. **peek()** (또는 `top()`): 스택의 top에 있는 원소를 제거하지 않고, 그 값을 확인한다.
4. **isEmpty()**: 스택이 비어 있는지 확인한다. 비어 있으면 `true`, 아니면 `false`를 반환한다.

### 스택의 특징
- **메모리 구조**: 재귀 호출 사용 시, 스택 프레임(Stack Frame) 형태로 구성  
- **주요 사용 예시**:  
  - 괄호 검사 (올바른 괄호 문자열 판단)  
  - 문자열 뒤집기  
  - 실행 취소 기능(Undo, Ctrl+Z)  
  - 뒤로 가기/앞으로 가기 (웹 브라우저 기록)  
  - 수식 계산 (중위 → 후위 표기법 등)  
  - 그래프 깊이 우선 탐색(DFS) 구현

```
스택(Stack) 상태 변화

초기 상태 (빈 스택)
(empty)

push(10)
┌─────┐
│  10 │   ← top
└─────┘

push(20)
┌─────┐
│  20 │   ← top
├─────┤
│  10 │
└─────┘

push(30)
┌─────┐
│  30 │   ← top
├─────┤
│  20 │
├─────┤
│  10 │
└─────┘

peek() -> 30 (stack unchanged)

pop() -> 30 (removes 30)
┌─────┐
│  20 │   ← top
├─────┤
│  10 │
└─────┘

peek() -> 20 (stack unchanged)
```

## 3. 스택의 연산 (Java - `ArrayDeque` 예시)

Java에는 `Stack` 클래스를 직접 사용할 수도 있지만, 권장되는 방법은 `Deque` 인터페이스를 구현한 `ArrayDeque` 또는 `LinkedList` 등을 사용하는 것입니다. `ArrayDeque`를 사용해서 스택을 구현하는 간단한 예시는 다음과 같습니다.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class StackExample {
    public static void main(String[] args) {
        // ArrayDeque를 이용해 Deque 인터페이스로 스택 사용
        Deque<Integer> stack = new ArrayDeque<>();

        // push 연산
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // peek 연산 (스택 맨 위 값 확인)
        System.out.println("Top of stack: " + stack.peek()); // 30

        // pop 연산 (스택에서 제거하며 꺼내기)
        System.out.println("Popped: " + stack.pop()); // 30
        System.out.println("Popped: " + stack.pop()); // 20

        // isEmpty() 연산
        if (!stack.isEmpty()) {
            System.out.println("Stack is not empty. Top is: " + stack.peek()); // 10
        }
    }
}
```

- `push(e)`: 맨 위에 요소 `e`를 추가
- `pop()`: 맨 위 요소를 제거 후 반환
- `peek()`: 맨 위 요소를 제거하지 않고 확인
- `isEmpty()`: 비었는지 확인

---

## 4. 스택을 이용한 코딩테스트 문제 예시

스택은 코딩 테스트에서 매우 자주 등장합니다. 주로 데이터를 저장하고, 직전의 상태를 되돌려야 한다거나, 특정 순서대로 처리해야 하는 상황에서 유용합니다.

아래는 **난이도가 비교적 쉬운 예시**와 **난이도가 더 높은 예시**를 통해 스택이 어떻게 활용되는지 살펴보겠습니다.

---

### 4.1 쉬운 문제 예시: 컨트롤 제트

#### 문제 설명

숫자와 `"Z"`가 공백으로 구분되어 담긴 문자열이 주어집니다. 문자열에 있는 숫자를 차례대로 더하려고 할 때, `"Z"`가 나오면 **바로 전에 더했던 숫자를 빼야** 합니다. 즉 `"Z"`는 이전에 추가한 숫자를 취소(삭제)하는 역할로 볼 수 있습니다.

- 예: `"1 2 Z 3"` →
    1. `1`을 더함
    2. `2`를 더함
    3. `Z`가 등장 → 바로 전에 더했던 `2`를 빼야 하므로 `2` 취소
    4. `3`을 더함  
       최종 합: `1 + (2 취소) + 3 = 4`

#### 문제 요구사항

1. `s`의 길이는 최대 200
2. `s`는 숫자와 `"Z"`, 그리고 공백으로 구성
3. `s`는 연속된 공백 없이 주어짐
4. 숫자는 -1000 ~ 1000 범위 (0부터 시작하지 않음)
5. `"Z"`가 연속으로 나오는 경우 없음
6. `"Z"`가 나왔을 때 취소할 숫자가 반드시 존재함 (즉, `s`의 시작은 숫자)

#### 문제 해결 아이디어 (스택 사용)

- 스택을 이용해 숫자를 `push`
- `"Z"`가 나오면 가장 최근에 추가한 숫자를 `pop`
- 모든 처리가 끝난 후 스택에 남아있는 숫자를 모두 더함

#### 예시 코드 (Java)

```java
import java.util.*;

class ControlZ {
    public int solution(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        // 입력으로 들어온 문자열을 공백 기준으로 분리
        String[] tokens = s.split(" ");
        
        for (String token : tokens) {
            if (token.equals("Z")) {
                // 직전에 더했던 숫자 제거
                stack.pop();
            } else {
                // 숫자인 경우 스택에 push
                stack.push(Integer.parseInt(token));
            }
        }
        
        // 스택에 남은 모든 숫자를 더함
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        
        return sum;
    }

    // 테스트 실행 예시
    public static void main(String[] args) {
        ControlZ cz = new ControlZ();
        System.out.println(cz.solution("1 2 Z 3"));        // 4
        System.out.println(cz.solution("10 20 30 40"));    // 100
        System.out.println(cz.solution("10 Z 20 Z 1"));    // 1
        System.out.println(cz.solution("10 Z 20 Z"));      // 0
        System.out.println(cz.solution("-1 -2 -3 Z"));     // -3
    }
}
```

**로직 요약**
1. 공백으로 분리한 문자열을 순회
2. 숫자는 스택에 `push`
3. `"Z"`일 경우 스택에서 `pop`
4. 최종적으로 스택에 남은 모든 숫자의 합을 반환

---

### 4.2 어려운 문제 예시: 주식가격

#### 문제 설명

초 단위로 기록된 주식가격이 담긴 배열 `prices`가 주어집니다. 각 시점의 가격이 떨어지지 않은 기간은 몇 초인지를 구하는 문제입니다.

- **가격이 떨어진 시점**: 뒤에 오는 시점의 주가가 현재 주가보다 낮아지는 순간
- **기간 계산**: 해당 시점부터 **가격이 떨어진 시점까지의 시간**을 의미
- 끝까지 가격이 떨어지지 않았다면, 남은 기간만큼 시간이 유지된 것으로 봅니다.

#### 예시

- 입력: `prices = [1, 2, 3, 2, 3]`
- 결과: `[4, 3, 1, 1, 0]`
    1. `1초 시점의 1원` → 가격이 이후 시점(2, 3, 2, 3) 어느 시점에서도 1원보다 떨어지지 않음. 끝까지 유지되므로 총 `4초`
    2. `2초 시점의 2원` → 이후 시점(3, 2, 3) 중, 2원보다 떨어지는 시점은 `4초 시점의 2원`(같은 가격이지만 떨어지지 않은 것으로 간주해야 검증 필요; 보통 “떨어지지 않은”은 **이전 가격보다 같거나 높은 가격**을 말함. 문제 정의를 따라야 함). 문제의 예시 결과를 보면 4초 시점(2원)은 현재 가격(2원)과 같으므로 “떨어지지 않았다”고 본다면, 5초 시점까지 가격이 떨어지지 않은 셈이지만, 예시 설명에서 `2초 시점의 2원은 끝까지 떨어지지 않았다`고 하면서 `3`초 유지라고 되어 있습니다.
        - 문제의 예시 해설에 따르면 `2초 시점의 2원`은 `3초, 2초, 3초`가 이어져요. `2 -> 3` 은 떨어지지 않음, `2 -> 2`는 같음, 그런데 이걸 ‘가격이 떨어지지 않음’으로 볼지?
        - 실제 문제 설명에는 `가격이 떨어지지 않는 동안`을 계산한 후, `price가 떨어지는 순간`을 만났을 때 그 시점을 빼서 계산합니다.
        - 자세한 논리는 문제 해설을 꼭 참고해야 합니다. (문제에서 주어진 공식 예시의 결과를 따른다고 할 때, 2초 시점 결과는 3초로 나와있습니다.)
    3. …
    4. `5초 시점의 3원` → 바로 마지막이므로 0초로 계산

#### 해결 방법

이 문제는 브루트 포스로 풀면 시간 복잡도가 O(n^2)가 되지만, **스택**을 사용하면 더 효율적으로(평균 O(n)) 해결할 수 있습니다.

**스택을 이용한 대표적 아이디어**:
- 인덱스(시간)와 가격을 함께 스택에 저장
- 현재 시점의 가격이 이전 시점의 가격보다 낮아졌다면, 해당 이전 시점을 스택에서 빼면서 기간 계산

#### 예시 코드 (Java)

```java
import java.util.*;

class StockPrice {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        
        // 스택에는 각 시점의 인덱스를 저장
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            // 스택이 비어있지 않고, 현재 가격이 스택 top(이전 시점)의 가격보다 떨어진 경우
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int top = stack.pop();
                // 떨어지지 않은 기간 계산: 현재 i 시점에서 가격이 떨어졌으므로
                // top 시점부터 i까지 (i - top)초 동안 유지
                answer[top] = i - top;
            }
            stack.push(i); 
        }
        
        // 끝까지 가격이 떨어지지 않은 주식들 처리
        while (!stack.isEmpty()) {
            int top = stack.pop();
            // 끝까지 떨어지지 않았으므로 전체 길이 n - 1까지 유지
            answer[top] = (n - 1) - top;
        }
        
        return answer;
    }
    
    // 실행 예시
    public static void main(String[] args) {
        StockPrice sp = new StockPrice();
        int[] result = sp.solution(new int[]{1, 2, 3, 2, 3});
        System.out.println(Arrays.toString(result)); // [4, 3, 1, 1, 0]
    }
}
```

**알고리즘 해설**:
1. 각 시점(인덱스)을 **오름차순**으로 순회
2. 현재 시점의 주가가 이전에 저장된 주가(스택 top)보다 낮으면, **주가가 떨어진 순간**으로 간주
    - 스택에서 해당 인덱스를 꺼내며 `answer[인덱스] = 현재 시점 - 해당 인덱스` 로 계산
3. 위 과정이 끝나고도 스택에 남은 인덱스들은 **끝까지 가격이 떨어지지 않은 것**
    - 따라서 `(n - 1) - topIndex`를 이용해 기간을 계산

**시간 복잡도**: 평균적으로 O(n)
- 각 요소(인덱스)가 스택에 들어갔다 나오면서 최대 2번의 push/pop 연산을 수행

---

## 5. 마무리

- **ADT**는 자료 구조가 가져야 할 연산과 동작을 추상화한 개념입니다.
- **스택(Stack)**은 후입선출 구조로, 직전 상태를 되돌리거나, 괄호/문자열/수식 계산 등의 상황에 자주 쓰입니다.
- 자바에서 스택을 구현할 때는 `Stack` 클래스보다 `ArrayDeque`(또는 `LinkedList`)를 Deque로 사용하는 것이 보편적이며,
    - `push()`, `pop()`, `peek()`, `isEmpty()` 등을 이용해 스택의 기본 연산을 수행합니다.
- **컨트롤 제트** 예시처럼, 최근 작업 취소(Undo)와 같은 기능을 스택으로 간단히 구현할 수 있습니다.
- **주식가격** 예시처럼, O(n^2) → O(n) 성능 개선에 스택을 활용하기도 합니다.

스택은 이렇게 간단한 문제부터 효율적인 문제 해결까지 넓은 스펙트럼으로 자주 활용됩니다.  
코딩 테스트를 준비하는 과정에서 **스택의 다양한 활용 패턴**을 습득해 두면 큰 도움이 됩니다.
