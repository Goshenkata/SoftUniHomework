function dayOfMonth(month, year) {
	let date = new Date(year, month)
	date.setDate(date.getDate() - 1)
	console.log(date.getDate())
}
