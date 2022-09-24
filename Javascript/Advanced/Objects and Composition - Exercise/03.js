function exer(input) {
  let output = { model: input.model };
  const engines = {
    small: { power: 90, volume: 1800 },
    normal: { power: 120, volume: 2400 },
    monster: { power: 200, volume: 3500 },
  };
	if (input.power <= 90) {
		output.engine = engines.small
	} else if (input.power <= 120) {
		output.engine = engines.normal
	} else {
		output.engine = engines.monster
	}
	output.carriage = {
		type: input.carriage,
		color: input.color
	}
	output.wheels = new Array()
	let wheelsize = input.wheelsize % 2 == 0 ? input.wheelsize - 1 : input.wheelsize
	for (let index = 0; index < 4; index++) {
		output.wheels[index] = wheelsize
	}
  return output;
}
console.log(
  exer({
    model: "VW Golf II",
    power: 90,
    color: "blue",
    carriage: "hatchback",
    wheelsize: 14,
  })
);
