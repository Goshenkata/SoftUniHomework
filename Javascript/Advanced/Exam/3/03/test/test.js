const { flowerShop } = require("../flowerShop");
const { it } = require("mocha");
const { assert } = require("chai");

describe("Tests..", () => {
  describe("calcPriceOfFlowers", () => {
		it("is valid", () => {
			assert.equal(flowerShop.calcPriceOfFlowers("rose", 10, 10),
			"You need $100.00 to buy rose!")
		})
	});
});
