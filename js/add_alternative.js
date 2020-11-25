function add_alternative() {
    if (alternatives.length >= 5 || alternatives.length < 2) {
        alert("You may only enter 2-5 alternatives");
        return;
    }

    var alt_id = getRndInteger(0, 2**16);
    var alt_name = document.getElementById('alternative_text').value;
    var alt_desc = document.getElementById('alternative_description').value;
    if (alt_name && alt_desc) {
        var alt_pair = [alt_id, alt_name, alt_desc];
        alternatives.push(alt_pair);
        console.log(alternatives);

        document.getElementById('alternative_text').value = "";
        document.getElementById('alternative_description').value = "";
    } else {
        alert("Please enter a name and a description for the alterative.");
    }
}
