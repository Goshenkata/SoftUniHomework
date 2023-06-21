const User = require('../models/User')
const bcrypt = require('bcrypt')
const jwt = require('../lib/jwt')
const { SECRET } = require('../config/config')

exports.login = async (username, password) => {
    const user = await User.findOne({username})
    if (!user) {
        throw new Error('Invalid user or password')
    }
    const isValid = bcrypt.compareSync(password, user.password)
    if (!isValid) {
        throw new Error('Invalid user or password')
    }
    const token = await generateToken(user)
    return token
}
exports.register = async (data) => {
    const user = await User.findOne({username: data.username})
    if (user) {
        throw new Error('Username already exists')
    }
    const createdUser = await User.create(data)
    const token = generateToken(createdUser)
    return token
}

async function generateToken(user) {
    const payload = {
        _id: user._id,
        username: user.username,
        email: user.email
    }
    const token = await jwt.sign(payload, SECRET, {expiresIn: '2d'});
    return token
}