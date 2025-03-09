

## 1. 문제 요약

이 문제는 다단계(MLM) 구조에서 칫솔 판매로 인한 이익을 단계별로 분배하는 과정을 계산하는 문제입니다.
- 각 판매원은 자신이 판매한 이익의 10%를 추천인에게 전달하고, 나머지를 자신이 가집니다.
- 추천인에게 전달된 이익도 똑같은 규칙으로 10%를 상위 추천인에게 전달합니다.
- 전달할 이익이 원 단위에서 절사되기 때문에, `10%`의 결과가 `1원 미만`이 되면 더 이상 분배하지 않고 본인이 전부 취득합니다.
- 최종적으로 모든 판매원의 이익을 계산해, 주어진 `enroll` 순서에 맞춰 배열로 리턴합니다.

---

## 2. 해결 전략

1. **트리(계층) 구조 표현**
    - 문제에서 말하는 다단계 조직은 “부모 - 자식” 관계로 볼 수 있습니다.
    - `parentMap`을 사용해, *"이 사람의 추천인은 누구인가?"*를 쉽게 찾을 수 있게 만듭니다.
        - 예: `parentMap.get("edward") = "mary"`.

2. **각 판매원의 누적 이익 관리**
    - `profitMap`을 사용해, *"이 사람이 현재까지 얻은 이익은 얼마인가?"*를 관리합니다.
        - 모든 판매원 이름을 key로 하고, 이익을 int 값으로 저장합니다.

3. **이익 분배 구현**
    - 각 판매원이 판매를 통해 얻은 총 이익(`수량 × 100원`)을 분배하는 함수를 별도로 작성합니다.
    - 분배 과정(while 루프)에서
        1. **부모에게 줄 이익**은 `전체 이익의 10% (floor 연산)`
        2. **자신이 가질 이익**은 `전체 이익 - 부모에게 줄 이익`
        3. 부모가 더 이상 없거나(`null`), 혹은 분배해야 할 금액이 0원이면 분배 종료

이 구조를 통해 어떤 판매원에게 이익이 발생할 때마다, 위로 따라가며 10%씩 계속 분배해 나갈 수 있습니다.

---

## 3. 코드 구현 상세

```java
import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, 
                          String[] seller, int[] amount) {
        Map<String, String> parentMap = new HashMap<>();
        Map<String, Integer> profitMap = new HashMap<>();

        // 1) parentMap, profitMap 초기화
        for (int i = 0; i < enroll.length; i++) {
            // 추천인이 "-"이면 부모 없음(null)
            parentMap.put(enroll[i], referral[i].equals("-") ? null : referral[i]);
            profitMap.put(enroll[i], 0);
        }

        // 2) 각 판매 정보에 대해 이익 분배
        for (int i = 0; i < seller.length; i++) {
            String currentSeller = seller[i];
            int profit = amount[i] * 100; // 칫솔 1개당 100원
            distributeProfit(currentSeller, profit, parentMap, profitMap);
        }

        // 3) 결과 배열 구성
        int[] result = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            result[i] = profitMap.get(enroll[i]);
        }
        return result;
    }

    // 이익 분배 함수
    private void distributeProfit(String seller, int profit, 
                                  Map<String, String> parentMap, 
                                  Map<String, Integer> profitMap) {
        // 현재 seller가 존재하고, 남은 profit이 0원 이상일 때 계속 분배
        while (seller != null && profit > 0) {
            int 부모전달금액 = profit / 10;   // 10% 전달
            int 자신의수익   = profit - 부모전달금액; 

            // 현재 판매원의 이익 누적
            profitMap.put(seller, profitMap.get(seller) + 자신의수익);

            // 다음 분배 주체는 상위 추천인
            seller = parentMap.get(seller);
            // 분배할 다음 이익은 부모전달금액
            profit = 부모전달금액;
        }
    }
}
```

### 주요 포인트

1. **부모 맵(`parentMap`) 생성**
    - 부모(추천인)가 “-”인 경우에는 `null`을 저장해, 상위가 없음을 표현했습니다.

