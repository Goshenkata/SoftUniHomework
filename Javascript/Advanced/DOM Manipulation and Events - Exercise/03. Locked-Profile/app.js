function lockedProfile() {
  let profiles = document.getElementsByClassName("profile");
  for (const profile of Array.from(profiles)) {
    let showButton = profile.querySelector("button");
    showButton.addEventListener("click", showMore);
  }

  function showMore(event) {
    let btn = event.target;
    let profile = btn.parentElement;
    let locked = profile.querySelector("input[value=lock]").checked;
    if (!locked) {
      let hiddenField = profile.querySelector("div");
      let display = hiddenField.style.display == "block" ? "none" : "block";
      if (display == "none") {
        btn.textContent = "Show more";
      } else {
        btn.textContent = "Hide it";
      }
      hiddenField.style.display = display;
    }
  }
}
