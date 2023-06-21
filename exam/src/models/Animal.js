const mongoose = require('mongoose')

const animalSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minLength: [2, "Name should be at least 2 characters long"]
    },
    years: {
        type: Number,
        required: true,
        min: [1, "Years should be between 1 and 100"],
        max: [100, "Years should be between 1 and 100"]
    },
    kind: {
        type: String,
        required: true,
        minLength: [3, "Kind should be at least 3 characters long"]
    },
    image: {
        type: String,
        required: true,
        match: [/^(http:\/\/|https:\/\/)/, 'Invalid url']
    },
    need: {
        type: String,
        required: true,
        minLength: [3, "Need should be at least 3 and no longer than 20 characters."],
        maxLength: [20, "Need should be at least 3 and no longer than 20 characters."]
    },
    location: {
        type: String,
        required: true,
        minLength: [5, "Location should be at least 5 and no longer than 15 characters."],
        maxLength: [15, "Location should be at least 5 and no longer than 15 characters."]
    },
    description: {
        type: String,
        required: true,
        minLength: [5, "Description should be at least 5 and no longer than 50 characters."],
        maxLength: [50, "Description should be at least 5 and no longer than 50 characters."]
    },
    donations: [
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

const Animal = mongoose.model('Animal', animalSchema)
module.exports = Animal
