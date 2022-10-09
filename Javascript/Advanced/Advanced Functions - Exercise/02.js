function argumentInfo(...arguments) {
  let typeNumber = new Map();
  for (const arg of arguments) {
    let type = typeof arg;
    console.log(`${type}: ${arg}`);
    if (typeNumber.has(type)) {
      typeNumber.set(type, typeNumber.get(type) + 1);
    } else {
      typeNumber.set(type, 1);
    }
  }

  let m2 = new Map([...typeNumber.entries()].sort((a, b) => b[1] - a[1]));
  m2.forEach((element, key) => {
    console.log(`${key} = ${element}`);
  });
}
argumentInfo({ name: "bob" }, 3.333, 9.999);
