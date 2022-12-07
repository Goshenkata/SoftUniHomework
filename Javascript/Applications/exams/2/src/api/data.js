import * as api from './api.js'

export const login = api.login
export const register = api.register
export const logout = api.logout

export async function getOffers() {
    return api.get('/data/offers?sortBy=_createdOn%20desc')
}

export async function createOffer(data) {
    api.post('/data/offers', data)
}

export async function getOfferDetails(id) {
    return api.get(`/data/offers/${id}`)
}
export async function deleteOffer(id) {
    api.del(`/data/offers/${id}`)
}
export async function editOffer(data, id) {
    api.put(`/data/offers/${id}`, data)
}
export async function hasApplied(offerId, userId) {
    return api.get(`/data/applications?where=offerId%3D%22${offerId}%22%20and%20_ownerId%3D%22${userId}%22&count`)
}
export async function getApplications(offerId) {
    return api.get(`/data/applications?where=offerId%3D%22${offerId}%22&distinct=_ownerId&count`)
}
export async function apply(offerId) {
    api.post('/data/applications', {offerId})
}