2. **이익 맵(`profitMap`) 관리**
    - 모든 인원의 이익을 0으로 초기화합니다.
    - 분배가 발생할 때마다 해당하는 판매원의 이익을 누적합니다.

3. **`distributeProfit` 함수**
    - **while** 루프로 부모가 없거나(`null`), 전달 이익이 0원이 되면 종료합니다.
    - 전달할 금액은 정수 몫(`profit / 10`)으로 계산하기 때문에, *1원 미만*으로 떨어지면 자동으로 0원이 됩니다.
    - 매 단계에서 자신의 이익을 더해주고, 나머지 10%만큼 부모에게 분배를 넘깁니다.

---

## 4. 복잡도 관점

- **enroll**과 **referral**의 길이를 \(N\), 판매 집계 데이터를 \(M\)이라 할 때,
    - `parentMap`, `profitMap` 초기화: \(O(N)\)
    - 판매 분배 과정: 최대 \(M\)번 반복, 각 분배마다 상위로 올라갈 때마다 최악의 경우 트리의 높이(최대 \(N\))만큼 반복할 수 있습니다.
    - 따라서 **최악의 시간 복잡도**는 \(O(M \times N)\) 정도로 추정됩니다.
- 보통 문제에서 \(N\)과 \(M\)이 크더라도(특히 \(M\)은 10만까지 가능), 실질적으로 모든 판매원이 직간접으로 연결되어 있어도 트리 높이가 너무 깊어지지 않는 케이스가 많기에, 적절히 처리 가능합니다.

---

## 5. 리뷰 시 확인하면 좋은 점

1. **부모가 없을 때(“-”)**
    - 코드에서 `parentMap`을 `null`로 관리하고 있는데, 이 부분이 잘 처리되고 있는지 확인합니다.

2. **정수 나눗셈(10% 계산)과 원 단위 절사**
    - Java에서 `int` 나눗셈은 내림 처리가 자동으로 이뤄집니다. 이 문제에서 요구하는 ‘원 단위 절사’를 만족합니다.

3. **분배 종료 조건**
    - `profit <= 0`이 되거나 `seller == null`일 때 반복을 중단합니다.
    - 문제에서 요구하는 1원 미만 분배 불가 조건이 정확히 충족되는지 체크합니다.

4. **메모리 사용**
    - `Map`에 모든 `enroll` 인원의 정보를 저장해야 하므로, 메모리 효율을 고려해야 합니다.
    - 문제의 제한사항(N=1만, M=10만) 내에서는 충분히 커버 가능합니다.

---

## 6. 결론 및 요약

- **트리 구조**로 조직을 표현하고, 각 판매 건마다 **재귀적(또는 반복적)**으로 10%를 나누어 상위로 올려주는 방식을 코드로 구현했습니다.
- 핵심 로직은 *“부모에게 10% 할당 → 자신 이익 추가 → 부모로 이익 이동”* 패턴을 반복하는 것에 있습니다.
- 모든 판매 기록을 처리한 후, `profitMap`을 조회해 `enroll` 순서대로 결과 배열을 만들면 최종 답을 얻을 수 있습니다.

**이로써 다단계 칫솔 판매 조직에서의 이익 분배 문제를 해결할 수 있습니다.**

해당 코드 리뷰 시에는
- 분배 로직 및 종료 조건이 문제 요구사항과 정확히 일치하는지
- 나눗셈(정수 연산)과 메모리 사용에 대한 부분이 적절한지  
  등을 중점적으로 보시면 도움이 될 것입니다.




---


## 예시 데이터 정리

