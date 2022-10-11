function solve() {
  let buttons = document.querySelectorAll("button");
  buttons[0].addEventListener("click", check);
  buttons[1].addEventListener("click", clear);
  let resultPar = document.querySelector("#check p");

  function check() {
    let inputs = Array.from(document.querySelectorAll("input[type=number]"));
    let sudoku = [];
    for (let index = 0; index < inputs.length; index += 3) {
      sudoku.push([
        Number(inputs[index].value),
        Number(inputs[index + 1].value),
        Number(inputs[index + 2].value),
      ]);
    }
    let solved = chechSolved(sudoku);
    if (solved) {
      document.querySelector("table").style.border = "2px solid green";
      resultPar.style.color = "green";
      resultPar.textContent = "You solve it! Congratulations!";
    } else {
      document.querySelector("table").style.border = "2px solid red";
      resultPar.style.color = "red";
      resultPar.textContent = "NOP! You are not done yet...";
    }
    console.log(sudoku);
  }

  function chechSolved(sudoku) {
    let solved = false;
    // rows
    for (const row of sudoku) {
      solved = checkRow(row);
    }

    //cols
    if (solved) {
      for (let i = 0; i < 3; i++) {
        let col = [];
        for (let j = 0; j < 3; j++) {
          col.push(sudoku[j][i]);
        }
        solved = checkRow(col);
      }
    }
    return solved;
  }

  function checkRow(row) {
    let hasOne = false;
    let hasTwo = false;
    let hasTree = false;
    for (const n of row) {
      if (n == 1) {
        hasOne = true;
      } else if (n == 2) {
        hasTwo = true;
      } else if (n == 3) {
        hasTree = true;
      } else {
        return false;
      }
    }
    return hasOne && hasTwo && hasTree;
  }

  function clear() {
    let inputs = Array.from(document.querySelectorAll("input[type=number]"));
    document.querySelector("table").style.borderStyle = "none";
    resultPar.textContent = "";
    for (const input of inputs) {
      input.value = "";
    }
  }
}
