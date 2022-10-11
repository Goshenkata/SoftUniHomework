function solve() {
  let buttons = document.querySelectorAll("button");
  let textareas = document.querySelectorAll("textarea");
  buttons[0].addEventListener("click", generate);
  buttons[1].addEventListener("click", buy);

  function buy() {
    let rows = Array.from(document.querySelectorAll("tbody tr"));
    let arr = [];
    for (const row of rows) {
      if (row.querySelector('input[type="checkbox"]').checked) {
        let obj = {};
        debugger;
        obj.name = row.children[1].children[0].textContent;
        obj.price = Number(row.children[2].children[0].textContent);
        obj.decFactor = Number(row.children[3].children[0].textContent);
        arr.push(obj);
      }
    }
    let broughFurniture = "";
    let totalPrice = 0;
    let sumDecfactor = 0;
    for (const obj of arr) {
      broughFurniture += obj.name + ", ";
      totalPrice += obj.price;
      sumDecfactor += obj.decFactor;
    }
    broughFurniture = broughFurniture.substring(0, broughFurniture.length - 2);
    let out = "";
    out += `Bought furniture: ${broughFurniture}\n`;
    out += `Total price: ${totalPrice.toFixed(2)}\n`;
    out += `Average decoration factor: ${(sumDecfactor / arr.length)}`;
    textareas[1].value = out;
  }

  function generate() {
    let arr = JSON.parse(textareas[0].value);
    for (const obj of arr) {
      let tbody = document.querySelector("tbody");
      tbody.innerHTML += `<tr><td><img src="${obj.img}"></td><td><p>${obj.name}</p></td><td><p>${obj.price}</p></td><td><p>${obj.decFactor}</p></td><td><input type="checkbox"></td></tr>`;
    }
  }
}

