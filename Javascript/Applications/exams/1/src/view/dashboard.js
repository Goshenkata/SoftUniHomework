import {html} from '../lib.js';
import { getShoes } from '../api/data.js'
const dashboardHtml = (shoes) => html`
        <!-- Dashboard page -->
        <section id="dashboard">
          <h2>Collectibles</h2>
          <ul class="card-wrapper">
					${shoes.length == 0 ?
						html`<h2>There are no items added yet.</h2>` :
						html`${shoes.map(shoe => shoeTemplate(shoe))} ` }
          </ul>
        </section>
`
const shoeTemplate = (shoe) => html`
            <li class="card">
              <img src="${shoe.imageUrl}" alt="eminem" />
              <p>
                <strong>Brand: </strong><span class="brand">${shoe.brand}</span>
              </p>
              <p>
                <strong>Model: </strong
                ><span class="model">${shoe.model}</span>
              </p>
              <p><strong>Value:</strong><span class="value">${shoe.value}</span>$</p>
              <a class="details-btn" href="details/${shoe._id}">Details</a>
            </li>
`

export async function dashboardPage(ctx) {
	const shoes = await getShoes()
	ctx.render(dashboardHtml(shoes))
}
