import {html} from '../lib.js';
import {getUserData} from '../util.js';
import { search } from '../api/data.js'


const searchTemplate = (onSubmit, data, isSubmit, isLoggedIn) => html`
        <section id="search">
          <h2>Search by Brand</h2>

          <form class="search-wrapper cf" @submit="${onSubmit}">
            <input
              id="#search-input"
              type="text"
              name="search"
              placeholder="Search here..."
              required
            />
            <button type="submit">Search</button>
          </form>


					${isSubmit ? resultTemplate(data, isLoggedIn) : null }
`

const resultTemplate = (data, isLoggedIn) => html`
          <h3>Results:</h3>

          <div id="search-container">
            <ul class="card-wrapper">
              <!-- Display a li with information about every post (if any)-->
						${data.length == 0 ?
						html` <h2>There are no results found.</h2> ` :
						html`${data.map(shoe => shoeTemplate(shoe, isLoggedIn))} ` }
            </ul>
`
const shoeTemplate = (shoe, isLoggedIn) => html`
              <li class="card">
                <img src="${shoe.imageUrl}" alt="travis" />
                <p>
                  <strong>Brand: </strong><span class="brand">${shoe.brand}</span>
                </p>
                <p>
                  <strong>Model: </strong
                  ><span class="model">${shoe.model}</span>
                </p>
                <p><strong>Value:</strong><span class="value">${shoe.value}</span>$</p>
								${isLoggedIn ?
										html` <a class="details-btn" href="/details/${shoe._id}">Details</a> ` :
										null
								}
              </li>
`

export async function searchPage(ctx) {
	const isLoggedIn = getUserData()
	console.log(isLoggedIn)
	ctx.render(searchTemplate(onSubmit, null, false, isLoggedIn))
	async function onSubmit(event) {
		event.preventDefault()
		let query = event.target.children[0].value
		const data = await search(query)
		if (query) {
			ctx.render(searchTemplate(onSubmit, data, true, isLoggedIn))
		}
	}
}
