window.addEventListener("load", solve);

function solve() {
  document.getElementById("form-btn").addEventListener("click", publish);
  function publish() {
    if (valid()) {
      let firstName = document.getElementById("first-name");
      let lastName = document.getElementById("last-name");
      let age = document.getElementById("age");
      let storyTitle = document.getElementById("story-title");
      let genre = document.getElementById("genre");
      let story = document.getElementById("story");

      let ul = document.getElementById("preview-list");
      let li = document.createElement("li");
      li.classList.add("story-info");
      let article = document.createElement("article");
      let h4Name = document.createElement("h4");
      h4Name.textContent = `Name: ${firstName.value} ${lastName.value}`;
      let pAge = document.createElement("p");
      pAge.textContent = `Age: ${age.value}`;
      let pTitle = document.createElement("p");
      pTitle.textContent = `Title: ${storyTitle.value}`;
      let pGenre = document.createElement("p");
      pGenre.textContent = `Genre: ${genre.value}`;
      let pStory = document.createElement("p");
      pStory.textContent = story.value;

      article.appendChild(h4Name);
      article.appendChild(pAge);
      article.appendChild(pTitle);
      article.appendChild(pGenre);
      article.appendChild(pStory);
      li.appendChild(article);

      let saveBtn = document.createElement("button");
      saveBtn.classList.add("save-btn");
      saveBtn.textContent = "Save Story";
      saveBtn.addEventListener("click", save);

      let editBtn = document.createElement("button");
      editBtn.classList.add("edit-btn");
      editBtn.textContent = "Edit Story";
      editBtn.addEventListener("click", edit);

      let deleteBtn = document.createElement("button");
      deleteBtn.classList.add("delete-btn");
      deleteBtn.textContent = "Delete Story";
      deleteBtn.addEventListener("click", del);

      li.appendChild(saveBtn);
      li.appendChild(editBtn);
      li.appendChild(deleteBtn);

      ul.appendChild(li);

      document.getElementById("form-btn").disabled = true;
      clear();
    }
  }

  function save() {
    let main = document.getElementById("main");
    main.innerHTML = "";
    let h1 = document.createElement("h1");
    h1.textContent = "Your scary story is saved!";
    main.appendChild(h1);
  }
  function edit(event) {
    let li = event.target.parentElement;
    let article = Array.from(li.children)[0];

    let firstName = document.getElementById("first-name");
    let lastName = document.getElementById("last-name");
    let age = document.getElementById("age");
    let storyTitle = document.getElementById("story-title");
    let genre = document.getElementById("genre");
    let story = document.getElementById("story");

    let data = Array.from(article.children);
    let names = data[0].textContent.substring("Name: ".length).split(" ");
    firstName.value = names[0];
    lastName.value = names[1];
    age.value = Number(data[1].textContent.substring("Age: ".length));
    storyTitle.value = data[2].textContent.substring("Title: ".length);
    genre.value = data[3].textContent.substring("Genre: ".length);
    story.value = data[4].textContent;
    document.getElementById("form-btn").disabled = false;
    li.parentElement.removeChild(li);
  }
  function del(event) {
    document.getElementById("form-btn").disabled = false;
    let li = event.target.parentElement;
    li.parentElement.removeChild(li);
  }

  function clear() {
    document.getElementById("first-name").value = "";
    document.getElementById("last-name").value = "";
    document.getElementById("age").value = "";
    document.getElementById("story-title").value = "";
    document.getElementById("story").value = "";
  }

  function valid() {
    let firstName = document.getElementById("first-name").value;
    let lastName = document.getElementById("last-name").value;
    let age = document.getElementById("age").value;
    let storyTitle = document.getElementById("story-title").value;
    let story = document.getElementById("story").value;
    return firstName && lastName && age && storyTitle && story;
  }
}
