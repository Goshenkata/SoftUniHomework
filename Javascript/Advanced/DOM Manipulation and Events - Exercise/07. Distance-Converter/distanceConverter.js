function attachEventsListeners() {
  let converBtn = document.getElementById("convert");
  converBtn.addEventListener("click", convert);
	function convert() {
		let input = document.getElementById("inputUnits").value
		let output = document.getElementById("outputUnits").value
		let inputDistance = Number(document.getElementById("inputDistance").value)
		let meters
		switch (input) {
			case "km":
				meters = inputDistance * 1000
				break;
			case "m":
				meters = inputDistance
				break;
			case "cm":
				meters = inputDistance * 0.01
				break;
			case "mm":
				meters = inputDistance * 0.001
				break;
			case "mi":
				meters = inputDistance * 1609.34
				break;
			case "yrd":
				meters = inputDistance * 0.9144
				break;
			case "ft":
				meters = inputDistance * 0.3048
				break;
			case "in":
				meters = inputDistance * 0.0254
				break;
		}

		let out = 0
		switch (output) {
			case "km":
				out = meters / 1000
				break;
			case "m":
				out = meters
				break;
			case "cm":
				out = meters / 0.01
				break;
			case "mm":
				out = meters / 0.001
				break;
			case "mi":
				out = meters / 1609.34
				break;
			case "yrd":
				out = meters / 0.9144
				break;
			case "ft":
				out = meters / 0.3048
				break;
			case "in":
				out = meters / 0.0254
				break;
		}

		let outBox = document.getElementById("outputDistance")
		outBox.value = out

	}
}

