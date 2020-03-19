let filterByRecommender = function () {
    let filterRecommenderButtons = document.querySelectorAll('.drop-recommender');
    let cards = document.querySelectorAll(".card");
    for (let button of filterRecommenderButtons) {
        button.addEventListener("click", function () {
            if (button.innerHTML === "Kornel") {
                for (let card of cards) {
                    if (!card.classList.contains("Kornel")) {
                        card.classList.add("not-selected");
                    } else {
                        card.classList.remove("not-selected");
                    }
                }
            } else if (button.innerHTML === "Peti") {
                for (let card of cards) {
                    if (!card.classList.contains("Peti")) {
                        card.classList.add("not-selected");
                    } else {
                        card.classList.remove("not-selected");
                    }
                }
            } else if (button.innerHTML === "Gabor") {
                for (let card of cards) {
                    if (!card.classList.contains("Gabor")) {
                        card.classList.add("not-selected");
                    } else {
                        card.classList.remove("not-selected");
                    }
                }
            }
        })
    }
};

let filterByGenre = function () {
    let filterGenreButtons = document.querySelectorAll('.drop-genre');
    let cards = document.querySelectorAll(".card");
    for (let button of filterGenreButtons) {
        button.addEventListener("click", function () {
            if (button.innerHTML === "Thriller") {
                for (let card of cards) {
                    if (!card.classList.contains("Thriller")) {
                        card.classList.add("not-selected");
                    } else {
                        card.classList.remove("not-selected");
                    }
                }
            } else if (button.innerHTML === "Satire") {
                for (let card of cards) {
                    if (!card.classList.contains("Satire")) {
                        card.classList.add("not-selected");
                    } else {
                        card.classList.remove("not-selected");
                    }
                }
            } else if (button.innerHTML === "Fantasy") {
                for (let card of cards) {
                    if (!card.classList.contains("Gabor")) {
                        card.classList.add("not-selected");
                    } else {
                        card.classList.remove("not-selected");
                    }
                }
            } else if (button.innerHTML === "Children's book") {
                for (let card of cards) {
                    if (!card.classList.contains("Childrens")) {
                        card.classList.add("not-selected");
                    } else {
                        card.classList.remove("not-selected");
                    }
                }
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
    filterByGenre();
};

main();



