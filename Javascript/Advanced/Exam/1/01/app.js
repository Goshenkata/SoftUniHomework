window.addEventListener("load", solve);

function solve() {
  let make = document.getElementById("make");
  let model = document.getElementById("model");
  let year = document.getElementById("year");
  let fuel = document.getElementById("fuel");
  let originalCost = document.getElementById("original-cost");
  let sellingPrice = document.getElementById("selling-price");
	let profit = 0

  let publishBtn = document.getElementById("publish");
  publishBtn.type = "button";
  publishBtn.addEventListener("click", publish);

  function valid() {
    let fieldsEmpty =
      make.value &&
      model.value &&
      year.value &&
      originalCost.value &&
      sellingPrice.value;
    if (!fieldsEmpty) {
      return false;
    } else {
			let sp = Number(sellingPrice.value)
			let oc = Number(originalCost.value);
			let y =  Number(year.value)

      return sp > 0 && oc > 0 && sp >= oc && y >= 1990 && y <= 2022 }
  }

  function publish() {
    let values = {
      make: make.value,
      model: model.value,
      year: Number(year.value),
      fuel: fuel.value,
      originalCost: Number(originalCost.value),
      sellingPrice: Number(sellingPrice.value),
    };

    if (valid()) {
      let td = document.getElementById("table-body");
      let tr = document.createElement("tr");
      tr.classList = "row";
      for (const key in values) {
        if (Object.hasOwnProperty.call(values, key)) {
          const element = values[key];
          let td = document.createElement("td");
          td.textContent = element;
          tr.appendChild(td);
        }
      }
      let tdBtns = document.createElement("td");
      let editBtn = document.createElement("button");
      let sellBtn = document.createElement("button");
      editBtn.classList = "action-btn edit";
      sellBtn.classList = "action-btn buy";
      editBtn.textContent = "Edit";
      sellBtn.textContent = "Sell";

      sellBtn.addEventListener("click", sell);
      editBtn.addEventListener("click", edit);

      tdBtns.appendChild(editBtn);
      tdBtns.appendChild(sellBtn);
      tr.appendChild(tdBtns);
      td.appendChild(tr);
      clear();
    }
  }

  function edit(event) {
    let tr = event.target.parentElement.parentElement;
    let tdArr = Array.from(tr.children);
    make.value = tdArr[0].textContent;
    model.value = tdArr[1].textContent;
    year.value = tdArr[2].textContent;
    fuel.value = tdArr[3].textContent;
    originalCost.value = tdArr[4].textContent;
    sellingPrice.value = tdArr[5].textContent;
		tr.parentElement.removeChild(tr)
  }

  function sell(event) {
    let ul = document.getElementById("cars-list");
		let tr = event.target.parentElement.parentElement
    let tdArr = Array.from(tr.children);
    let li = document.createElement("li");
    li.classList = "each-list";

    let values = {
      makeModel: `${tdArr[0].textContent} ${tdArr[1].textContent}`,
      year: Number(tdArr[2].textContent),
      difference: Number(tdArr[5].textContent) - Number(tdArr[4].textContent),
    };

    for (const key in values) {
      if (Object.hasOwnProperty.call(values, key)) {
        const element = values[key];
        let span = document.createElement("span");
        span.textContent = element;
        li.appendChild(span);
      }
    }
    ul.appendChild(li);
		profit += values.difference
		document.getElementById("profit").textContent = profit.toFixed(2)
		tr.parentElement.removeChild(tr)
  }

  function clear() {
    make.value = "";
    model.value = "";
    year.value = "";
    fuel.value = "";
    originalCost.value = "";
    sellingPrice.value = "";
  }
}
