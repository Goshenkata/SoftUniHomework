function exer(array) {
  let out = [];
  for (let index = 1; index < array.length; index++) {
    let elementArr = array[index].split("|");
		let lat = Number(elementArr[2].trim()).toFixed(2)
		let lon = Number(elementArr[3].trim()).toFixed(2)
    let obj = {
      Town: elementArr[1].trim(),
      Latitude: Number(lat),
      Longitude: Number(lon)
    };
    out.push(obj);
  }
  console.log(JSON.stringify(out));
}
exer([
  "| Town | Latitude | Longitude |",
  "| Sofia | 42.696552 | 23.32601 |",
  "| Beijing | 39.913818 | 116.363625 |",
]);
