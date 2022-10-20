window.addEventListener("load", solve);

function solve() {
  document.getElementById("publish-btn").addEventListener("click", publish);
	document.getElementById("clear-btn").addEventListener("click", clear)


  function publish() {
    if (valid()) {
      let postTitle = document.getElementById("post-title").value;
      let postCategory = document.getElementById("post-category").value;
      let postContent = document.getElementById("post-content").value;

      let ul = document.getElementById("review-list");
      let li = document.createElement("li");
      li.classList.add("rpost");

      let article = document.createElement("article");
      let h4 = document.createElement("h4");
      h4.textContent = postTitle;
      let pCat = document.createElement("p");
      pCat.textContent = "Category: " + postCategory;
      let pCon = document.createElement("p");
      pCon.textContent = "Content: " + postContent;

      article.appendChild(h4);
      article.appendChild(pCat);
      article.appendChild(pCon);

      let editBtn = document.createElement("button");
      editBtn.classList.add("action-btn", "edit");
      editBtn.textContent = "Edit";
      editBtn.addEventListener("click", edit);

      let approveBtn = document.createElement("button");
      approveBtn.classList.add("action-btn", "approve");
      approveBtn.textContent = "Approve";
      approveBtn.addEventListener("click", approve);

      li.appendChild(article);
      li.appendChild(editBtn);
      li.appendChild(approveBtn);
      ul.appendChild(li);

      document.getElementById("post-title").value = ""
      document.getElementById("post-category").value = "";
      document.getElementById("post-content").value = "";
    }
  }

  function edit(ev) {
    let li = ev.target.parentElement;
    let postTitle = li.children[0].children[0].textContent;
    let postCategory = li.children[0].children[1].textContent.substring(
      "Category: ".length
    );
    let postContent = li.children[0].children[2].textContent.substring(
      "Content: ".length
    );

    document.getElementById("post-title").value = postTitle;
    document.getElementById("post-category").value = postCategory;
    document.getElementById("post-content").value = postContent;
    li.parentElement.removeChild(li);
  }

  function approve(ev) {
		let li = ev.target.parentElement
		let newLi = li
		let buttons = Array.from(newLi.querySelectorAll("button"))
		for (const button of buttons) {
			li.removeChild(button)
		}
		document.getElementById("published-list").appendChild(newLi)
		li.parentElement.removeChild(ev.target.parentElement)
  }

  function valid() {
    let postTitle = document.getElementById("post-title");
    let postCategory = document.getElementById("post-category");
    let postContent = document.getElementById("post-content");
    return postTitle.value && postCategory.value && postContent.value;
  }

	function clear() {
		document.getElementById("published-list").innerHTML = ""
	}
}
