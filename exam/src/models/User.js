const mongoose = require('mongoose')
const bcrypt = require('bcrypt')

const userSchema = new mongoose.Schema({
    email: {
        type: String,
        required: true,
        unique: true,
        minLength: [10, "Email should be at least 10 characters long"]
    },
    password: {
        type: String,
        required: true,
        minLength: [4, "Password should be at least 4 characters long"]
    },
})
userSchema.virtual('repeatPassword')
    .set(function(value) {
        if (this.password !== value) {
            throw new Error('password mismatch')
        }
    });

userSchema.pre('save', function () {
    const hash = bcrypt.hashSync(this.password, 10)
    this.password = hash
})

const User = mongoose.model('User', userSchema)
module.exports = User