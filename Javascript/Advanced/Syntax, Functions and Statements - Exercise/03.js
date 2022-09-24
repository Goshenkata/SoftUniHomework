function same(n) {
	let sum = 0
	let numForComp = n % 10
	let same = true
	while(n != 0) {
		lastNum = n % 10
		sum += lastNum
		if (numForComp !== lastNum) {
			same = false
		}
		n = Math.floor(n / 10)
	}
	console.log(same)
	console.log(sum)
}
same(222)
