const Photo = require('../models/Photo')
const mongoose = require("mongoose");
const ObjectId = mongoose.Types.ObjectId

exports.create = async (data) => {
    const createdPhoto = await Photo.create(data)
    return createdPhoto;
}
exports.getAll = async () => {
    const posts = await Photo.find().populate('owner').lean()
    return posts;
}

exports.getOne = async (id) => {
    const photo = await Photo.findById(id).populate('owner').populate('commentList.user').lean()
    return photo;
}

exports.getById = async (id) => {
    const photo = await Photo.findById(id).lean()
    return photo;
}

exports.addComment = async (id, data) => {
    const photo = await Photo.findById(id)
    photo.commentList.push(data)
    await photo.save()
}
exports.delete = async (id) => {
    return await Photo.findByIdAndDelete(id);
}

exports.editPhoto = async (id, data) => {
    const photo = await Photo.findById(id)
    for (let dataKey in data) {
        photo[dataKey] = data[dataKey]
    }
    await photo.save();
}
exports.getPhotosByUser = async (userId) => {
    const photos = await Photo.find({owner: new ObjectId(userId)}).lean()
    return photos
}
