function solve() {
  document.querySelector("#btnSend").addEventListener("click", onClick);

  function onClick() {
    function parse() {
      let input = document.querySelector("textarea").value;
      let arr = JSON.parse(input);
      let parsed = [];
      for (const data of arr) {
        let parts = data.split(" - ");
        let name = parts[0];
        let restObject = {
          name: name,
          workers: [],
        };

        for (let index = 0; index < parsed.length; index++) {
          const res = parsed[index];
          if (res.name == name) {
            parsed.splice(index);
            restObject = res;
            break;
          }
        }

        let workerSalarty = parts[1].split(", ");
        for (let workerData of workerSalarty) {
          workerData = workerData.split(" ");
          let sal = Number(workerData[1]);
          let workerObj = {
            name: workerData[0],
            salary: sal,
          };
          restObject.workers.push(workerObj);
        }
        parsed.push(restObject);
      }
      return parsed;
    }

    let data = parse();
    debugger;

    data.sort((x, y) => {
      if (x.averagesalary > y.averagesalary) {
        return -1;
      } else {
        return 1;
      }
    });
    let bestRest = data[0];
    let maxSalary = Number.MIN_SAFE_INTEGER;
    let salarySum = 0;
    bestRest.workers.forEach((w) => {
      if (w.salary > maxSalary) {
        maxSalary = w.salary;
      }
      salarySum += w.salary;
    });
		let avgSal = salarySum / bestRest.workers.length
    let bestRestElement = document.querySelector("#bestRestaurant p");
    bestRestElement.textContent = `Name: ${
      bestRest.name
    } Average Salary: ${avgSal.toFixed(2)} Best Salary: ${maxSalary.toFixed(
      2
    )}`;

    let out = "";
    let works = bestRest.workers;
    works.sort((x, y) => {
      if (x.salary == y.salary) {
        return 0;
      }
      if (x.salary > y.salary) {
        return -1;
      } else {
        return 1;
      }
    });
    for (const worker of works) {
      out += `Name: ${worker.name} With Salary: ${worker.salary} `;
    }
    document.querySelector("#workers p").textContent = out;
  }
}
