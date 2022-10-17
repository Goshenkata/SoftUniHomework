class SmartHike {
  goals = {};
  listOfHikes = [];
  resources = 100;
  username;
  constructor(username) {
    this.username = username;
  }

  addGoal(peak, altitute) {
    if (this.goals.hasOwnProperty(peak)) {
      return `${peak} has already been added to your goals`;
    } else {
      this.goals[peak] = altitute;
      return `You have successfully added a new goal - ${peak}`;
    }
  }
  hike(peak, time, difficultyLevel) {
    if (this.goals.hasOwnProperty(peak)) {
      if (this.resources <= 0) {
        throw new Error("You don't have enough resources to start the hike");
      } else {
        let difference = this.resources - time * 10;
        if (difference < 0) {
          return "You don't have enough resources to complete the hike";
        } else {
          this.resources -= time * 10;
          this.listOfHikes.push({
            peak: peak,
            time: time,
            difficultyLevel: difficultyLevel,
          });
        }
				return `You hiked ${peak} peak for ${time} hours and you have ${this.resources}% resources left`
      }
    } else {
      throw new Error(`${peak} is not in your current goals`);
    }
  }

  rest(time) {
    this.resources += time * 10;
    if (this.resources >= 100) {
      this.resources = 100;
      return `Your resources are fully recharged. Time for hiking!`;
    } else {
      return `You have rested for ${time} hours and gained ${
        time * 10
      }% resources`;
    }
  }

  showRecord(criteria) {
    if (this.listOfHikes) {
      if (criteria == "all") {
				let out = ["All hiking records:"]
				for (const hike of this.listOfHikes) {
					out.push(`${this.username} hiked ${hike.peak} for ${hike.time} hours`)
				}
				return out.join("\n")
      } else {
        let hikes = this.listOfHikes.filter(
          (h) => h.difficultyLevel == criteria
        );
				if (hikes.length > 0) {
					let best = hikes.sort((a,b) => a.time - b.time)[0]
					return `${this.username}'s best ${criteria} hike is ${best.peak} peak, for ${best.time} hours`
				} else {
					return `${this.username} has not done any ${criteria} hiking yet`
				}
      }
    } else {
      return `${this.username} has not done any hiking yet`;
    }
  }
}
const user = new SmartHike('Vili');
user.addGoal('Musala', 2925);
user.hike('Musala', 8, 'hard');
console.log(user.showRecord('easy'));
user.addGoal('Vihren', 2914);
user.hike('Vihren', 4, 'hard');
console.log(user.showRecord('hard'));
user.addGoal('Rui', 1706);
user.hike('Rui', 3, 'easy');
console.log(user.showRecord('all'));
