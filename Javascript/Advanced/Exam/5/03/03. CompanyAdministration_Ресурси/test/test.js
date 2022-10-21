const { companyAdministration } = require("../companyAdministration");
const { it } = require("mocha");
const { assert, expect } = require("chai");

describe("Tests", () => {
  describe("Hiring employee", () => {
		it("hired", () => {
			assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 4), 'gosho was successfully hired for the position Programmer.')
			assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 3), 'gosho was successfully hired for the position Programmer.')
			assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 2), 'gosho is not approved for this position.')
		})
		it("invalid", () => {
			assert.throw(() => companyAdministration.hiringEmployee('gosgo', 'Test', 3), `We are not looking for workers for this position.`)
		})
	});
  describe("calculate salary", () => {
		it("is invalid", () => {
			assert.throw(() => companyAdministration.calculateSalary('100'), "Invalid hours")
			assert.throw(() => companyAdministration.calculateSalary(-100), "Invalid hours")
		})
		it("valid", () => {
			assert.equal(companyAdministration.calculateSalary(10), 150)
			assert.equal(companyAdministration.calculateSalary(200), 4000)
			assert.equal(companyAdministration.calculateSalary(161), 161 * 15 + 1000)
			assert.equal(companyAdministration.calculateSalary(160), 160 * 15)
		})
	});

  describe("calculate salary", () => {
		it("is invalid", () => {
			assert.throw(() => companyAdministration.firedEmployee('100', 1), "Invalid input")
			assert.throw(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 3), "Invalid input")
			assert.throw(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 3.2), "Invalid input")
			assert.throw(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], -2), "Invalid input")
		})
		it("valid", () => {
			assert.equal(companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 1), "Petar, George")
			assert.equal(companyAdministration.firedEmployee(["Petar", "George"], 1), "Petar")
			assert.equal(companyAdministration.firedEmployee(["Petar"], 0), [])
		})
	});
});
