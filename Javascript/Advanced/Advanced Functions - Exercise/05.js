function add(n) {
  function calc(n2) {
    return add(n + n2);
  }
  calc.toString = function () {
    return n;
  };
  return calc;
}
console.log(add(4)(3).toString());
