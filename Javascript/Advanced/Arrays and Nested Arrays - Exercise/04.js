function exer(array, rotation) {
  function rotate(arr) {
    let last = arr[arr.length - 1];
    for (let index = arr.length - 1; index >= 0; index--) {
			arr[index] = arr[index - 1]
    }
    arr[0] = last;
		return arr
  }
	for (let index = 0; index < rotation; index++) {
		array = rotate(array)
	}
	console.log(array.join(" "))
}
exer(["1", "2", "3", "4"], 2);
