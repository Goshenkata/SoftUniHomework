function aggregate(input) {
	let sum = 0;
	let invSum = 0;
	let concat = '';
	input.forEach(element => {
		sum += element
		invSum += (1 / element)
		concat += `${element}`
	});
	console.log(sum)
	console.log(invSum)
	console.log(concat)
}
aggregate([1, 2, 3])
