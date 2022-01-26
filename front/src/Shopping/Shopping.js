class Shopping{


    clear(){
        ROOT_SHOPPING.innerHTML = '';
    }


    render(){

        const productsStore = localStorageUtil.getProducts();
        let htmlCatalog = '';
        let totalPrice = 0;

        CATALOG.forEach(({ id, name, price, img }) => {
           if (productsStore.indexOf(id) != -1){
            htmlCatalog += `
            <div class="shopping-element">
                <div class="shopping-element__name">${name}</div>
                <div class="shopping-element__price">${price.toLocaleString()} RUB</div>
            </div>
            `;
            
            totalPrice += price;
           }
        })

        const html = `
        <div class="shopping-container">
            <div class="shopping-container__close" onclick="shoppingPage.clear()"> 
            <i class="fas fa-times"></i>
            </i>
            </div>
            <div>
                ${htmlCatalog}
            </div>
                <div class="shopping-element sum">
                <div class="shopping-element__name sum">СУММА:</div>
                <div class="shopping-element__price">${totalPrice.toLocaleString()} RUB</div>
            </div>
            <div>
                <form method="GET" action="/SUAIshop/bin" class="accept">
                <input id="inf_txt" type="adress" name="Adress" placeholder="Input your Adress">
                <input type = "hidden" name = "products" value="${localStorageUtil.getProducts()}">
                <input type ="submit" class="gradient-button" value="Оформить заказ">
                </form>
            </div>
        </div>
        `;
        ROOT_SHOPPING.innerHTML = html;
        
    }
}

const shoppingPage = new Shopping();