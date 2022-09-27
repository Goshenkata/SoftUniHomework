function solve() {
  let text = document.getElementById("text").value
  let conv = document.getElementById("naming-convention").value
	if (conv == "Pascal Case") {
		let arr = text.split(" ")
		text = ""
		arr.forEach(element => {
			text += element.charAt(0).toUpperCase() + element.substring(1).toLowerCase()
		});
		document.getElementById("result").innerText = text
	} else if (conv == "Camel Case") {
		let array = text.split(" ")
		text = ""
		text += array[0].toLowerCase()
		for (let index = 1; index < array.length; index++) {
			const element = array[index];
			text += element.charAt(0).toUpperCase() + element.substring(1).toLowerCase()

		}
		document.getElementById("result").innerText = text
	} else {
		document.getElementById("result").innerText = "Error!"
	}
}
