class footballTeam {
  clubName;
  country;
  invitedPlayers = [];
  constructor(clubName, country) {
    this.clubName = clubName;
    this.country = country;
  }
  newAdditions(footballPlayers) {
    let out = [];
    for (const player of footballPlayers) {
      let parts = player.split("/");
      let name = parts[0];
      let age = Number(parts[1]);
      let playerValue = Number(parts[2]);
      let isPresent = false;
      for (let index = 0; index < this.invitedPlayers.length; index++) {
        const invitedPlater = this.invitedPlayers[index];
        if (invitedPlater.name == name) {
          isPresent = true;
          if (playerValue > invitedPlater.playerValue) {
            this.invitedPlayers[index].playerValue = playerValue;
          }
          break;
        }
      }
      if (!isPresent) {
        this.invitedPlayers.push({
          name: name,
          age: age,
          playerValue: playerValue,
        });
      }
      if (!out.includes(name)) {
        out.push(name);
      }
    }
    return "You successfully invite " + out.join(", ") + ".";
  }

  signContract(selectedPlayer) {
    let parts = selectedPlayer.split("/");
    let name = parts[0];
    let playerOffer = Number(parts[1]);
    for (let index = 0; index < this.invitedPlayers.length; index++) {
      const invitedPlater = this.invitedPlayers[index];
      if (invitedPlater.name == name) {
        if (playerOffer < invitedPlater.playerValue) {
          let priceDifference = invitedPlater.playerValue - playerOffer;
          throw new Error(
            `The manager's offer is not enough to sign a contract with ${name}, ${priceDifference} million more are needed to sign the contract!`
          );
        } else {
          invitedPlater.playerValue = "Bought";
          return `Congratulations! You sign a contract with ${name} for ${playerOffer} million dollars.`;
        }
      }
    }
    throw new Error(`${name} is not invited to the selection list!`);
  }

  ageLimit(name, age) {
    let player = false;
    for (const invPlayer of this.invitedPlayers) {
      if (invPlayer.name == name) {
        player = invPlayer;
        break;
      }
    }
    if (player) {
      if (player.age < age) {
        let difference = age - player.age;
        if (difference < 5) {
          return `${name} will sign a contract for ${difference} years with ${this.clubName} in ${this.country}!`;
        } else {
          return `${name} will sign a full 5 years contract for ${this.clubName} in ${this.country}!`;
        }
      } else {
        return `${name} is above age limit!`;
      }
    } else {
      throw new Error(`${name} is not invited to the selection list!`);
    }
  }

  transferWindowResult() {
		let out = ["Players list:"]
		this.invitedPlayers.sort((a,b) => a.name.localeCompare(b.name))
		this.invitedPlayers.forEach(p => out.push(`Player ${p.name}-${p.playerValue}`))
		return out.join("\n")
	}
}
