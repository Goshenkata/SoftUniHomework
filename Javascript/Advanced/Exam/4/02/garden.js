
class Garden {
  storage = [];
  plants = [];
  spaceAvailable;
  constructor(spaceAvailable) {
    this.spaceAvailable = spaceAvailable;
  }
  addPlant(plantName, spaceRequired) {
    if (this.spaceAvailable < spaceRequired) {
      throw new Error("Not enough space in the garden.");
    } else {
      this.plants.push({
        plantName: plantName,
        spaceRequired: spaceRequired,
        ripe: false,
        quantity: 0,
      });
      this.spaceAvailable -= spaceRequired;
      return `The ${plantName} has been successfully planted in the garden.`;
    }
  }
  ripenPlant(plantName, quantity) {
    for (let index = 0; index < this.plants.length; index++) {
      const plant = this.plants[index];
      if (plant.plantName == plantName) {
        if (plant.ripe) {
          throw new Error(`The ${plantName} is already ripe.`);
        } else {
          if (quantity <= 0) {
            throw new Error("The quantity cannot be zero or negative.");
          } else {
            this.plants[index].ripe = true;
            this.plants[index].quantity += quantity;
            if (quantity > 1) {
              return `${plant.quantity} ${plant.plantName}s have successfully ripened.`;
            } else {
              return `${plant.quantity} ${plant.plantName} has successfully ripened.`;
            }
          }
        }
      }
    }
    throw new Error(`There is no ${plantName} in the garden.`);
  }

  harvestPlant(plantName) {
    for (let index = 0; index < this.plants.length; index++) {
      const plant = this.plants[index];
      if (plant.plantName == plantName) {
        if (plant.ripe) {
          this.storage.push({
            plantName: plant.plantName,
            quantity: plant.quantity,
          });
          this.plants.splice(index, 1);
					this.spaceAvailable += plant.spaceRequired
          return `The ${plantName} has been successfully harvested.`;
        } else {
          throw new Error(
            `The ${plantName} cannot be harvested before it is ripe.`
          );
        }
      }
    }
    throw new Error(`There is no ${plantName} in the garden.`);
  }
  generateReport() {
    let out = [`The garden has ${this.spaceAvailable} free space left.`];
    let listPlants = [];
    let listStorage = [];
    this.plants
      .sort((a, b) => a.plantName.localeCompare(b.plantName))
      .forEach((p) => {
        listPlants.push(p.plantName);
      });
    out.push("Plants in the garden: " + listPlants.join(", "));
    if (this.storage.length == 0) {
      out.push("Plants in storage: The storage is empty.");
    } else {
      this.storage.forEach((s) => {
        listStorage.push(`${s.plantName} (${s.quantity})`);
      });
      out.push("Plants in storage: " + listStorage.join(", "));
    }
    return out.join("\n");
  }
}

const myGarden = new Garden(250);
console.log(myGarden.addPlant("apple", 20));
console.log(myGarden.addPlant("orange", 200));
console.log(myGarden.addPlant("raspberry", 10));
console.log(myGarden.ripenPlant("apple", 10));
console.log(myGarden.ripenPlant("orange", 1));
console.log(myGarden.harvestPlant("orange"));
console.log(myGarden.generateReport());
