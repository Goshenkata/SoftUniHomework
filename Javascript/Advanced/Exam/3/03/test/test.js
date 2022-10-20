const { flowerShop } = require("../flowerShop");
const { it } = require("mocha");
const { assert } = require("chai");

describe("Tests..", () => {
  describe("calcPriceOfFlowers", () => {
    it("is valid", () => {
      assert.equal(
        flowerShop.calcPriceOfFlowers("rose", 10, 10),
        "You need $100.00 to buy rose!"
      );
    });
    it("is invalid", () => {
      assert.throw(
        () => flowerShop.calcPriceOfFlowers(10, 10, 10),
        "Invalid input!"
      );
      assert.throw(
        () => flowerShop.calcPriceOfFlowers("rose", 10.1, 10),
        "Invalid input!"
      );
      assert.throw(
        () => flowerShop.calcPriceOfFlowers("rose", 10, 10.1),
        "Invalid input!"
      );
    });
  });
  describe("checkFlowersAvailable", () => {
    let gardenArr = ["Rose", "Lily", "Orchid"];
    it("is valid", () => {
      assert.equal(
        flowerShop.checkFlowersAvailable("Lily", gardenArr),
        `The Lily are available!`
      );
    });
    it("is invalid", () => {
      assert.equal(
        flowerShop.checkFlowersAvailable("Test", gardenArr),
        `The Test are sold! You need to purchase more!`
      );
    });
  });
	describe("sellFlowers", () => {
    let gardenArr = ["Rose", "Lily", "Orchid"];
    it("is valid", () => {
			assert.equal(flowerShop.sellFlowers(gardenArr, 1), "Rose / Orchid")
    });
    it("is invalid", () => {
			assert.throw(() => flowerShop.sellFlowers(gardenArr, 3), 'Invalid input!')
			assert.throw(() => flowerShop.sellFlowers(gardenArr, 1.1), 'Invalid input!')
			assert.throw(() => flowerShop.sellFlowers('test', 1), 'Invalid input!')
    });
	})
});
