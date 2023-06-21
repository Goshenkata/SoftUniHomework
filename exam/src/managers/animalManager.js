const Animal = require('../models/Animal')

exports.getAll = async () => {
    const animals = await Animal.find().lean();
    return animals
}
exports.create = async (data) => {
    await Animal.create(data)
}

exports.getAnimal = async (id) => {
    const animal = await Animal.findById(id).populate('donations.user').lean()
    return animal
}

exports.editAnimal = async (animalId, animalData) => {
    let animal = await Animal.findById(animalId)
    for (let dataKey in animalData) {
        animal[dataKey] = animalData[dataKey]
    }
    await animal.save()
}

exports.deleteAnimal = async (animalId) => {
    await Animal.findByIdAndDelete(animalId)
}
exports.donate = async (userId,animalId) => {
    const animal = await Animal.findById(animalId)
    animal.donations.push(userId)
    await animal.save()
}
exports.searchAnimals = async (location) => {
    let animals = await Animal.find().lean()
    if (location) {
        animals = animals.filter(animal => animal.location.toLowerCase().includes(location.toLowerCase()));
    }
    return animals
}
