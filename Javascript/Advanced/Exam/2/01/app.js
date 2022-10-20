function solve() {
  let addBtn = document.getElementById("add");
  let resetBtn = document.getElementById("reset");

  addBtn.addEventListener("click", addMail);
  resetBtn.addEventListener("click", cleanInput);

  function addMail(event) {
    event.preventDefault();
    let recipientName = document.getElementById("recipientName");
    let title = document.getElementById("title");
    let message = document.getElementById("message");

    if (isValid()) {
      let ul = document.getElementById("list");
      let li = document.createElement("li");

      let h4Title = document.createElement("h4");
      h4Title.textContent = `Title: ${title.value}`;
      let h4Recipient = document.createElement("h4");
      h4Recipient.textContent = `Recipient Name: ${recipientName.value}`;
      let span = document.createElement("span");
      span.textContent = message.value;

      let div = document.createElement("div");
      div.id = "list-action";
      let send = document.createElement("button");
      send.type = "submit";
      send.id = "send";
      send.textContent = "Send";
      send.addEventListener("click", sendFunc);
      let del = document.createElement("button");
      del.type = "submit";
      del.id = "delete";
      del.textContent = "Delete";
      del.addEventListener("click", delFunc);

      div.appendChild(send);
      div.appendChild(del);

      li.appendChild(h4Title);
      li.appendChild(h4Recipient);
      li.appendChild(span);
      li.appendChild(div);
      ul.appendChild(li);

      recipientName.value = "";
      title.value = "";
      message.value = "";
    }
  }

  function sendFunc(ev) {
    ev.preventDefault();
    let liParent = ev.target.parentElement.parentElement;
    let to =
      "To: " +
      liParent.children[1].textContent.substring("Recipient Name: ".length);
    let title = liParent.children[0].textContent;
    let ul = document.querySelector(".sent-list");
    let li = document.createElement("li");
    let spanTo = document.createElement("span");
    spanTo.textContent = to;
    let spanTitle = document.createElement("span");
    spanTitle.textContent = title;
    let div = document.createElement("div");
    div.classList.add("btn");
    let btn = document.createElement("button");
    btn.type = "submit";
    btn.classList.add("delete");
    btn.textContent = "Delete";
    btn.addEventListener("click", delFunc);
    div.appendChild(btn);
    li.appendChild(spanTo);
    li.appendChild(spanTitle);
    li.appendChild(div);
    ul.appendChild(li);
    debugger;
    liParent.parentElement.removeChild(liParent);
  }

  function delFunc(ev) {
    ev.preventDefault();
    let liParent = ev.target.parentElement.parentElement;
    let to;
    let title;
    if (liParent.parentElement.id == "list") {
      //list of mails
			title = liParent.children[0].textContent
      to = "To: " + liParent.children[1].textContent.substring("Recipient Name: ".length);
    } else {
			to = liParent.children[0].textContent
			title = liParent.children[1].textContent
      //sent list
    }
		let ul = document.querySelector(".delete-list")
		let li = document.createElement("li")
		let spanTo = document.createElement("span")
		spanTo.textContent = to
		let spanTitle = document.createElement("span")
		spanTitle.textContent = title
		li.appendChild(spanTo)
		li.appendChild(spanTitle)
		ul.appendChild(li)
		liParent.parentElement.removeChild(liParent)
  }

  function isValid() {
    return (
      document.getElementById("recipientName").value &&
      document.getElementById("title").value &&
      document.getElementById("message").value
    );
  }

  function cleanInput(event) {
    event.preventDefault();
    document.getElementById("recipientName").value = "";
    document.getElementById("title").value = "";
    document.getElementById("message").value = "";
  }
}
solve();

