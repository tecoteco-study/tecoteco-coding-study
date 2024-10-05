// https://school.programmers.co.kr/learn/courses/30/lessons/81303?language=javascript

function solution(n, k, cmd) {
  const left = Array.from({ length: n }, (_, idx) => idx - 1);
  const right = Array.from({ length: n }, (_, idx) => idx + 1);
  const rows = Array(n).fill(true);
  const deleted = [];
  let currentIdx = k;

  cmd.forEach((command) => {
    const operation = command[0];

    if (operation == "U") {
      let steps = parseInt(command.split(" ")[1]);

      while (steps-- > 0) currentIdx = left[currentIdx];
    } else if (operation == "D") {
      let steps = parseInt(command.split(" ")[1]);

      while (steps-- > 0) currentIdx = right[currentIdx];
    } else if (operation == "C") {
      deleted.push(currentIdx);
      rows[currentIdx] = false;

      right[left[currentIdx]] = right[currentIdx];
      left[right[currentIdx]] = left[currentIdx];

      currentIdx =
        right[currentIdx] == n ? left[currentIdx] : right[currentIdx];
    } else if (operation == "Z") {
      const idx = deleted.pop();
      rows[idx] = true;

      right[left[idx]] = idx;
      left[right[idx]] = idx;
    }
  });

  return rows.map((row) => (row ? "O" : "X")).join("");
}
