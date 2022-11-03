async function getInfo() {
  let id = Number(document.getElementById("stopId").value);
  let ul = document.getElementById("buses");
  try {
    let response = await fetch(
      `http://localhost:3030/jsonstore/bus/businfo/${id}`
    );
    const data = await response.json();
    document.getElementById("stopName").textContent = data.name;
    for (const busId in data.buses) {
      if (Object.hasOwnProperty.call(data.buses, busId)) {
        const time = data.buses[busId];
        let li = document.createElement("li");
        li.textContent = `Bus ${busId} arrives in ${time} minutes`
				ul.appendChild(li)
      }
    }
  } catch (error) {
    document.getElementById("stopName").textContent = 'Error'
	}
}
