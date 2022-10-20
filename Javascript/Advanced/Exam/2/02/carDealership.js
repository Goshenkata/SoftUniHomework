class CarDealership {
  name;
  availableCars = [];
  soldCars = [];
  totalIncome = 0;
  constructor(name) {
    this.name = name;
  }
  addCar(model, horsepower, price, mileage) {
    if (model && horsepower >= 0 && price >= 0 && mileage >= 0) {
      this.availableCars.push({
        model: model,
        horsepower: horsepower,
        price: price,
        mileage: mileage,
      });
      return `New car added: ${model} - ${horsepower} HP - ${mileage.toFixed(
        2
      )} km - ${price.toFixed(2)}$`;
    } else {
      throw new Error("Invalid input!");
    }
  }

  sellCar(model, desiredMileage) {
    let desiredCar = false;
    for (const car of this.availableCars) {
      if (car.model == model) {
        desiredCar = car;
        break;
      }
    }
    if (desiredCar) {
      if (!(desiredCar.mileage <= desiredMileage)) {
        let difference = desiredCar.mileage - desiredMileage;
        if (difference <= 40000) {
          desiredCar.price *= 0.95;
        } else {
          desiredCar.price *= 0.9;
        }
      }
      this.availableCars = this.availableCars.filter((c) => c.name != model);
      this.soldCars.push(desiredCar);
      this.totalIncome += desiredCar.price;
      return `${model} was sold for ${desiredCar.price.toFixed(2)}$`;
    } else {
      throw new Error(`${model} was not found!`);
    }
  }

  currentCar() {
    if (this.availableCars.length == 0) {
      return "There are no available cars";
    }
    let out = ["-Available cars:"];
    for (const car of this.availableCars) {
      out.push(
        `---${car.model} - ${car.horsepower} HP - ${car.mileage.toFixed(
          2
        )} km - ${car.price.toFixed(2)}$`
      );
    }
    return out.join("\n");
  }
  salesReport(criteria) {
    if (criteria == "horsepower") {
      this.soldCars.sort((a, b) => b.horsepower - a.horsepower);
    } else if (criteria == "model") {
      this.soldCars.sort((a, b) => a.model.localeCompare(b.model));
    } else {
      throw new Error("Invalid criteria!");
    }
    let out = [
      `-${this.name} has a total income of ${this.totalIncome.toFixed(2)}$`,
      `-${this.soldCars.length} cars sold:`,
    ];
    for (const car of this.soldCars) {
      out.push(`---${car.model} - ${car.horsepower} HP - ${car.price.toFixed(2)}$`);
    }
    return out.join("\n");
  }
}
let dealership = new CarDealership("SoftAuto");
dealership.addCar("Toyota Corolla", 100, 3500, 190000);
dealership.addCar("Mercedes C63", 300, 29000, 187000);
dealership.addCar("Audi A3", 120, 4900, 240000);
dealership.sellCar("Toyota Corolla", 230000);
dealership.sellCar("Mercedes C63", 110000);
console.log(dealership.salesReport("horsepower"));

