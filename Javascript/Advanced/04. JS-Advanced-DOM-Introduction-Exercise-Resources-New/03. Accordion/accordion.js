function toggle() {
  let button = document.getElementsByClassName("button")[0];
  let div = document.getElementById("extra");
  if (button.innerText.toUpperCase() == "MORE") {
    div.style.display = "block";
    button.innerText = "Less";
  } else {
    div.style.display = "none";
    button.innerText = "More";
  }
}