> enroll = ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]  
> referral = ["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]  
> seller = ["young", "john", "tod", "emily", "mary"]  
> amount = [12, 4, 2, 5, 10]

- **조직 구조**
   - john → (부모: 없음)
   - mary → (부모: 없음)
   - edward → (부모: mary)
   - sam → (부모: edward)
   - emily → (부모: mary)
   - jaimie → (부모: mary)
   - tod → (부모: jaimie)
   - young → (부모: edward)

위 정보를 코드 상에서는 다음과 같이 맵(Map)으로 표현됩니다.
- `parentMap("john") = null`  (`"-"` → `null`)
- `parentMap("mary") = null`
- `parentMap("edward") = "mary"`
- `parentMap("sam") = "edward"`
- `parentMap("emily") = "mary"`
- `parentMap("jaimie") = "mary"`
- `parentMap("tod") = "jaimie"`
- `parentMap("young") = "edward"`

각 사람이 벌어들인 수익을 저장할 `profitMap`은 처음에 전부 0원으로 시작합니다.

---

## 1) 첫 번째 판매: young, 12개 판매

- 판매원: **young**
- 판매 수량: 12개 → **이익 1,200원** (12 × 100)

### 분배(while 루프) 흐름

1. **seller = "young"**, `profit = 1200`
   - 부모에게 줄 금액 = 120원 (10%)
   - young이 가져갈 금액 = 1200 - 120 = 1080원
   - `profitMap("young") += 1080`
   - 다음 분배 대상 = `parentMap("young")` = **"edward"**
   - 다음 profit = 120원

2. **seller = "edward"**, `profit = 120`
   - 부모에게 줄 금액 = 12원 (10%)
   - edward가 가져갈 금액 = 120 - 12 = 108원
   - `profitMap("edward") += 108`
   - 다음 분배 대상 = `parentMap("edward")` = **"mary"**
   - 다음 profit = 12원

3. **seller = "mary"**, `profit = 12`
   - 부모에게 줄 금액 = 1원 (10%, 정수 나눗셈)
   - mary가 가져갈 금액 = 12 - 1 = 11원
   - `profitMap("mary") += 11`
   - 다음 분배 대상 = `parentMap("mary")` = **null** (부모 없음)
   - 다음 profit = 1원

4. **seller = null** → **while 종료**
   - 분배가 멈추면서, 1원은 ‘센터(민호)’에게 돌아가는 셈(코드 상에서는 더 이상 추적하지 않음).

### 첫 판매 후 누적 이익

- young: 1080
- edward: 108
- mary: 11
- 나머지(센터)로 1원

---

## 2) 두 번째 판매: john, 4개 판매

- 판매원: **john**
- 판매 수량: 4개 → **이익 400원** (4 × 100)

### 분배(while 루프) 흐름

1. **seller = "john"**, `profit = 400`
   - 부모에게 줄 금액 = 40원 (10%)
   - john이 가져갈 금액 = 400 - 40 = 360원
   - `profitMap("john") += 360`
   - 다음 분배 대상 = `parentMap("john")` = **null**
   - 다음 profit = 40원

2. **seller = null** → **while 종료**
   - 40원은 센터(민호)로.

### 두 번째 판매 후 누적 이익

- **존재하던 것에 추가**
   - john: 360 (이전까지 0이었으므로 +360)
   - 그 외: young 1080, edward 108, mary 11 → 그대로
- 나머지(센터)로 40원

---

## 3) 세 번째 판매: tod, 2개 판매

- 판매원: **tod**
- 판매 수량: 2개 → **이익 200원** (2 × 100)

### 분배(while 루프) 흐름

1. **seller = "tod"**, `profit = 200`
   - 부모에게 줄 금액 = 20원 (10%)
   - tod가 가져갈 금액 = 200 - 20 = 180원
   - `profitMap("tod") += 180`
   - 다음 분배 대상 = `parentMap("tod")` = **"jaimie"**
   - 다음 profit = 20원

2. **seller = "jaimie"**, `profit = 20`
   - 부모에게 줄 금액 = 2원 (10%)
   - jaimie가 가져갈 금액 = 20 - 2 = 18원
   - `profitMap("jaimie") += 18`
   - 다음 분배 대상 = `parentMap("jaimie")` = **"mary"**
   - 다음 profit = 2원

3. **seller = "mary"**, `profit = 2`
   - 부모에게 줄 금액 = 0원 (10% = 0.2, 정수 나눗셈 → 0)
   - mary가 가져갈 금액 = 2원
   - `profitMap("mary") += 2`
   - 다음 분배 대상 = `parentMap("mary")` = **null**
   - 다음 profit = 0원

4. **seller = null** → **while 종료**

### 세 번째 판매 후 누적 이익

- tod: 180
- jaimie: 18
- mary: 기존 11 + 2 = 13
- 그 외(young, edward, john)는 변화 없음

---

## 4) 네 번째 판매: emily, 5개 판매

- 판매원: **emily**
- 판매 수량: 5개 → **이익 500원** (5 × 100)

### 분배(while 루프) 흐름

1. **seller = "emily"**, `profit = 500`
   - 부모에게 줄 금액 = 50원 (10%)
   - emily가 가져갈 금액 = 500 - 50 = 450원
   - `profitMap("emily") += 450`
   - 다음 분배 대상 = `parentMap("emily")` = **"mary"**
   - 다음 profit = 50원

2. **seller = "mary"**, `profit = 50`
   - 부모에게 줄 금액 = 5원 (10%)
   - mary가 가져갈 금액 = 50 - 5 = 45원
   - `profitMap("mary") += 45`  (기존 13 + 45 = 58)
   - 다음 분배 대상 = `parentMap("mary")` = **null**
   - 다음 profit = 5원

3. **seller = null** → **while 종료**
   - 5원은 센터로.

### 네 번째 판매 후 누적 이익

- emily: 450
- mary: 기존(13) + 45 = 58
- 그 외(young, edward, john, tod, jaimie) 변화 없음

---

## 5) 다섯 번째 판매: mary, 10개 판매

- 판매원: **mary**
- 판매 수량: 10개 → **이익 1,000원** (10 × 100)

### 분배(while 루프) 흐름

1. **seller = "mary"**, `profit = 1000`
   - 부모에게 줄 금액 = 100원 (10%)
   - mary가 가져갈 금액 = 900원
   - `profitMap("mary") += 900` (기존 58 + 900 = 958)
   - 다음 분배 대상 = `parentMap("mary")` = **null**
   - 다음 profit = 100원

2. **seller = null** → **while 종료**
   - 100원은 센터로.

### 다섯 번째(마지막) 판매 후 최종 누적 이익

- mary: 958 (이전 58 + 900)
- 그 외 인원은 변화 없음

---

## 최종 profitMap 상태

위 과정을 전부 적용하면, 문제 설명의 그림과 동일한 수익 배분 상태가 형성됩니다.  
(enroll 순서: `"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"`)

1. **john**: 360
2. **mary**: 958
3. **edward**: 108
4. **sam**: 0   (판매 기록 없음)
5. **emily**: 450
6. **jaimie**: 18
7. **tod**: 180
8. **young**: 1080

이 결과가 문제의 예시 해설과 동일합니다.

---

# 시각적 흐름 요약

아래는 **첫 번째 판매(young) → edward → mary**로 이어지는 분배 과정을 간단히 화살표로 시각화한 예시입니다.

```
[ young ]
   |
   |   10% (120원)
   V
[ edward ]
   |
   |   10% (12원)
   V
[ mary ]
   |
   |   10% (1원)
   V
 ( null : 센터 )
```

각 노드(판매원)에서 **자기가 가져가는 금액**은 `(현재 profit - 부모에게 준 10%)`이 되고, 그 10%만큼을 **상위 추천인**에게 넘깁니다. 상위 추천인이 `null`이면(= “-”) 센터(민호)로 귀속되어 반복이 멈춥니다.

이 로직이 모든 판매에 똑같이 적용되어, 최종적으로 각자의 `profitMap` 누적 금액이 완성됩니다.

---

# 마무리

- **핵심 아이디어**:
   1) 각 사람의 “부모(추천인)”를 Map으로 저장
   2) 판매 시 발생한 이익을 **while** 루프(혹은 재귀)로 10%씩 상위로 분배
   3) 1원 단위 이하가 되면 더 이상 부모에게 전달하지 않음

- 위 예시 분배 과정을 통해 코드를 추적해 보면, 실제로 `distributeProfit` 함수가 (1) 자신이 가져갈 몫, (2) 부모로 넘길 몫을 계산하고, (3) 다음 판매원을 부모로 지정해 분배를 이어가는 흐름을 알 수 있습니다.

- 이렇게 단계별로 분배를 끝낸 뒤, `enroll` 순서대로 `profitMap` 값을 모으면 최종 정답을 얻을 수 있게 됩니다.