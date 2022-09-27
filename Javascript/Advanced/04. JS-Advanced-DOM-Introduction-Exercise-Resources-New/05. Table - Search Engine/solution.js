function solve() {
  document.querySelector("#searchBtn").addEventListener("click", onClick);

  function onClick() {
    let rows = document.querySelectorAll("tbody tr");
    let search = document.getElementById("searchField");
    let selected = document.getElementsByClassName("select");

    for (let row of selected) {
      row.classList.remove("select")
    }

		for (let row of rows) {
			tdloop: for (let td of Array.of(row.children)) {
        if (td.textContent.includes(search.value)) {
          row.classList.add("select");
					break tdloop
        }
      }
    }
    search.value = "";
  }
}
