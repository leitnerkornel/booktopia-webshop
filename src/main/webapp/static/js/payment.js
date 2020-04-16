function backToShippingINfo() {
    let backButton = document.querySelector(".backBtn");
    backButton.addEventListener("click", function () {
        window.location = "/checkout";
    })
}

function finalizeOrder() {
    let payButton = document.querySelector(".payBtn");
    payButton.addEventListener("click", function () {
        alert("Jól van, ügyes vagy!");
    })
}

backToShippingINfo();
finalizeOrder();