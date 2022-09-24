function exer(input) {
  let out = [];
  input.forEach((element) => {
    let split = element.split(":");
    let name = split[0];
    let price = split[1];
    out.push({ name: name, price: price });
  });
  out.sort((a, b) => {
    a = a.name.toLowerCase();
    b = b.name.toLowerCase();
    if (a > b) {
      return 1;
    } else {
      return -1;
    }
  });
  let lastChar = out[0].name.charAt(0);
  console.log(lastChar);
  out.forEach((element) => {
    let firstChar = element.name.charAt(0);
    if (firstChar != lastChar) {
      console.log(firstChar);
      lastChar = firstChar;
    }
		let productName = element.name.trim()
		let productPrice = element.price.trim()
    console.log(`  ${productName}: ${productPrice}`);
  });
}
exer([
  "Appricot : 20.4",
  "Fridge : 1500",
  "TV : 1499",
  "Deodorant : 10",
  "Boiler : 300",
  "Apple : 1.25",
  "Anti-Bug Spray : 15",
  "T-Shirt : 10",
]);
