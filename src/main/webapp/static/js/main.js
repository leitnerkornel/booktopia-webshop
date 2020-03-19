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

let increaseCartCount = function () {
    let counter = document.querySelector("#lblCartCount");
    let currentValue = parseInt(counter.innerHTML);
    console.log(currentValue);
    currentValue += 1;
    counter.innerHTML = currentValue.toString();
    changeCartColor(currentValue);
};

let addToCart = function () {
    let buttons = document.querySelectorAll(".add-to-cart");
    for (let button of buttons) {
        button.addEventListener("click", increaseCartCount);
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



