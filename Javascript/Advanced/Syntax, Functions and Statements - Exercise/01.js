function fruit(fruit, grams, priceKg) {
	let kg = grams / 1000;
	let price = priceKg * kg;
	console.log(`I need \$${price.toFixed(2)} to buy ${kg.toFixed(2)} kilograms ${fruit}.`)
}
fruit('yum', 1000, 10)
fruit('yum', 1000, 10)
fruit('yum', 1000, 10)
