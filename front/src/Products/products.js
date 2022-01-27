class Products {


    constructor(){
        this.classNameActive = 'elm__btn_active';
        this.labelAdd = 'Add to Cart';
        this.labelRemove = 'Delete from Cart';
    }

    /*readTextFile(){
    var file = new XMLHttpRequest();
    file.open("GET", "front/src/Products/cat.txt", true);
    file.onreadystatechange = function ()
    {
        if(file.readyState === 4)
        {
            if(file.status === 200 || file.status == 0)
            {
                let allText = file.responseText;
                CATALOG.push({id:allText.split('\r\n')[0], name:allText.split('\r\n')[1], 
                img:allText.split('\r\n')[2], price:Number(allText.split('\r\n')[3])})
            }
        }
    }
    file.send(null);
    }*/

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
        console.log(productsStore);
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
                        ${price.toLocaleString()} RUB
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
        //localStorageUtil.clear();
    }
}

localStorageUtil.clear();
const productsPage = new Products();
//productsPage.readTextFile();
productsPage.render();
