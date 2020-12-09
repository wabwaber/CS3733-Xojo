var alternatives = new Array();
var choice_id;

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min) ) + min;
}

function request_choice() {

    // Get information from page 
    var choice_name = document.getElementById("choice_text").value;
    var choice_desc = document.getElementById("choice_description").value;
    var max_members = document.getElementById("num_mem_text").value;

    // Validate member ammount
    if (parseInt(max_members) != NaN) {
        max_members = parseInt(max_members);
    } else {
        alert("Please enter a number for max members");
        return;
    }

    // Begin XHR Request
    if (choice_name && choice_desc && max_members) {
        if (alternatives.length >= 2) {
            var data = {};
            data["choiceName"] = choice_name;
            data["choiceDesc"] = choice_desc;
            data["maxMembers"] = max_members;

            var js = JSON.stringify(data);
            console.log("JS:" + js);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", create_url, true);

            xhr.send(js);

            // Wait for response on new choice
            xhr.onloadend = function () {
                console.log(xhr);
                console.log(xhr.request);
                if (xhr.readyState == XMLHttpRequest.DONE) {

                    // Good response
                    if (xhr.status == 200) {
                        console.log ("XHR:" + xhr.responseText);
                        
                        // Retrieve choice ID
                        var resp = JSON.parse(xhr.responseText);
                        choice_id = resp["choice"]["choiceID"];
                        
                        // Send alternatives (not async)
                        for (const alternative of alternatives) {
                            var alt_data = {};
                            alt_data["choiceID"] = choice_id;
                            alt_data["alternativeDesc"] = alternative;

                            var alt_js = JSON.stringify(alt_data);
                            console.log("JS:" + alt_js);
                            var alt_xhr = new XMLHttpRequest();
                            alt_xhr.open("POST", add_alt_url, false);

                            alt_xhr.send(alt_js);

                            if (alt_xhr.readyState == XMLHttpRequest.DONE) {
                            
                                // Good response
                                if (alt_xhr.status == 200) {
                                    console.log ("XHR:" + alt_xhr.responseText);
                                    console.log("Alternative added");
                                } else {
                                    alert("Something went wrong.");
                                    return;
                                }
                            } 
                        }
                    } else {
                        console.log("actual:" + xhr.responseText)
                        var js = JSON.parse(xhr.responseText);
                        var err = js["response"];
                        alert (err);
                        return;
                    }
                }
                
                // All requests were successful, travel to participate page
                window.location.href = 'user_login.html?id=' + choice_id;
            };
        } else {
            alert("You must enter at least 2 alternatives");
            return;
        }
    } else {
        alert("You must enter a name and a description for the Choice");
        return;
    }
}
