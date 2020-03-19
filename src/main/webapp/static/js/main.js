let filterByRecommender = function () {
    let filterRecommenderButtons = document.querySelectorAll('.drop-recommender');
    for (let button of filterRecommenderButtons) {
        button.addEventListener("click", function () {
            if (button.innerHTML === "Kornel") {
                console.log("Kornel");
            }
        })
    }
};

let increaseCartCount = function (author, title, price) {
    let counter = document.querySelector("#lblCartCount");
    let currentValue = parseInt(counter.innerHTML);
    if (currentValue === 0) {
        let cart = document.querySelector(".items-in-cart");
        cart.querySelector(".empty-cart-text").remove();
    }
    currentValue += 1;
    counter.innerHTML = currentValue.toString();
    showInCart(author, title, price);
    changeCartColor(currentValue);
};

let showInCart = function (author, title, price) {
    let cart = document.querySelector(".items-in-cart");
    let newElement = document.createElement("p");
    newElement.innerHTML = `${author} - ${title} - ${price}`;
    cart.appendChild(newElement);
};

let addToCart = function () {
    let buttons = document.querySelectorAll(".add-to-cart");
    for (let button of buttons) {
        let author = button.dataset.author;
        let title = button.dataset.title;
        let price = button.dataset.price;
        button.addEventListener("click", function () {
            increaseCartCount(author, title, price);
        });
    }
};

let changeCartColor = function (value) {
    let cartIcon = document.querySelector(".fa-shopping-cart");
    if (value > 0) {
        cartIcon.classList.add('cart-color-change');
    } else {
        cartIcon.classList.remove('cart-color-change');
    }
};

const main = function () {

    addToCart();
    filterByRecommender();
};

main();



