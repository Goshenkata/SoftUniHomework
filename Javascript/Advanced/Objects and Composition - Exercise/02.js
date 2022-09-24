function exer(worker) {
	if (worker.dizziness) {
		let added = 0.1 * worker.weight * worker.experience
		worker.levelOfHydrated += added
		worker.dizziness = false
		return worker
	} else {
		return worker
	}
}
console.log(exer({ weight: 80, experience: 1, levelOfHydrated: 0, dizziness: true }))
