import * as api from './api.js'

export const login = api.login
export const register = api.register
export const logout = api.logout

export async function getShoes() {
	return api.get('/data/shoes?sortBy=_createdOn%20desc')
}

export async function addItem(data) {
	api.post('/data/shoes', data)
}

export async function getShoeDetails(id) {
	return api.get(`/data/shoes/${id}`)
}

export async function deleteShoe(id) {
	api.del(`/data/shoes/${id}`)
}

export async function editItem(data, id) {
	api.put(`/data/shoes/${id}`, data)
}

export async function search(query) {
	return api.get(`/data/shoes?where=brand%20LIKE%20%22${query}%22`)
}
