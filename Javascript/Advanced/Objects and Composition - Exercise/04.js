function exer(input) {
	let out = new Array()
	input.forEach(heroData => {
		let heroDataArr = heroData.split(' / ')
		let hero = {name: heroDataArr[0], level: Number(heroDataArr[1])}
		let items = heroDataArr[2].split(", ")
		hero.items = items
		out.push(hero)
	});
	console.log(JSON.stringify(out))
}
