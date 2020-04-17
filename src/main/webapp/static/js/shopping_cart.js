import {dataManager} from "./data_manager.js";

function continueToCheckout() {
    let checkoutButton = document.querySelector(".checkout-cart");
    let cartWithBook = document.querySelector(".cart-color-change");
    checkoutButton.addEventListener("click", function () {
        cartWithBook = document.querySelector(".cart-color-change");
        if (cartWithBook === null) {
            alert("You have to choose a book first");
        } else {
            window.location = "/checkout";
        }
    })
}

export let shoppingCart = {
    addToCart: function (bookID, author, title, price) {
        let currentValue = shoppingCart.increaseCartNumber();
        shoppingCart.showInCart(bookID, author, title, price);
        shoppingCart.changeCartColor(currentValue);
        let item = {id: bookID, author: author, title: title, price: price};
        dataManager.postData("/shoppingCart", item);
    },
    increaseCartNumber: function () {
        let counter = document.querySelector("#lblCartCount");
        let currentValue = parseInt(counter.innerHTML);
        if (currentValue === 0) {
            let cart = document.querySelector(".items-in-cart");
            if (cart.querySelector(".empty-cart-text") !== null) {
                cart.querySelector(".empty-cart-text").remove();
                shoppingCart.createCartHeader(cart);
            }
        }
        currentValue += 1;
        counter.innerHTML = currentValue.toString();
        return currentValue;
    },
    showInCart: function (bookID, author, title, price) {
        let bookIDsInCart = [];
        let cart = document.querySelector(".items-in-cart");
        let cartItems = cart.querySelectorAll(".item-div");
        for (let cartItem of cartItems) {
            bookIDsInCart.push(parseInt(cartItem.dataset.bookidcart));
        }
        if (bookIDsInCart.includes(parseInt(bookID))) {
            for (let item of cartItems) {
                if (item.dataset.bookidcart === bookID) {
                    let currentItemQuantity = item.querySelector(".count-in-cart");
                    let currentItemSubtotal = item.querySelector(".subtotal-in-cart");
                    console.log(currentItemSubtotal);
                    let currentQuantity = parseInt(currentItemQuantity.innerHTML);
                    let defaultPrice = parseFloat(price.split(" ")[0]);
                    currentItemQuantity.innerHTML = (currentQuantity + 1).toString();
                    currentItemSubtotal.innerHTML = (defaultPrice * (currentQuantity + 1)).toString() + " USD";
                }
            }
        } else {
            shoppingCart.addNewItemIntoCart(cart, bookID, author, title, price);
        }
    },
    addNewItemIntoCart: function (cart, bookID, author, title, price) {
        let cartIds = [];
        let cartItems = document.querySelectorAll(".cart-delete-button");
        for (let cartItem of cartItems) {
            console.log(cartItem.dataset.cartidcart);
            cartIds.push(parseInt(cartItem.dataset.cartidcart));
            console.log(cartItem.dataset.cartidcart);
        }
        let newCartId =Math.max(...cartIds) + 1;

        let newDiv = document.createElement("div");
        let newCover = document.createElement("img");
        let newAuthorTitle = document.createElement("div");
        let authorP = document.createElement("p");
        let titleP = document.createElement("p");
        let newPrice = document.createElement("div");
        let newCount = document.createElement("div");
        let newSubTotal = document.createElement("div");
        let newButtons = document.createElement("div");

        let increaseButton = document.createElement("button");
        let decreaseButton = document.createElement("button");
        let removeRowButton = document.createElement("button");

        newDiv.classList.add("item-div");
        newDiv.dataset.bookidcart = bookID;

        newCover.setAttribute("src", `/static/img/product_${bookID}.jpg`);
        newCover.classList.add("cover-cart");

        newAuthorTitle.classList.add("author-title-cart");
        newButtons.classList.add("cart-action-buttons-container");

        authorP.innerHTML = `${author}`;
        titleP.innerHTML = `${title}`;
        newPrice.innerHTML = `${price}`;
        newCount.innerHTML = "1";
        newSubTotal.innerHTML = `${price}`;

        increaseButton.classList.add("cart-increase-button");
        decreaseButton.classList.add("cart-decrease-button");
        removeRowButton.classList.add("cart-delete-button");

        increaseButton.innerHTML = "+";
        decreaseButton.innerHTML = "-";
        removeRowButton.innerHTML = "x";

        removeRowButton.dataset.cartidcart = newCartId.toString();

        authorP.classList.add("author-in-cart");
        titleP.classList.add("title-in-cart");
        newPrice.classList.add("price-in-cart");
        newCount.classList.add("count-in-cart");
        newSubTotal.classList.add("subtotal-in-cart");

        removeRowButton.addEventListener('click', function () {
            let item = {id: bookID, author: author, title: title, price: price};
            dataManager.postData("/deleteOneEntryFromShoppingCart", item);
            removeRowButton.parentElement.parentElement.remove();
        });

        newAuthorTitle.appendChild(authorP);
        newAuthorTitle.appendChild(titleP);

        newButtons.appendChild(increaseButton);
        newButtons.appendChild(decreaseButton);
        newButtons.appendChild(removeRowButton);

        newDiv.appendChild(newCover);
        newDiv.appendChild(newAuthorTitle);
        newDiv.appendChild(newPrice);
        newDiv.appendChild(newCount);
        newDiv.appendChild(newSubTotal);
        newDiv.appendChild(newButtons);
        cart.appendChild(newDiv);
    },
    createCartHeader: function (cart) {
        let newDiv = document.createElement("div");
        let productHeader = document.createElement("div");
        let priceHeader = document.createElement("div");
        let quantityHeader = document.createElement("div");
        let subtotalHeader = document.createElement("div");

        newDiv.classList.add("cart-header-container");
        productHeader.classList.add("cart-product-header");
        priceHeader.classList.add("cart-price-header");
        quantityHeader.classList.add("cart-quantity-header");
        subtotalHeader.classList.add("cart-subtotal-header");

        productHeader.innerHTML = "Product";
        priceHeader.innerHTML = "Price";
        quantityHeader.innerHTML = "Quantity";
        subtotalHeader.innerHTML = "Subtotal";

        newDiv.appendChild(productHeader);
        newDiv.appendChild(priceHeader);
        newDiv.appendChild(quantityHeader);
        newDiv.appendChild(subtotalHeader);
        cart.appendChild(newDiv);
    },
    changeCartColor: function (value) {
        let cartIcon = document.querySelector(".fa-shopping-cart");
        if (value > 0) {
            cartIcon.classList.add('cart-color-change');
        } else {
            cartIcon.classList.remove('cart-color-change');
        }
    },
    setCartColorAtStart: function () {
        let counter = document.querySelector("#lblCartCount");
        let currentValue = parseInt(counter.innerHTML);
        shoppingCart.changeCartColor(currentValue);
    },
    deleteEntryFromCart: function () {
        let deleteButtons = document.querySelectorAll(".cart-delete-button");
        for (let deleteButton of deleteButtons) {
            deleteButton.addEventListener('click', function () {
                let item = {id: deleteButton.dataset.cartidcart};
                dataManager.postData("/deleteOneEntryFromShoppingCart", item);
                deleteButton.parentElement.parentElement.remove();
            });
        }
    },
    shoppingCartMain: function () {
        continueToCheckout();
        shoppingCart.setCartColorAtStart();
        shoppingCart.deleteEntryFromCart();
        let buttons = document.querySelectorAll(".add-to-cart");
        for (let button of buttons) {
            let bookID = button.dataset.bookid;
            let author = button.dataset.author;
            let title = button.dataset.title;
            let price = button.dataset.price;
            button.addEventListener("click", function () {
                shoppingCart.addToCart(bookID, author, title, price);
            });
        }
    }
};
