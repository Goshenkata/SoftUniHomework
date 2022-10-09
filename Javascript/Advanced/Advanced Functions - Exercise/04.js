function solution() {
  let stock = {
    protein: 0,
    carbohydrate: 0,
    fat: 0,
    flavour: 0,
  };

  let ingridients = ["protein", "carbohydrate", "fat", "flavour"];

  let recepes = {
    apple: {
      protein: 0,
      carbohydrate: 1,
      fat: 0,
      flavour: 2,
    },
    lemonade: {
      protein: 0,
      carbohydrate: 10,
      fat: 0,
      flavour: 20,
    },
    burger: {
      protein: 0,
      carbohydrate: 5,
      fat: 7,
      flavour: 3,
    },
    eggs: {
      protein: 5,
      carbohydrate: 0,
      fat: 1,
      flavour: 1,
    },
    turkey: {
      protein: 10,
      carbohydrate: 10,
      fat: 10,
      flavour: 10,
    },
  };

  function prepare(recepe, quantity) {
    let canCook = true;
		let missing = ""
    for (const ing of ingridients) {
      let toSubsctract = recepes[recepe][ing] * quantity;
      if (stock[ing] - toSubsctract < 0) {
        canCook = false;
				missing = ing
				break
      }
    }
    if (canCook) {
    for (const ing of ingridients) {
      let toSubsctract = recepes[recepe][ing] * quantity;
			stock[ing] -= toSubsctract
    }
      return "Success";
    } else {
        return `Error: not enough ${missing} in stock`;
		}
  }

  return function (operation) {
    let arr = operation.split(" ");
    switch (arr[0]) {
      case "restock":
        stock[arr[1]] += Number(arr[2]);
        return "Success";
      case "prepare":
        let recepe = arr[1];
        let quantity = Number(arr[2]);
        return prepare(recepe, quantity);
      case "report":
        return `protein=${stock.protein} carbohydrate=${stock.carbohydrate} fat=${stock.fat} flavour=${stock.flavour}`;
    }
  };
}
let manager = solution();
console.log(manager("prepare turkey 1"));
console.log(manager("restock protein 10"));
console.log(manager("prepare turkey 1"));
console.log(manager("restock carbohydrate 10"));
console.log(manager("prepare turkey 1"));
console.log(manager("restock fat 10"));
console.log(manager("prepare turkey 1"));
console.log(manager("restock flavour 10"));
console.log(manager("prepare turkey 1"));
console.log(manager("report"));
