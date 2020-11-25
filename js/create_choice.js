var choice_id = getRndInteger(0, 2**16);
var alternatives = new Array();

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min) ) + min;
}

function request_choice() {
    var choice_name = document.getElementById("choice_text").value;
    var choice_desc = document.getElementById("choice_description").value;

    if (choice_name && choice_desc) {
        if (alternatives.length >= 2) {
            var data = {};
            data["choiceID"] = choice_id;
            data["choiceName"] = choice_name;
            data["choiceDesc"] = choice_desc;

            var js = JSON.stringify(data);
            console.log("JS:" + js);
            var xhr = new XMLHttpRequest();
            // xhr.open("POST", create_url, true);

            // xhr.send(js);
        } else {
            alert("You must enter at least 2 alternatives");
        }

    } else {
        alert("You must enter a name and a description for the Choice");
    }
    

}
