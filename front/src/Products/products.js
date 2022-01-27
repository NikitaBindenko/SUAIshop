class Products {

    constructor(){
        this.classNameActive = 'elm__btn_active';
        this.labelAdd = 'Добавить в корзину';
        this.labelRemove = 'Удалить из корзины';
    }

    handleSetLocationStorage(element, id){
      console.log(element);
      const {push, products} = localStorageUtil.putProducts(id);
      
      if(push){
          element.classList.add(this.classNameActive);
          element.innerHTML = this.labelRemove;
      }
      else{
        element.classList.remove(this.classNameActive);
        element.innerHTML = this.labelAdd;
      }
    }

    render() {
        const productsStore = localStorageUtil.getProducts();
        let htmlCatalog = '';

        CATALOG.forEach(({ id, name, price, img }) => {
            let activeClass = '';
            let activeText = '';

            if (productsStore.indexOf(id) == -1 ){
                activeText =  this.labelAdd;
            }
            else{
                activeClass = ' ' + this.classNameActive;
                activeText = this.labelRemove;
            }

            htmlCatalog += `
                <li class="elm">
                    <span class="elm__name">${name}</span>
                    <img class="elm__img" src="${img}" />
                    <span class="elm__price">
                        👨🏻‍🚀${price.toLocaleString()} RUB
                    </span>
                    <button class="elm__btn${activeClass}" onclick="productsPage.handleSetLocationStorage(this, '${id}')">${activeText}</button>
                </li>
            `;
        });

        const html = `
            <ul class="products-container">
                ${htmlCatalog}
            </ul>
        `;

        ROOT_PRODUCTS.innerHTML = html;

        localStorageUtil.clear();
    }
}

const productsPage = new Products();
productsPage.render();
