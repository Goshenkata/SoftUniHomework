function exer(array) {
	let out = {}
	for (let index  = 0; index  < array.length; index += 2) {
		out[array[index]] = Number(array[index + 1])
	}
	console.log(out)
}
exer(['Yoghurt', '48', 'Rise', '138', 'Apple', '52'])
