function getFibonator() {
  let sequence = [0, 1];
  let n = 0;
  return function () {
    if (n == 0) {
			n++
			return 1
    } else {
      let next = sequence[n] + sequence[n - 1];
      n++;
      sequence.push(next);
      return next;
    }
  };
}
let fib = getFibonator();
console.log(fib()); // 1
console.log(fib()); // 1
console.log(fib()); // 2
console.log(fib()); // 3
console.log(fib()); // 5
console.log(fib()); // 8
console.log(fib()); // 13
