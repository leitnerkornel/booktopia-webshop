export let dataManager = {
    postData: function postData(url = "", data = {}) {
        console.log("Posted");
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(r => {return  r.json()}).then(data => console.log(`Callback ${data}`));
    },
    postDataWithCallback: function postDataWithCallback(url = "", data = {}, callback) {
        console.log("Posted");
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(r => {return  r.json()}).then(callback(data));
    }
};