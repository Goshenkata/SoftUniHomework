function exer(input) {
	let out = {}
	let products = []
	input.forEach(data => {
		data = data.split(' | ')
		let town = data[0]
		let product = data[1]
		let price = Number(data[2])
		if (out[product]) {
			if (out[product].price > price) {
				out[product] = {price: price, town: town}
			}
		} else{
			products.push(product)
			out[product] = {price: price, town: town}
		}
	});
	products.forEach(prod => {
		let price = out[prod].price
		let town = out[prod].town
		console.log(`${prod} -> ${price} (${town})`);
	})
}

exer([
"Sofia City | BMW | 100000",
"Mexico City | BMW | 99999",
])
console.log("----------------");
exer([
"Sofia City | Audi | 100000",
"Sofia City | BMW | 100000",
"Sofia City | Mitsubishi | 10000",
"Sofia City | Mercedes | 10000",
"Sofia City | NoOffenseToCarLovers | 0",
"Mexico City | Audi | 1000",
"Mexico City | BMW | 99999",
"Mexico City | Mitsubishi | 10000",
"New York City | Mitsubishi | 1000",
"Washington City | Mercedes | 1000"
])
