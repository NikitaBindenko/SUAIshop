class Products {


    constructor(){
        this.classNameActive = 'elm__btn_active';
        this.labelAdd = 'Add to Cart';
        this.labelRemove = 'Delete from Cart';
    }

    readTextFile(){
    var file = new XMLHttpRequest();
    var tmp = CATALOG;
    file.open("GET", "front/src/Products/cat.txt", false);
    file.onreadystatechange = function ()
    {
        if(file.readyState === 4)
        {
            if(file.status === 200 || file.status == 0)
            {
                let allText = file.responseText.split('\r\n');
                console.log(allText);
                for(let i = 0; i < allText.length; i += 4){
                    tmp.push({id:allText[i], name:allText[i + 1], 
                    img:allText[i + 2], price:Number(allText[i + 3])});
                }
            }
        }
    }
    file.send(null);
    return tmp;
    }

    handleSetLocationStorage(element, id){

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
        let counter = 0;

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
                        ${price.toLocaleString()} RUB
                    </span>
                    <button class="elm__btn${activeClass}" onclick="productsPage.handleSetLocationStorage(this, '${id}')">${activeText}</button>
                </li>
            `;
            ++counter;
        });

       /* for(let i = 0; i < CATALOG.length + 1; ++i ){
            console.log(CATALOG);
            let activeClass = '';
            let activeText = '';

            if (productsStore.indexOf(CATALOG[i].id) == -1 ){
                activeText =  this.labelAdd;
            }
            else{
                activeClass = ' ' + this.classNameActive;
                activeText = this.labelRemove;
            }

            htmlCatalog += `
                <li class="elm">
                    <span class="elm__name">${CATALOG[i].name}</span>
                    <img class="elm__img" src="${CATALOG[i].img}" />
                    <span class="elm__price">
                        ${CATALOG[i].price.toLocaleString()} RUB
                    </span>
                    <button class="elm__btn${activeClass}" onclick="productsPage.handleSetLocationStorage(this, '${CATALOG[i].id}')">${activeText}</button>
                </li>
            `;
        } */

        const html = `
            <ul class="products-container">
                ${htmlCatalog}
            </ul>
        `;


        ROOT_PRODUCTS.innerHTML = html;
    }
}


localStorageUtil.clear();

const productsPage = new Products();
CATALOG = productsPage.readTextFile();
productsPage.render();
