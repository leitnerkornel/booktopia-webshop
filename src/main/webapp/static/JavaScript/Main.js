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

filterByRecommender();