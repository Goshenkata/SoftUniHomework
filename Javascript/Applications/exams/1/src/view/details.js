import {html} from '../lib.js';
import { getShoeDetails } from '../api/data.js'
import { deleteShoe } from '../api/data.js'
import { getUserData } from '../util.js';

const detailsTemplate = (shoe, isOwner, onDelete) => html`
        <!-- Details page -->
        <section id="details">
          <div id="details-wrapper">
            <p id="details-title">Shoe Details</p>
            <div id="img-wrapper">
              <img src="${shoe.imageUrl}" alt="example1" />
            </div>
            <div id="info-wrapper">
              <p>Brand: <span id="details-brand">${shoe.brand}</span></p>
              <p>
                Model: <span id="details-model">${shoe.model}</span>
              </p>
              <p>Release date: <span id="details-release">${shoe.release}</span></p>
              <p>Designer: <span id="details-designer">${shoe.designer}</span></p>
              <p>Value: <span id="details-value">${shoe.value}</span></p>
            </div>

            <!--Edit and Delete are only for creator-->
						${isOwner ? html`
						<div id="action-buttons">
              <a href="/edit/${shoe._id}" id="edit-btn">Edit</a>
              <a id="delete-btn" @click="${onDelete}">Delete</a>
            </div>
							` : null
						}
          </div>
        </section>
`
export async function detailsPage(ctx) {
  const userData = getUserData();
  const shoe = await getShoeDetails(ctx.params.id);
	const isOwner = userData && shoe._ownerId == userData._id
	ctx.render(detailsTemplate(shoe, isOwner, onDelete))
	async function onDelete() {
		if (confirm('Are you sure you want to delete this item?')) {
			await deleteShoe(shoe._id)
			ctx.page.redirect('/dashboard')
		}
	}
}
