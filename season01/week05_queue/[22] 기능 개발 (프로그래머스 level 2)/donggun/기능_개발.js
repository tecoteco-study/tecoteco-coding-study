// https://school.programmers.co.kr/learn/courses/30/lessons/42586

function solution(progresses, speeds) {
  const completeDays = progresses.map((p, i) =>
    Math.ceil((100 - p) / speeds[i])
  );

  const answer = [];
  let maxDay = 0;

  completeDays.forEach((day) => {
    if (day > maxDay) {
      answer.push(1);
      maxDay = day;
    } else {
      answer[answer.length - 1]++;
    }
  });

  return answer;
}
