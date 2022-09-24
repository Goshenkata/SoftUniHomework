function exer(steps, footLength, studentSpeed) {
  function pad(num) {
    num = "" + num;
    while (num.length < 2) num = "0" + num;
    return num;
  }
  studentSpeed = studentSpeed * (5 / 18);
  let distance = steps * footLength;
  let seconds = distance / studentSpeed;
  let breaks = Math.floor(distance / 500);
  seconds += breaks * 60;
  let hours = Math.floor(seconds / 3600);
  seconds -= hours * 3600;
  let mins = Math.floor(seconds / 60);
  seconds -= mins * 60;
	seconds = Math.ceil(seconds)
  hours = pad(hours);
  mins = pad(mins);
  seconds = pad(seconds);
  console.log(`${hours}:${mins}:${seconds}`);
}
exer(2564, 0.70, 5.5)
