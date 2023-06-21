const mongoose = require('mongoose')
const bcrypt = require('bcrypt')

const userSchema = new mongoose.Schema({
    username: {
        type: String,
        required: true,
        minLength: 4
    },
    password: {
        type: String,
        required: true,
        minLength: 3
    },
    email: {
        type: String,
        required: true,
        unique: true,
        minLength: 10
    }
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