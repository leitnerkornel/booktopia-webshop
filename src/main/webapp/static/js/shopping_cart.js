import {postData} from "./data_manager.js";

function continueToCheckout() {
    let checkoutButton = document.querySelector(".checkout-cart");
    let cartWithBook = document.querySelector(".cart-color-change");
    console.log(cartWithBook);
    checkoutButton.addEventListener("click", function () {
        console.log(cartWithBook + "after");
        if (cartWithBook == null) {
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
        postData("/shoppingCart", item);
    },
    increaseCartNumber: function () {
        let counter = document.querySelector("#lblCartCount");
        let currentValue = parseInt(counter.innerHTML);
        if (currentValue === 0) {
            let cart = document.querySelector(".items-in-cart");
            cart.querySelector(".empty-cart-text").remove();
            shoppingCart.createCartHeader(cart);
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
                    let currentItemPrice = currentItemQuantity.previousSibling;
                    let currentQuantity = parseInt(currentItemQuantity.innerHTML);
                    let defaultPrice = parseFloat(price.split(" ")[0]);
                    currentItemQuantity.innerHTML = (currentQuantity + 1).toString();
                    currentItemPrice.innerHTML = (defaultPrice * (currentQuantity + 1)).toString() + " USD";
                }
            }
        } else {
            shoppingCart.addNewItemIntoCart(cart, bookID, author, title, price);
        }
    },
    addNewItemIntoCart: function (cart, bookID, author, title, price) {
        let newDiv = document.createElement("div");
        let newCover = document.createElement("img");
        let newAuthorTitle = document.createElement("div");
        let authorP = document.createElement("p");
        let titleP = document.createElement("p");
        let newPrice = document.createElement("div");
        let newCount = document.createElement("div");

        newDiv.classList.add("item-div");
        newDiv.dataset.bookidcart = bookID;

        newCover.setAttribute("src", `/static/img/product_${bookID}.jpg`);
        newCover.classList.add("cover-cart");

        newAuthorTitle.classList.add("author-title-cart");

        authorP.innerHTML = `${author}`;
        titleP.innerHTML = `${title}`;
        newPrice.innerHTML = `${price}`;
        newCount.innerHTML = "1";

        authorP.classList.add("author-in-cart");
        titleP.classList.add("title-in-cart");
        newPrice.classList.add("price-in-cart");
        newCount.classList.add("count-in-cart");

        newAuthorTitle.appendChild(authorP);
        newAuthorTitle.appendChild(titleP);

        newDiv.appendChild(newCover);
        newDiv.appendChild(newAuthorTitle);
        newDiv.appendChild(newPrice);
        newDiv.appendChild(newCount);
        cart.appendChild(newDiv);
    },
    createCartHeader: function (cart) {
        let newDiv = document.createElement("div");
        let productHeader = document.createElement("div");
        let priceHeader = document.createElement("div");
        let quantityHeader = document.createElement("div");

        newDiv.classList.add("cart-header-container");
        productHeader.classList.add("cart-product-header");
        priceHeader.classList.add("cart-price-header");
        quantityHeader.classList.add("cart-quantity-header");

        productHeader.innerHTML = "Product";
        priceHeader.innerHTML = "Price";
        quantityHeader.innerHTML = "Quantity";

        newDiv.appendChild(productHeader);
        newDiv.appendChild(priceHeader);
        newDiv.appendChild(quantityHeader);
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
    shoppingCartMain: function () {
        continueToCheckout();
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
