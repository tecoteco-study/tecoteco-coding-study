function solution(info, edges) {
  const childrenMap = Array.from({ length: info.length }, () => []);
  for (const [parent, child] of edges) childrenMap[parent].push(child);

  let maxSheepCount = 1;

  // 상태를 표현하는 큐: [현재 노드, 현재 양 수, 현재 늑대 수, 방문 가능한 노드 배열]
  const queue = [[0, 1, 0, []]];

  while (queue.length > 0) {
    const [currNode, sheepCount, wolfCount, nextPossible] = queue.shift();
    const children = childrenMap[currNode];
    nextPossible.push(...children);

    for (const next of nextPossible) {
      const remainingNodes = nextPossible.filter((x) => x !== next);

      if (info[next] === 0) {
        const newSheep = sheepCount + 1;
        maxSheepCount = Math.max(maxSheepCount, newSheep);
        queue.push([next, newSheep, wolfCount, remainingNodes]);
      } else {
        const newWolf = wolfCount + 1;
        if (newWolf < sheepCount) {
          queue.push([next, sheepCount, newWolf, remainingNodes]);
        }
      }
    }
  }

  return maxSheepCount;
}
