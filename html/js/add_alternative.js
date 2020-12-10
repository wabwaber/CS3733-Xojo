function add_alternative() {
    if (alternatives.length >= 5) {
        alert("You may only enter 5 alternatives");
        return;
    }

    var alt_desc = document.getElementById('alternative_description').value;

    if (alt_desc) {
        alternatives.push(alt_desc);
        console.log(alternatives);

        ReactDOM.render(
            React.createElement(AltConstruction, { }),
            document.getElementById('alt_list')
        )

        document.getElementById('alternative_description').value = "";
    } else {
        alert("Please enter a description for the alternative.");
    }
}