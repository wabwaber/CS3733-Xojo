function request_login() {
    var username = document.getElementById("user_id").value;
    var password = document.getElementById("password").value;

    if (username && password) {
        var data = {};

        data["username"] = username;
        data["password"] = password;

        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        // xhr.open("POST", login_url, true);

        // xhr.send(js);

        // xhr.onloadend = function () {
        //     console.log(xhr);
        //     console.log(xhr.request);
        //     if (xhr.readyState == XMLHttpRequest.DONE) {
        //          if (xhr.status == 200) {
        //           console.log ("XHR:" + xhr.responseText);
        //           // Respond to success
        //          } else {
        //              console.log("actual:" + xhr.responseText)
        //               var js = JSON.parse(xhr.responseText);
        //               var err = js["response"];
        //               alert (err);
        //          }
        //     }
        // };
    } else {
        alert("Must enter username and password");
    }
}