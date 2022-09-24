function exer(arrays) {
  let firstSum = arrays[0].reduce((a, b) => a + b, 0);
  // rows
  for (let index = 0; index < arrays.length; index++) {
    let rowSum = arrays[index].reduce((a, b) => a + b, 0);
    if (rowSum !== firstSum) {
      return false;
    }
		let colSum = 0
    for (let j = 0; j < arrays.length; j++) {
			colSum += arrays[index][j]
		}
    if (colSum !== firstSum) {
      return false;
    }
  }
  return true;
}
console.log(
	exer(
[[11, 32, 45],
 [21, 0, 1],
 [21, 1, 1]]
	)
)
