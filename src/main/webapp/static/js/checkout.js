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
        let form = document.querySelector("form");
        /*if (checkForm(form)){
            console.log("Redirect payment")
        } else {
            console.log("Not redirect to payment")
        }*/
    })
}

backToMain();
toPayment();