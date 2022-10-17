window.addEventListener("load", solve);

function solve() {
  let publishButton = document.getElementById("publish-btn");
  publishButton.addEventListener("click", publish);
  let postTitle = document.getElementById("post-title");
  let postCategory = document.getElementById("post-category");
  let postContent = document.getElementById("post-content");
	let clearBtn = document.getElementById("clear-btn")
	clearBtn.addEventListener("click", clear)

  function publish() {
    let title = postTitle.value;
    let category = postCategory.value;
    let content = postContent.value;
    postTitle.value = "";
    postCategory.value = "";
    postContent.value = "";

		let ul = document.getElementById("review-list")

    let li = document.createElement("li");
    li.classList += "rpost";

    let article = document.createElement("article");
    let h4 = document.createElement("h4");
    h4.textContent = title;

    let pCat = document.createElement("p");
    pCat.textContent = `Category: ${category}`;

    let pCont = document.createElement("p");
    pCont.textContent = `Content: ${content}`;

    article.appendChild(h4);
    article.appendChild(pCat);
    article.appendChild(pCont);

    let edit = document.createElement("button");
    edit.classList += "action-btn edit";
    edit.textContent = "Edit";
		edit.addEventListener("click", editFunc)
    let approve = document.createElement("button");
    approve.classList += "action-btn approve";
    approve.textContent = "Approve";
		approve.addEventListener("click", approveFunc)

    li.appendChild(article);
    li.appendChild(edit);
    li.appendChild(approve);

		ul.appendChild(li)
  }

	function editFunc(event) {
		let li = event.target.parentElement
		postTitle.value = li.querySelector("article h4").textContent
		postCategory.value = li.querySelectorAll("article p")[0].textContent.substring("Category: ".length - 1)
		postContent.value = li.querySelectorAll("article p")[1].textContent.substring("Content: ".length - 1)
		li.parentElement.removeChild(li)
	}

	function approveFunc(event) {
		let li = event.target.parentElement
		li.removeChild(li.querySelector("button"))
		li.removeChild(li.querySelector("button"))
		let uploaded = document.getElementById("published-list")
		li.parentElement.removeChild(li)
		uploaded.appendChild(li)
	}

	function clear() {
		let uploaded = document.getElementById("published-list")
		uploaded.innerHTML = ""
	}
}
