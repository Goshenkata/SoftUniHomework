function exer(array) {
  let out = new Array();
  while (array.length !== 0) {
    let max = Math.max.apply(Math, array);
    let min = Math.min.apply(Math, array);
    if (array.length == 1) {
			out.push(min)
			break;
    } else {
      out.push(min);
      out.push(max);
      array.splice(array.indexOf(max), 1);
      array.splice(array.indexOf(min), 1);
    }
  }
  return out;
}
console.log(exer([1, 2, 3]));
