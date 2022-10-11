function create(words) {
  for (const word of words) {
    let div = document.createElement("div");
    let p = document.createElement("p");
    p.style.display = "none";
    p.textContent = word;
    div.appendChild(p);
    div.addEventListener("click", () => {
      p.style.display = "block";
    });
    document.getElementById("content").appendChild(div);
  }
}

