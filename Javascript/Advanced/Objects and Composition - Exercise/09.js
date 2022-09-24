function createSortedList() {
  return {
    data: [],
    add: function(element) {
      this.data.push(element);
			this.data.sort()
    },
    remove: function(index) {
      if (this.data[index]) {
        this.data.splice(index, index);
				this.data.sort()
      }
    },
    get: function(index) {
      if (this.data[index]) {
        return this.data[index];
      }
    },
    size: function() {
      return data.size + 1;
    },
  };
}
let list = createSortedList();
list.add(5);
list.add(6);
list.add(7);
console.log(list.get(1));
list.remove(1);
console.log(list.get(1));
