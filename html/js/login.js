function request_login() {
    // Set loading notification
    set_loading(true);

    // Get id from url and data from page
    const urlParams = new URLSearchParams(window.location.search);
    var choiceID = urlParams.get('id');
    var username = document.getElementById("user_id").value;
    var password = document.getElementById("password").value;

    console.log("choice: " + choiceID);
    if (username) {

        // Build XHR request
        var data = {};

        data["choiceID"] = choiceID;
        data["name"] = username;
        data["password"] = password;
        
        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", login_url, true);

        xhr.send(js);

        xhr.onloadend = function () {
            console.log(xhr);
            console.log(xhr.request);
            if (xhr.readyState == XMLHttpRequest.DONE) {

                // Good response
                if (xhr.status == 200) {
                    var js = JSON.parse(xhr.responseText);
                    if (js["httpCode"] == 200) {

                        // Username and password good
                        memberID = js["member"]["memberID"];
                        firstTime = js["firstTime"];
                        console.log ("XHR:" + xhr.responseText);
                        console.log ("Memberid: " + memberID);
                        console.log("successful login");

                        // If first time, register for voting
                        if (firstTime) {

                            // Build new XHR request
                            var voting_data = {};
                            voting_data["choiceID"] = choiceID;
                            voting_data["memberID"] = memberID;

                            var voting_js = JSON.stringify(voting_data);
                            console.log("JS:" + voting_js);
                            var voting_xhr = new XMLHttpRequest();
                            voting_xhr.open("POST", set_vote_url, false);

                            voting_xhr.send(voting_js);

                            if (voting_xhr.readyState == XMLHttpRequest.DONE) {
                                
                                // Good resp
                                if (voting_xhr.status == 200) {
                                    var js = JSON.parse(voting_xhr.responseText);
                                    if (js["httpCode"] == 200) {
                                        console.log(voting_xhr);
                                        console.log("Registered");
                                    }
                                } else {
                                    alert("Something went wrong.");
                                    return;
                                }
                            }
                        }
                    
                        // Goto view choice screen
                        window.location.href = 'view_choice.html?id=' + choiceID + '&memberid=' + memberID;

                    } else {

                        // Bad username or password
                        // Undraw loading notification
                        set_loading(false);

                        alert("Choice is full or incorrect password.");
                    }

                } else {
                    // Undraw loading notification
                    set_loading(false);

                    console.log("actual:" + xhr.responseText)
                    var js = JSON.parse(xhr.responseText);
                    var err = js["response"];
                    alert (err);
                }
            }
        };
    } else {
        // Undraw loading notification
        set_loading(false);

        alert("Must enter username and password");
    }
}