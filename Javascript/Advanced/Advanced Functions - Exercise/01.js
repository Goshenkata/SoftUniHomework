function sort(arr, sort) {
  if (sort == "asc") {
    arr.sort((a,b) => a - b);
    console.log(arr);
    return arr;
  } else {
    arr.sort((a,b) => b - a);
    console.log(arr);
    return arr;
  }
}
sort([14, 7, 17, 6, 8], "desc");
