function generateReport() {
  let checkboxes = document.querySelectorAll("input[type=checkbox]");
  let selected = [];
  for (let checkbox of checkboxes) {
    if (checkbox.checked) {
      selected.push(checkbox.name);
    }
  }
  let rows = document.querySelector("tbody").children.length;
  let out = [];
  for (let index = 0; index < rows; index++) {
    let tr = document.querySelectorAll(`tbody tr`).item(index);
    let obj = {};
    for (let j = 0; j < selected.length; j++) {
			const select = selected[j]
			console.log(select)
			let data = tr.children.item(j).firstChild
      obj[select] = data == undefined ? "" : data.data
    }
		console.log("---------------------------------------")
    out.push(obj);
  }
  document.getElementById("output").textContent = JSON.stringify(out);
}
