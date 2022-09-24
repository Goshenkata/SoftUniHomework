function exer(array, step) {
	let out = new Array
	for (let i = 0; i < array.length; i += step) {
		out.push(array[i])
	}
	return out
}
