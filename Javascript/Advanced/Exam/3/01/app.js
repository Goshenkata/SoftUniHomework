function solve() {
  let hireWorker = document.getElementById("add-worker");
  hireWorker.addEventListener("click", hire);
	let budgetSalary = 0

  function hire(ev) {
    ev.preventDefault();
    if (isValid()) {
      let inputs = Array.from(document.querySelectorAll("input"));
      let tbody = document.getElementById("tbody");
      let tr = document.createElement("tr");
      for (const input of inputs) {
        let td = document.createElement("td");
        td.textContent = input.value;
        tr.appendChild(td);
      }
			budgetSalary += Number(inputs[inputs.length - 1].value)
      let tdBtn = document.createElement("td");

      let firedBtn = document.createElement("button");
      firedBtn.classList.add("fired");
      firedBtn.textContent = "Fired";
      firedBtn.addEventListener("click", fire);

      let editBtn = document.createElement("button");
      editBtn.classList.add("edit");
      editBtn.textContent = "Edit";
      editBtn.addEventListener("click", edit);

      tdBtn.appendChild(firedBtn);
      tdBtn.appendChild(editBtn);
      tr.appendChild(tdBtn);
      tbody.appendChild(tr);

      for (const input of inputs) {
        input.value = "";
      }
			document.getElementById("sum").textContent = budgetSalary.toFixed(2)
    }
  }

  function fire(event) {
		let tr = event.target.parentElement.parentElement
		let tds = Array.from(tr.children)
		let salary = Number(tds[tds.length - 2].textContent)
		budgetSalary -= salary
		document.getElementById("sum").textContent = budgetSalary.toFixed(2)
		tr.parentElement.removeChild(tr)
  }
  function edit(event) {
		let tr = event.target.parentElement.parentElement
		let tds = Array.from(tr.children)
    let inputs = Array.from(document.querySelectorAll("input"));
		for (let index = 0; index < inputs.length; index++) {
			inputs[index].value = tds[index].textContent
		}
		let salary = Number(inputs[inputs.length - 1].value)
		budgetSalary -= salary
		document.getElementById("sum").textContent = budgetSalary.toFixed(2)
		tr.parentElement.removeChild(tr)
  }

  function isValid() {
    let inputs = Array.from(document.querySelectorAll("input"));
    let valid = true;
    for (const input of inputs) {
      if (input.value == "") {
        valid = false;
        break;
      }
    }
    return valid;
  }
}
solve();
