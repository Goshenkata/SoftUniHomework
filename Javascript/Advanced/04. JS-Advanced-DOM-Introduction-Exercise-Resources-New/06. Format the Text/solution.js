function solve() {
  let input = document.getElementById("input").value;
  let arr = input.split(".").filter((x) => x);
  let out = "";
  let remainder = arr.length % 3;
  for (let index = 0; index < arr.length - remainder; index += 3) {
    out += `<p>${arr[index]}.${arr[index + 1]}.${arr[index + 2]}.</p>`;
  }
  if (remainder) {
    out += "<p>";
  }
  for (let index = 0; index < remainder; index++) {
    out += `${arr[index]}.`;
  }
  if (remainder) {
    out += "</p>";
  }
  document.getElementById("output").innerHTML = out;
}

