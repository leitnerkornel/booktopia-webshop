function backToMain() {
    let backButton = document.querySelector(".backBtn");
    backButton.addEventListener("click", function () {
        window.location = "/";
    })
}

function toPayment() {
    let payButton = document.querySelector(".payBtn");
    payButton.addEventListener("click", function () {
        window.location = "/payment";
    })
}

backToMain();
toPayment();