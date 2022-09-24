function exer(array) {
  let out = new Array();
  out.push(array[0]);
  let max = array[0];
  for (let index = 1; index < array.length; index++) {
    if (max <= array[index]) {
      out.push(array[index]);
      max = array[index];
    }
  }
	return out
}
