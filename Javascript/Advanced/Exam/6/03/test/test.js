const { chooseYourCar } = require("../chooseYourCar");
const { it } = require("mocha");
const { assert, expect } = require("chai");

describe("Tests …", function () {
  describe("choosingType", function () {
    it("invalid year", function () {
      assert.throw(() => chooseYourCar.choosingType("Sedan", "black", 1899));
      assert.throw(() => chooseYourCar.choosingType("Sedan", "black", 2023));
      assert.throw(() => chooseYourCar.choosingType("Test", "black", 2023));
      assert.throw(() => chooseYourCar.choosingType("Test", "black", 1899));
    });
    it("not Sedan", function () {
      assert.throw(() => chooseYourCar.choosingType("Test", "black", 2010));
      assert.throw(() => chooseYourCar.choosingType("Test", "black", 2011));
      assert.throw(() => chooseYourCar.choosingType("Test", "black", 2009));
    });
    it("valid", function () {
      assert.equal(
        chooseYourCar.choosingType("Sedan", "black", 2010),
        "This black Sedan meets the requirements, that you have."
      );
      assert.equal(
        chooseYourCar.choosingType("Sedan", "black", 2011),
        "This black Sedan meets the requirements, that you have."
      );
      assert.equal(
        chooseYourCar.choosingType("Sedan", "black", 2022),
        "This black Sedan meets the requirements, that you have."
      );
      assert.equal(
        chooseYourCar.choosingType("Sedan", "red", 2022),
        "This red Sedan meets the requirements, that you have."
      );
    });
    it("too old", function () {
      assert.equal(
        chooseYourCar.choosingType("Sedan", "black", 2009),
        "This Sedan is too old for you, especially with that black color."
      );
      assert.equal(
        chooseYourCar.choosingType("Sedan", "red", 2009),
        "This Sedan is too old for you, especially with that red color."
      );
      assert.equal(
        chooseYourCar.choosingType("Sedan", "red", 1900),
        "This Sedan is too old for you, especially with that red color."
      );
      assert.equal(
        chooseYourCar.choosingType("Sedan", "red", 1901),
        "This Sedan is too old for you, especially with that red color."
      );
    });
  });
  describe("brandName", function () {
    it("invalid information", function () {
			assert.throw(() => chooseYourCar.brandName(1, 1), "Invalid Information!")
			assert.throw(() => chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], 1.1), "Invalid Information!")
			assert.throw(() => chooseYourCar.brandName(':D', ':D'), "Invalid Information!")
			assert.throw(() => chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], ':D'), "Invalid Information!")
			assert.throw(() => chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], 3), "Invalid Information!")
			assert.throw(() => chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], 4), "Invalid Information!")
			assert.throw(() => chooseYourCar.brandName([], 0), "Invalid Information!")

    });
    it("valid information", function () {
			assert.equal(chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], 0), "Toyota, Peugeot")
			assert.equal(chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], 1), "BMW, Peugeot")
			assert.equal(chooseYourCar.brandName(["BMW", "Toyota", "Peugeot"], 2), "BMW, Toyota")
			assert.equal(chooseYourCar.brandName(["BMW", "Toyota"], 0), "Toyota")
			assert.equal(chooseYourCar.brandName(["BMW", "Toyota"], 1), "BMW")
			assert.equal(chooseYourCar.brandName(["BMW"], 0), "")
    });
});
  describe("carFuelConsumption", function () {
    it("invalid", function () {
			assert.throw(() => chooseYourCar.carFuelConsumption(0, 100), "Invalid Information!")
			assert.throw(() => chooseYourCar.carFuelConsumption(0, 100), "Invalid Information!")
			assert.throw(() => chooseYourCar.carFuelConsumption(100, 0), "Invalid Information!")
			assert.throw(() => chooseYourCar.carFuelConsumption(100, -1), "Invalid Information!")
			assert.throw(() => chooseYourCar.carFuelConsumption('ya', 100), "Invalid Information!")
			assert.throw(() => chooseYourCar.carFuelConsumption('ya', 'ya'), "Invalid Information!")
			assert.throw(() => chooseYourCar.carFuelConsumption(100, 'ya'), "Invalid Information!")
    });
    it("enought", function () {
			assert.equal(chooseYourCar.carFuelConsumption(100, 1), "The car is efficient enough, it burns 1.00 liters/100 km.")
			assert.equal(chooseYourCar.carFuelConsumption(15, 1), "The car is efficient enough, it burns 6.67 liters/100 km.")
			assert.equal(chooseYourCar.carFuelConsumption(1, 7/100), "The car is efficient enough, it burns 7.00 liters/100 km.")
    });
    it("not enought", function () {
			assert.equal(chooseYourCar.carFuelConsumption(1, 1), "The car burns too much fuel - 100.00 liters!")
			assert.equal(chooseYourCar.carFuelConsumption(14, 1), "The car burns too much fuel - 7.14 liters!")
			assert.equal(chooseYourCar.carFuelConsumption(1, 8/100), "The car burns too much fuel - 8.00 liters!")
    });
	});
});
