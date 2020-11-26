function request_login() {
    const urlParams = new URLSearchParams(window.location.search);
    var choiceID = urlParams.get('id');
    var username = document.getElementById("user_id").value;
    var password = document.getElementById("password").value;

    console.log("choice: " + choiceID);
    if (username) {
        var data = {};

        data["name"] = username;
        data["password"] = password;
        data["choiceID"] = choiceID;
    
        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", login_url, true);

        xhr.send(js);

        xhr.onloadend = function () {
            console.log(xhr);
            console.log(xhr.request);
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var js = JSON.parse(xhr.responseText);
                    memberID = js["member"]["memberID"];
                    console.log ("XHR:" + xhr.responseText);
                    console.log ("Memberid: " + memberID);
                    console.log("successful login");
                    
                    // Goto view choice screen
                    window.location.href = 'view_choice.html?id=' + choiceID + '&memberid=' + memberID;

                 } else {
                     console.log("actual:" + xhr.responseText)
                      var js = JSON.parse(xhr.responseText);
                      var err = js["response"];
                      alert (err);
                 }
            }
        };
    } else {
        alert("Must enter username and password");
    }
}