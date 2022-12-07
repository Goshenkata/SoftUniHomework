import {html} from '../lib.js';
import { addItem } from '../api/data.js';

const addTemplate = (onSubmit) => html`
        <!-- Create Page (Only for logged-in users) -->
        <section id="create">
          <div class="form">
            <h2>Add item</h2>
            <form class="create-form" @submit=${onSubmit}>
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

export function addPage(ctx) {
	ctx.render(addTemplate(onSubmit))
	async function onSubmit(event) {
		event.preventDefault()
		const formData = new FormData(event.target)
		let data = {
			brand: formData.get('brand').trim(),
			model: formData.get('model').trim(),
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
		addItem(data)
		ctx.page.redirect('/dashboard')
	}
}
