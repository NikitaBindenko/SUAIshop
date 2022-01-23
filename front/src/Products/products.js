class Products {

    render() {
        let htmlCatalog = '';

        CATALOG.forEach(({ id, name, price, img }) => {
            htmlCatalog += `
                <li class="elm">
                    <span class="elm__name">${name}</span>
                    <img class="elm__img" src="${img}" />
                    <span class="elm__price">
                        👨🏻‍🚀${price.toLocaleString()} RUB
                    </span>
                    <form method "GET" action = "BinServlet">
                    <input type="hidden" name="id" value=${id} />
                    <input type="hidden" name="act" value="1" />
                    <button class="elm__btn">Добавить в корзину</button>
                    </form>
                </li>
            `;
        });

        const html = `
            <ul class="products-container">
                ${htmlCatalog}
            </ul>
        `;

        ROOT_PRODUCTS.innerHTML = html;
    }
}

const productsPage = new Products();
productsPage.render();