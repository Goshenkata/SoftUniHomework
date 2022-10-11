function attachEventsListeners() {
  let buttons = document.querySelectorAll("input[type=button]");
  for (const button of buttons) {
    button.addEventListener("click", calculate);
  }

  function calculate(event) {
    let timeInput =
      event.target.parentElement.querySelector("input[type=text]");
    let timeUnit = timeInput.id;
    console.log(timeInput);
    let time = Number(timeInput.value);
    let days = getDays(timeUnit, time);
    draw(days);
  }

  function getDays(timeUnit, time) {
    switch (timeUnit) {
      case "days":
        return time;
      case "hours":
        return time / 24;
      case "minutes":
        return time / 1440;
      case "seconds":
        return time / 86400;
    }
  }

  function draw(days) {
    let inputs = document.querySelectorAll("input[type=text]");
    for (const input of inputs) {
			let id = input.parentElement.querySelector("input[type=text]").id
      switch (id) {
        case "days":
          input.value = days
					break
        case "hours":
          input.value = days * 24
					break
        case "minutes":
          input.value = days * 1440
					break
        case "seconds":
          input.value = days * 86400;
					break
      }
    }
  }
}
