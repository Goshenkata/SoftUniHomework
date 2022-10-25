const { companyAdministration } = require("../companyAdministration");
const { it } = require("mocha");
const { assert, expect } = require("chai");

describe("Tests …", function() {
    describe("hiringEmployee", function() {
        it("hired", function() {
            assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 3), "gosho was successfully hired for the position Programmer.")
            assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 4), "gosho was successfully hired for the position Programmer.")
            assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 20), "gosho was successfully hired for the position Programmer.")
        });
        it("not hired", function() {
            assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 2), "gosho is not approved for this position.")
            assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 1), "gosho is not approved for this position.")
            assert.equal(companyAdministration.hiringEmployee('gosho', 'Programmer', 0), "gosho is not approved for this position.")
        });
        it("invalid", function() {
            assert.throw(() => companyAdministration.hiringEmployee('gosho','Test', 2), "We are not looking for workers for this position.")
        });
     });
    describe("calculateSalary", function() {
        it("valid no bunus", function() {
            assert.equal(companyAdministration.calculateSalary(0), 0)
            assert.equal(companyAdministration.calculateSalary(1), 15)
            assert.equal(companyAdministration.calculateSalary(10), 150)
            assert.equal(companyAdministration.calculateSalary(160), 160 * 15)
        });
        it("valid bonus", function() {
            assert.equal(companyAdministration.calculateSalary(161), 161 * 15 + 1000)
            assert.equal(companyAdministration.calculateSalary(200), 200 * 15 + 1000)
        });
        it("invalid", function() {
            assert.throw(() => companyAdministration.calculateSalary({}))
            assert.throw(() => companyAdministration.calculateSalary('aa'))
            assert.throw(() => companyAdministration.calculateSalary(-1))
        });
     });
    describe("firedEmployee", function() {
        it("valid", function() {
					assert.equal(companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 0), "Ivan, George")
					assert.equal(companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 1), "Petar, George")
					assert.equal(companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 2), "Petar, Ivan")
					assert.equal(companyAdministration.firedEmployee(["Petar", "George"], 0), "George")
					assert.equal(companyAdministration.firedEmployee(["Petar", "George"], 1), "Petar")
					assert.equal(companyAdministration.firedEmployee(["Petar"], 0), "")
        });
        it("invalid", function() {
					assert.throw(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 3), "Invalid input")
					assert.throw(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 4), "Invalid input")
					assert.throw(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], -1), "Invalid input")
					assert.throw(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 1.1), "Invalid input")
					assert.throw(() => companyAdministration.firedEmployee(12, 'aa'), "Invalid input")
					assert.throw(() => companyAdministration.firedEmployee([], 0), "Invalid input")
        });
     });
});
