function exer(array) {
	let out = new Array()
	for (let i = 1; i <= array.length; i++) {
		if (array[i-1] == "add") {
			out.push(i)
		} else {
			out.pop()
		}
	}
	console.log(out.length == 0 ? "Empty" : out.join("\n"))
}
