const { assert } = require("chai");
const { it } = require("mocha");
const { carService } = require("../03. Car Service_Resources");

describe("Test carService", () => {
  describe("isItExpensive", () => {
    it("is expensive", () => {
      assert.equal(
        carService.isItExpensive("Engine"),
        "The issue with the car is more severe and it will cost more money"
      );
      assert.equal(
        carService.isItExpensive("Transmission"),
        "The issue with the car is more severe and it will cost more money"
      );
    });
    it("is cheap", () => {
      assert.equal(
        carService.isItExpensive("totaled beyond salvation"),
        "The overall price will be a bit cheaper"
      );
    });
  });
  describe("discount", () => {
    it("is valid", () => {
      for (let index = 3; index <= 7; index++) {
        assert.equal(
          carService.discount(index, 100),
          "Discount applied! You saved 15$"
        );
      }
      assert.equal(
        carService.discount(8, 100),
        "Discount applied! You saved 30$"
      );
      assert.equal(
        carService.discount(9, 100),
        "Discount applied! You saved 30$"
      );
      assert.equal(carService.discount(1, 100), "You cannot apply a discount");
      assert.equal(carService.discount(2, 100), "You cannot apply a discount");
    });
    it("is invalid", () => {
      assert.throw(() => carService.discount("test", 100), "Invalid input");
      assert.throw(() => carService.discount(1, "test"), "Invalid input");
    });
  });
  describe("partsToBuy", () => {
    let neededParts = ["blowoff valve", "injectors"];
    let partsCatalogue = [
      { part: "blowoff valve", price: 145 },
      { part: "coil springs", price: 230 },
    ];
    it("is valid", () => {
      assert.equal(carService.partsToBuy([], neededParts), 0);
      assert.equal(carService.partsToBuy(partsCatalogue, neededParts), 145);
    });
    it("is invalid", () => {
      assert.throw(
        () => carService.partsToBuy("yea", neededParts),
        "Invalid input"
      );
      assert.throw(
        () => carService.partsToBuy(partsCatalogue, "yea"),
        "Invalid input"
      );
    });
  });
});
