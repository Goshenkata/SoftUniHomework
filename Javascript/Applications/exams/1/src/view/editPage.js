import {html} from '../lib.js';
import { getShoeDetails } from '../api/data.js'
import {editItem} from '../api/data.js'

const editTemplate = (onSubmit) => html`
        <section id="edit">
          <div class="form">
            <h2>Edit item</h2>
            <form class="edit-form" @submit="${onSubmit}">
              <input
                type="text"
                name="brand"
                id="shoe-brand"
                placeholder="Brand"
              />
              <input
                type="text"
                name="model"
                id="shoe-model"
                placeholder="Model"
              />
              <input
                type="text"
                name="imageUrl"
                id="shoe-img"
                placeholder="Image url"
              />
              <input
                type="text"
                name="release"
                id="shoe-release"
                placeholder="Release date"
              />
              <input
                type="text"
                name="designer"
                id="shoe-designer"
                placeholder="Designer"
              />
              <input
                type="text"
                name="value"
                id="shoe-value"
                placeholder="Value"
              />

              <button type="submit">post</button>
            </form>
          </div>
        </section>
`
export async function editPage(ctx) {
	ctx.render(editTemplate(onSubmit))
	const id = ctx.params.id
  const shoe = await getShoeDetails(id);
	let formData = new FormData(document.querySelector('form'))

	document.getElementById('shoe-brand').value = shoe.brand
	document.getElementById('shoe-model').value = shoe.model
	document.getElementById('shoe-img').value = shoe.imageUrl
	document.getElementById('shoe-release').value = shoe.release
	document.getElementById('shoe-designer').value = shoe.designer
	document.getElementById('shoe-value').value = shoe.value

	async function onSubmit(event) {
		event.preventDefault()
	formData = new FormData(document.querySelector('form'))
		let data = {
			brand: formData.get('brand'),
			model: formData.get('model'),
  		imageUrl: formData.get('imageUrl'),
  		release: formData.get('release'),
  		designer: formData.get('designer'),
  		value: formData.get('value')
		}
		for (const key in data) {
			if (Object.hasOwnProperty.call(data, key)) {
				const element = data[key];
				if (!element) {
					return alert('All fields must be filled')
				}
			}
		}
		editItem(data, id)
		ctx.page.redirect('/dashboard')
	}
}
