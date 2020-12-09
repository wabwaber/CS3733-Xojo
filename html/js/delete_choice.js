'use strict';

function delete_choices(date) {
    console.log("Deleting choices");
    
    for (const choice of js["list"]) {
        if (choice.date <= date) {
            console.log("Deleting choice: " + choice.id)
            delete choice;
        }
    }
}

function delete_choice(id) {
    console.log("Deleting choice")
    
    for (const choice of js["list"]) {
        if (choice.id == id) {
            console.log("Deleting choice: " + choice.id)
            delete choice;
        }
    }
}
