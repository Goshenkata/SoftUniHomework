const mongoose = require('mongoose')

const photoSchema = new mongoose.Schema({
    name : {
        type: String,
        required: true,
        minLength: 2
    },
    image : {
        type: String,
        required: true,
        match: [/^(http:\/\/|https:\/\/)/, 'cock']
    },
    age: {
        type: Number,
        required: true,
        min: 1,
        max: 100
    },
    description : {
        type: String,
        required: true,
        minLength: [5,'longaaa'],
        maxLength: [50,'shortaa']
    },
    location : {
        type: String,
        required: true,
        minLength: [5,'longaaa'],
        maxLength: [50,'shortaa']
    },
    commentList: [
        {
            user: {
                type: mongoose.Schema.Types.ObjectId,
                ref:'User'
            },
            comment: {
                type: String
            }
        }
    ],
    owner: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    }
})

const Photo = mongoose.model('Photo', photoSchema);
module.exports = Photo;