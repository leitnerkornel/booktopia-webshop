import { shoppingCart } from "./shopping_cart.js";

let filterByOption = function () {
    let filterRecommenderButtons = document.querySelectorAll('.dropdown-item');
    let cards = document.querySelectorAll(".card");
    for (let button of filterRecommenderButtons) {
        let recommenderName = button.innerHTML;
        button.addEventListener("click", function () {
            selectByOrderOption(recommenderName, cards)
        });
    }
};

let selectByOrderOption = function (optionName, cards) {
    for (let card of cards) {
        if (isValidOrderOption(optionName)) {
            if (!card.classList.contains(optionName)) {
                //card.classList.add("not-selected");
                card.parentElement.classList.add("not-selected");
            } else {
                card.parentElement.classList.remove("not-selected");
            }
        } else {
            card.parentElement.classList.remove("not-selected");
        }
    }
};

let isValidOrderOption = function (orderOption) {
    const recommenders = new Set(['Gabor', 'Kornel', 'Peti']);
    const genres = new Set(["Thriller", "Satire", "Fantasy", "Childrens"]);
    return recommenders.has(orderOption) || genres.has(orderOption);
};

const main = function () {
    shoppingCart.shoppingCartMain();
    filterByOption();
};

main();



