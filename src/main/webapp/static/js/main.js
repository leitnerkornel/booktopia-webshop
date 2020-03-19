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
};

let addToCart = function () {
    let buttons = document.querySelectorAll(".add-to-cart");
    for (let button of buttons) {
        button.addEventListener("click", increaseCartCount);
    }
};


const main = function () {
    addToCart();
    filterByRecommender();
};

main();



