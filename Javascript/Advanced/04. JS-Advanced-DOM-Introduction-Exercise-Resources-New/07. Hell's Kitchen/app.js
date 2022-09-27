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
        let workerSalarty = parts[1].split(", ");
        let salarySum = 0;
        for (let workerData of workerSalarty) {
          workerData = workerData.split(" ");
          salarySum += Number(workerData[1]);
          let workerObj = {
            name: workerData[0],
            salary: Number(workerData[1]),
          };
          restObject.workers.push(workerObj);
        }
        restObject.averageSalary = salarySum / restObject.workers.length;
        parsed.push(restObject);
      }
      return parsed;
    }
    let data = parse();
    console.log(data);
  }
}

