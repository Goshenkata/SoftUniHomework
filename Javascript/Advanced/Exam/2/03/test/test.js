const { rentCar } = require("../rentCar");
const { it } = require("mocha");
const {assert} = require("chai");
describe("Tests...", () => {
  describe("searchCar()", () => {
		let shop = ["Volkswagen", "BMW", "Audi"]
		let model = "BMW"
		it("is valid", () => {
			assert.equal(rentCar.searchCar(shop, model), "There is 1 car of model BMW in the catalog!")
		})
		it("is not found", () => {
			assert.throw( () => rentCar.searchCar(shop, "Test"), "There are no such models in the catalog!")
		})
		it("is invalid", () => {
			assert.throw( () => rentCar.searchCar(shop, 1), "Invalid input!")
			assert.throw( () => rentCar.searchCar(1, model), "Invalid input!")
		})
  });
	describe("calculatePriceOfCar()", () => {
		it("is valid", () => {
			assert.equal(rentCar.calculatePriceOfCar("Toyota", 2), "You choose Toyota and it will cost $80!")
		})
		it("is unavalable", () => {
			assert.throw(() => rentCar.calculatePriceOfCar("Test", 2), "No such model in the catalog!")
		})
		it("is invalid", () => {
			assert.throw(() => rentCar.calculatePriceOfCar("Test", 2.1), "Invalid input")
			assert.throw(() => rentCar.calculatePriceOfCar(2, 2), "Invalid input")
		})
	})
	describe("checkBudget()", () => {
		it("is valid", () => {
			assert.equal(rentCar.checkBudget(10, 10, 101), "You rent a car!")
			assert.equal(rentCar.checkBudget(10, 10, 100), "You rent a car!")
			assert.equal(rentCar.checkBudget(10, 10, 99), "You need a bigger budget!")
		})
		it("is invalid", () => {
			assert.throw(() => rentCar.checkBudget("", 1, 1), "Invalid input")
			assert.throw(() => rentCar.checkBudget(1, "", 1), "Invalid input")
			assert.throw(() => rentCar.checkBudget(1, 1, ""), "Invalid input")
		})

	})
});
