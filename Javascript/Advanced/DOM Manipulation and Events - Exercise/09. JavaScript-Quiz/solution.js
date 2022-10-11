function solve() {
  let sections = Array.from(document.querySelectorAll("section"));
  let answers = {
    right: 0,
    wrong: 0,
  };
  for (let index = 0; index < sections.length; index++) {
    const section = sections[index];
    let lis = Array.from(section.getElementsByClassName("quiz-answer"));
    for (const li of lis) {
      li.addEventListener("click", () => {
        let answer = li.querySelector("p").textContent;
        switch (index) {
          case 0:
            if (answer == "onclick") {
              answers.right++;
            } else {
              answers.wrong++;
            }
            section.style.display = "none";
						sections[index + 1].style.display = "block"
            break;
          case 1:
            if (answer == "JSON.stringify()") {
              answers.right++;
            } else {
              answers.wrong++;
            }
            section.style.display = "none";
						sections[index + 1].style.display = "block"
            break;
          case 2:
            if (answer == "A programming API for HTML and XML documents") {
              answers.right++;
            } else {
              answers.wrong++;
            }
            section.style.display = "none";
						let results = document.getElementById("results")
						results.style.display = "block"
						let p = results.children[0].children[0]
						p.textContent = answers.right == 3 ?
							"You are recognized as top JavaScript fan!" :
							`You have ${answers.right} right answers`
            break;
        }
      });
    }
  }
}
