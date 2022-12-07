import {html} from "../lib.js";
import {createOffer} from "../api/data.js";


const createTemplate = (onSubmit) => html`
    <section id="create">
        <div class="form">
            <h2>Create Offer</h2>
            <form class="create-form" @submit="${onSubmit}">
                <input
                        type="text"
                        name="title"
                        id="job-title"
                        placeholder="Title"
                />
                <input
                        type="text"
                        name="imageUrl"
                        id="job-logo"
                        placeholder="Company logo url"
                />
                <input
                        type="text"
                        name="category"
                        id="job-category"
                        placeholder="Category"
                />
                <textarea
                        id="job-description"
                        name="description"
                        placeholder="Description"
                        rows="4"
                        cols="50"
                ></textarea>
                <textarea
                        id="job-requirements"
                        name="requirements"
                        placeholder="Requirements"
                        rows="4"
                        cols="50"
                ></textarea>
                <input
                        type="text"
                        name="salary"
                        id="job-salary"
                        placeholder="Salary"
                />

                <button type="submit">post</button>
            </form>
        </div>
    </section>
`

export async function createPage(ctx) {
    ctx.render(createTemplate(onSubmit))

    async function onSubmit(event) {
        event.preventDefault()
        const formData = new FormData(event.target)
        let data = {
            title: formData.get('title'),
            imageUrl: formData.get('imageUrl'),
            category: formData.get('category'),
            description: formData.get('description'),
            requirements: formData.get('requirements'),
            salary: formData.get('salary')
        }
        for (const key in data) {
            if (Object.hasOwnProperty.call(data, key)) {
                const element = data[key];
                if (!element) {
                    return alert('All fields must be filled')
                }
            }
        }
        createOffer(data)
        ctx.page.redirect('/dashboard')
    }
}