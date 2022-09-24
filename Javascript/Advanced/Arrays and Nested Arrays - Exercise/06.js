function exer(array) {
	array.sort()
	for (let index = 0; index < array.length; index++) {
		console.log(`${index+1}.${array[index]}`)
	}
}
exer(["John", "Bob", "Christina", "Ema"])
