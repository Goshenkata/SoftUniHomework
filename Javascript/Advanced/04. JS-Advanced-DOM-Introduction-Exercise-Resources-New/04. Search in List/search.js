function search() {
  let townList =Array.from(document.querySelectorAll("ul li"))
	let query = document.getElementById("searchText").value
	let matches = 0
	for (let item of townList) {
		let text = item.textContent
		if (text.includes(query)) {
			item.style.textDecoration = "underline"
			item.style.fontWeight = "bold"
			matches++
		}
	}
	document.getElementById("result").innerText = `${matches} matches found`
}
