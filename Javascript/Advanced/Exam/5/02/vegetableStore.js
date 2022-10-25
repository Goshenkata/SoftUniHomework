const { log } = require("console");

class VegetableStore {
  owner;
  location;
  avalableProducts = [];
  constructor(owner, location) {
    this.owner = owner;
    this.location = location;
  }
  loadingVegetables(vegetables) {
    let out = [];
    for (const vegetable of vegetables) {
      let parts = vegetable.split(" ");
      let type = parts[0];
      let quantity = Number(parts[1]);
      let price = Number(parts[2]);
      let found = false;
      for (let index = 0; index < this.avalableProducts.length; index++) {
        const product = this.avalableProducts[index];
        if (product.type == type) {
          this.avalableProducts[index].quantity += quantity;
          if (price > product.price) {
            this.avalableProducts[index].price = price;
          }
          found = true;
          break;
        }
      }
      if (!found) {
        let obj = {
          type: type,
          quantity: quantity,
          price: price,
        };
        this.avalableProducts.push(obj);
      }
      if (!out.includes(type)) {
        out.push(type);
      }
    }
    return "Successfully added " + out.join(", ");
  }
  buyingVegetables(selectedProducts) {
    let totalPrice = 0;
    for (const product of selectedProducts) {
      let parts = product.split(" ");
      let type = parts[0];
      let quantity = Number(parts[1]);
      let available = false;
      for (let index = 0; index < this.avalableProducts.length; index++) {
        const avProduct = this.avalableProducts[index];
        if (avProduct.type == type) {
          available = true;
          if (quantity > avProduct.quantity) {
            throw new Error(
              `The quantity ${quantity} for the vegetable ${type} is not available in the store, your current bill is $${totalPrice.toFixed(
                2
              )}.`
            );
          } else {
            totalPrice += avProduct.price * quantity;
            this.avalableProducts[index].quantity -= quantity;
          }
        }
      }
      if (!available) {
        throw new Error(
          `${type} is not available in the store, your current bill is $${totalPrice.toFixed(
            2
          )}.`
        );
      }
    }
    return `Great choice! You must pay the following amount $${totalPrice.toFixed(
      2
    )}.`;
  }

  rottingVegetable(type, quantity) {
    for (let index = 0; index < this.avalableProducts.length; index++) {
      const product = this.avalableProducts[index];
      if (product.type == type) {
        if (quantity > product.quantity) {
          this.avalableProducts[index].quantity = 0;
          return `The entire quantity of the ${type} has been removed.`;
        } else {
          this.avalableProducts[index].quantity -= quantity;
          return `Some quantity of the ${type} has been removed.`;
        }
      }
    }
    throw new Error(`${type} is not available in the store.`);
  }

  revision() {
    let out = ["Available vegetables:"];
    this.avalableProducts.sort((a, b) => a.price - b.price);
    this.avalableProducts.forEach((p) =>
      out.push(`${p.type}-${p.quantity}-$${p.price}`)
    );
    out.push(
      `The owner of the store is ${this.owner}, and the location is ${this.location}.`
    );
		return out.join("\n")
  }
}

 let vegStore = new VegetableStore("Jerrie Munro", "1463 Pette Kyosheta, Sofia");
 console.log(vegStore.loadingVegetables(["Okra 2.5 3.5", "Beans 10 2.8", "Celery 5.5 2.2", "Celery 0.5 2.5"]));
 console.log(vegStore.buyingVegetables(["Okra 1"]));
 console.log(vegStore.buyingVegetables(["Beans 8", "Okra 1.5"]));
 console.log(vegStore.buyingVegetables(["Banana 1", "Beans 2"]));
