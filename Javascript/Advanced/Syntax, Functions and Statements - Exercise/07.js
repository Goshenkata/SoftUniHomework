function exer(n, ...operations) {
  const opFunc = {
    chop: (n) => n / 2,
    dice: (n) => Math.sqrt(n),
		spice: n => n += 1,
		bake: n => n *= 3,
		fillet: n => n *= 0.8
  };
  for (const operation of operations) {
    n = opFunc[operation](n);
    console.log(n);
  }
}
exer('9', 'dice', 'spice', 'chop', 'bake', 'fillet')
