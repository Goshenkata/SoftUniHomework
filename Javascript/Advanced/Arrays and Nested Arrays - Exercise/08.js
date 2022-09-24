function exer(array) {
  array.sort((v1, v2) => {
    if (v1.length == v2.length) {
      return v1.localeCompare(v2);
    } else {
      return v1.length > v2.length ? 1 : -1;
    }
  });
  console.log(array.join("\n"));
}
exer(["Isacc", "Theodor", "Jack", "Harrison", "George"]);
