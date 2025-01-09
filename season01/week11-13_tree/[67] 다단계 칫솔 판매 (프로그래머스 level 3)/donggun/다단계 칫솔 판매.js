function solution(enroll, referral, seller, amount) {
  const pointMap = new Map([["-", 0]]);
  const parentMap = new Map();

  enroll.forEach((e, idx) => {
    pointMap.set(e, 0);
    parentMap.set(e, referral[idx]);
  });

  const addPoint = (seller, profit) => {
    if (profit <= 0 || seller == null) return;

    const tax = Math.floor(0.1 * profit);
    pointMap.set(seller, pointMap.get(seller) + profit - tax);

    addPoint(parentMap.get(seller), tax);
  };

  seller.forEach((s, idx) => {
    const profit = amount[idx] * 100;
    addPoint(s, profit);
  });

  return enroll.map((e) => pointMap.get(e));
}
