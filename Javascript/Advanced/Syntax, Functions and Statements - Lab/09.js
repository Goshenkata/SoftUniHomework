function suareOfStart(width) {
  let output = "";
  for (let i = 0; i < width; i++) {
    for (let i = 0; i < width; i++) {
      output += "* ";
      String.prototype.trim(output);
    }
    output += "\n";
  }
  console.log(output);
}
suareOfStart(10);
