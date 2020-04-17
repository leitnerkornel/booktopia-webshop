function checkForm(form) {
    let inputs = form.getElementsByTagName('input');
    for (let i = 0; i < inputs.length; i++) {
        if(inputs[i].value === ""){
            alert("Please fill all required fields");
            return false;
        }

    }
    return true;
}

function backToShippingINfo() {
    let backButton = document.querySelector(".backBtn");
    backButton.addEventListener("click", function () {
        window.location = "/checkout";
    })
}

function finalizeOrder() {
    let payButton = document.querySelector(".payBtn");
    payButton.addEventListener("click", function () {
        let form = document.querySelector("form");
        if (checkForm(form) === true){
            alert("Guadalupei Szent Szűzanya áldja meg rendelésed!");
        } else {
        }
    })
}

backToShippingINfo();
finalizeOrder();