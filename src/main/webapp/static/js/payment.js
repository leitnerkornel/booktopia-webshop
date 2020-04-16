function backToShippingINfo() {
    let backButton = document.querySelector(".backBtn");
    backButton.addEventListener("click", function () {
        window.location = "/checkout";
    })
}

function finalizeOrder() {
    let payButton = document.querySelector(".payBtn");
    payButton.addEventListener("click", function () {
        alert("Guadalupei Szent Szűzanya áldja meg rendelésed!");
    })
}

backToShippingINfo();
finalizeOrder();