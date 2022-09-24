function exer(speed, area) {
	let limit = 0;
  switch (area) {
    case "motorway":
      limit = 130;
      break;
    case "interstate":
			limit = 90
			break;
		case "city":
			limit = 50
			break;
		case "residential":
			limit = 20
			break;
  }
	if (speed <= limit) {
		console.log(`Driving ${speed} km/h in a ${limit} zone`);
	} else {
		let difference = speed - limit
		let status_
		if (difference <= 20) {
			status_ = "speeding"
		} else if (difference <= 40) {
			status_ = "excessive speeding"
		} else {
			status_ = "reckless driving"
		}
		console.log(`The speed is ${difference} km/h faster than the allowed speed of ${limit} - ${status_}`);
	}
}
exer(40, 'city')
exer(21, 'residential')
