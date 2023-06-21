const mongoose = require('mongoose');
const connectionStr = 'mongodb://localhost:27017/unidb';
const studentSchema = new mongoose.Schema({
name: { type: String, required: true, minlength: 3 },
age: Number
});
const Student = mongoose.model('Student', studentSchema);
mongoose.connect(connectionStr).then(() => {
new Student({ name: 'Petar', age: 21 })
.save()
.then(student => {
console.log(student._id)
});
});
