const { bookSelection } = require("../bookSelection");
const { it } = require("mocha");
const { assert, expect } = require("chai");

describe("Tests...", () => {
  describe("isGenreSuitable", () => {
    it("suitable", () => {
      assert.equal(
        bookSelection.isGenreSuitable("Horror", 18),
        "Those books are suitable"
      );
      assert.equal(
        bookSelection.isGenreSuitable("Thriller", 18),
        `Those books are suitable`
      );
      assert.equal(
        bookSelection.isGenreSuitable("Test", 10),
        `Those books are suitable`
      );
    });
    it("unsuitable", () => {
      assert.equal(
        bookSelection.isGenreSuitable("Horror", 10),
        `Books with Horror genre are not suitable for kids at 10 age`
      );
      assert.equal(
        bookSelection.isGenreSuitable("Thriller", 10),
        `Books with Thriller genre are not suitable for kids at 10 age`
      );
    });
  });

  describe("isItAffordable", () => {
    it("is invalid", () => {
      assert.throw(
        () => bookSelection.isItAffordable("100", 100),
        "Invalid input"
      );
      assert.throw(
        () => bookSelection.isItAffordable(100, "100"),
        "Invalid input"
      );
    });
    it("invalid", () => {
      assert.equal(
        bookSelection.isItAffordable(15, 20),
        `Book bought. You have 5$ left`
      );
      assert.equal(
        bookSelection.isItAffordable(15, 10),
        `You don't have enough money`
      );
    });
  });

  describe("suitableTitles", () => {
    it("is invalid", () => {
      assert.throw(
        () => bookSelection.suitableTitles("100", "Thriller"),
        "Invalid input"
      );
      assert.throw(
        () =>
          bookSelection.suitableTitles(
            [{ title: "The Da Vinci Code", genre: "Thriller" }],
            100
          ),
        "Invalid input"
      );
    });
    it("is valid", () => {
      assert.equal(
        bookSelection
          .suitableTitles(
            [{ title: "The Da Vinci Code", genre: "Thriller" }],
            "Thriller"
          )
          .join(", "),
        "The Da Vinci Code"
      );
      assert.equal(
        bookSelection.suitableTitles(
          [{ title: "The Da Vinci Code", genre: "Thriller" }],
          "Test"
        ).length,
        [].length
      );
    });
  });
});
