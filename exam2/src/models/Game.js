const mongoose = require('mongoose')
const {Mongoose} = require("mongoose");

const gameSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minLength: 4
    },
    image: {
        type: String,
        required: true,
        match: [/^(http:\/\/|https:\/\/)/, 'invalid url']
    },
    price: {
        type: Number,
        required: true,
        min: 0
    },
    description: {
        type: String,
        required: true,
        minLength: 10
    },
    genre: {
        type: String,
        required: true,
        minLength: 2
    },
    platform: {
        type: String,
        enum: ['PC', 'Nintendo', 'PS4', 'PS5', 'XBOX'],
        required: true,
    },
    boughtBy: [
        {
            user: {
                type: mongoose.Schema.Types.ObjectId,
                ref: 'User'
            }
        }
    ],
    owner: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    }
})

const Game = mongoose.model('Game', gameSchema)
module.exports = Game
