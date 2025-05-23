# 동영상 재생기
[문제링크](https://school.programmers.co.kr/learn/courses/30/lessons/340213)

## 문제 설명

당신은 동영상 재생기를 만들고 있습니다. 동영상 재생기는 다음과 같은 기능을 지원합니다:

- **10초 전으로 이동**: 사용자가 "prev" 명령을 입력할 경우, 현재 위치에서 10초 전으로 이동합니다. 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동합니다. 영상의 처음 위치는 0분 0초입니다.
- **10초 후로 이동**: 사용자가 "next" 명령을 입력할 경우, 현재 위치에서 10초 후로 이동합니다. 동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동합니다. 영상의 마지막 위치는 동영상의 길이와 같습니다.
- **오프닝 건너뛰기**: 현재 재생 위치가 오프닝 구간(`op_start` ≤ 현재 재생 위치 ≤ `op_end`)인 경우 자동으로 오프닝이 끝나는 위치로 이동합니다.

동영상의 길이를 나타내는 문자열 `video_len`, 기능이 수행되기 직전의 재생 위치를 나타내는 문자열 `pos`, 오프닝 시작 시각을 나타내는 문자열 `op_start`, 오프닝이 끝나는 시각을 나타내는 문자열 `op_end`, 사용자의 입력을 나타내는 1차원 문자열 배열 `commands`가 주어집니다. 이때 사용자의 입력이 모두 끝난 후 동영상의 위치를 "`mm`:`ss`" 형식으로 반환하는 함수를 작성하세요.

## 제한사항

- `video_len`, `pos`, `op_start`, `op_end`는 모두 "`mm`:`ss`" 형식의 문자열입니다.
    - `mm`은 0 이상 59 이하의 정수입니다.
    - `ss`은 0 이상 59 이하의 정수입니다.
    - 분과 초가 한 자리일 경우 0을 붙여 두 자리로 나타냅니다.
- `commands`의 길이는 1 이상 100 이하입니다.
    - `commands`의 원소는 "prev" 또는 "next"입니다.
    - "prev"는 10초 전으로 이동하는 명령입니다.
    - "next"는 10초 후로 이동하는 명령입니다.

## 입출력 예

| video_len | pos    | op_start | op_end  | commands                | result |
|-----------|--------|----------|---------|-------------------------|--------|
| "34:33"   | "13:00" | "00:55"  | "02:55" | ["next", "prev"]        | "13:00" |
| "10:55"   | "00:05" | "00:15"  | "06:55" | ["prev", "next", "next"] | "06:55" |
| "07:22"   | "04:05" | "00:15"  | "04:07" | ["next"]                | "04:17" |

## 입출력 예 설명

**입출력 예 #1**

- 시작 위치 13분 0초에서 10초 후로 이동하면 13분 10초입니다.
- 13분 10초에서 10초 전으로 이동하면 13분 0초입니다.
- 따라서 "13:00"을 반환합니다.

**입출력 예 #2**

- 시작 위치 0분 5초에서 10초 전으로 이동합니다. 현재 위치가 10초 미만이기 때문에 0분 0초로 이동합니다.
- 0분 0초에서 10초 후로 이동하면 0분 10초입니다.
- 0분 10초에서 10초 후로 이동하면 0분 20초입니다. 0분 20초는 오프닝 구간이기 때문에 오프닝이 끝나는 위치인 6분 55초로 이동합니다.
- 따라서 "06:55"를 반환합니다.

**입출력 예 #3**

- 시작 위치 4분 5초는 오프닝 구간이기 때문에 오프닝이 끝나는 위치인 4분 7초로 이동합니다.
- 4분 7초에서 10초 후로 이동하면 4분 17초입니다.
- 따라서 "04:17"을 반환합니다.
