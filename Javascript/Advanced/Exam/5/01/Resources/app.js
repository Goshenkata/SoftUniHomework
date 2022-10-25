function solve() {
  document.querySelector("form button").addEventListener("click", send);
	document.querySelector(".clear-btn").addEventListener("click", clear)
  function send(event) {
    event.preventDefault();
    if (valid()) {
      let typeProduct = document.getElementById("type-product");
      let description = document.getElementById("description");
      let clientName = document.getElementById("client-name");
      let clientPhone = document.getElementById("client-phone");

      let receivedOrders = document.getElementById("received-orders");
      let div = document.createElement("div");
      div.classList.add("container");
      let h2 = document.createElement("h2");
      h2.textContent = `Product type for repair: ${typeProduct.value}`;
      let h3 = document.createElement("h3");
      h3.textContent = `Client information: ${clientName.value}, ${clientPhone.value}`;
      let h4 = document.createElement("h4");
      h4.textContent = `Description of the problem: ${description.value}`;
      let startBtn = document.createElement("button");
      startBtn.classList.add("start-btn");
      startBtn.textContent = "Start repair";
      startBtn.addEventListener("click", start);

      let finishBtn = document.createElement("button");
      finishBtn.classList.add("finish-btn");
      finishBtn.textContent = "Finish repair";
      finishBtn.addEventListener("click", finish);
			finishBtn.disabled = true

      div.appendChild(h2);
      div.appendChild(h3);
      div.appendChild(h4);
      div.appendChild(startBtn);
      div.appendChild(finishBtn);
      receivedOrders.appendChild(div);

      typeProduct.value = "";
      description.value = "";
      clientName.value = "";
      clientPhone.value = "";
    }
  }

  function start(event) {
    let startBtn = event.target
		let finishBtn = startBtn.parentElement.querySelector('.finish-btn')
		startBtn.disabled = true
		finishBtn.disabled = false
  }

  function finish(event) {
    let div = event.target.parentElement
		let buttons = Array.from(div.querySelectorAll("button"))
		for (const button of buttons) {
			div.removeChild(button)
		}
		document.getElementById("completed-orders").appendChild(div)
  }

  function valid() {
    let typeProduct = document.getElementById("type-product").value;
    let description = document.getElementById("description").value;
    let clientName = document.getElementById("client-name").value;
    let clientPhone = document.getElementById("client-phone").value;
    return typeProduct && description && clientName && clientPhone;
  }

	function clear() {
		let section = document.getElementById("completed-orders")
		let children = Array.from(section.children)
		for (let index = 3; index < children.length; index++) {
			section.removeChild(children[index])
		}
	}
}
solve();

