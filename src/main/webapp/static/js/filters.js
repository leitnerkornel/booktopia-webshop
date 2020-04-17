export let filters = {
    filterByOption: function () {
        let filterRecommenderButtons = document.querySelectorAll('.dropdown-item');
        let cards = document.querySelectorAll(".card");
        for (let button of filterRecommenderButtons) {
            let recommenderName = button.innerHTML;
            button.addEventListener("click", function () {
                filters.selectByOrderOption(recommenderName, cards)
            });
        }
    },
    selectByOrderOption: function (optionName, cards) {
        for (let card of cards) {
            if (filters.isValidOrderOption(optionName)) {
                if (!card.classList.contains(optionName)) {
                    card.parentElement.classList.add("not-selected");
                } else {
                    card.parentElement.classList.remove("not-selected");
                }
            } else {
                card.parentElement.classList.remove("not-selected");
            }
        }
    },
    isValidOrderOption: function (orderOption) {
        const recommenders = new Set(['Gabor', 'Kornel', 'Peti']);
        const genres = new Set(["Thriller", "Satire", "Fantasy", "Childrens"]);
        return recommenders.has(orderOption) || genres.has(orderOption);
    }
};


