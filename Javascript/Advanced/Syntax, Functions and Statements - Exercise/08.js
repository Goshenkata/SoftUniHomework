function exer(x1, y1, x2, y2) {
	function isValid(x11, y11, x22, y22) {
		let distance = Math.sqrt((Math.pow(x22-x11, 2)) + (Math.pow((y22 - y11), 2)))
		let valid = Number.isInteger(distance) ? 'valid' : 'invalid'
		console.log(`{${x11}, ${y11}} to {${x22}, ${y22}} is ${valid}`)
	}
	isValid(x1, y1, 0, 0)
	isValid(x2, y2, 0, 0)
	isValid(x1, y1, x2, y2)
}
exer(2, 1, 1, 1)
