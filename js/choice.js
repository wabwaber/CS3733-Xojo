'use strict';

class Choice extends React.Component {
  constructor(props) {
    super(props);
    this.state = { };
  }


  render() {
    var data = {};
    var choice_name = "";
    var choice_desc = "";

    const urlParams = new URLSearchParams(window.location.search);
    data["choiceID"] = urlParams.get('id');

    var js = JSON.stringify(data);
    console.log("JS:" + js);

    choice_name = "Test Choice";
    choice_desc = "Test desc";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", get_choice_url, false);
    xhr.send(js);

    console.log(xhr);
    if (xhr.readyState == XMLHttpRequest.DONE) {
        if (xhr.status == 200) {
            var js = JSON.parse(xhr.responseText);
            console.log("XHR:" + xhr.responseText);
            console.log(js["list"]);

            choice_name = js["choice"]["name"];
            choice_desc = js["choice"]["description"];
        } else {
            console.log("actual:" + xhr.responseText)
            var js = JSON.parse(xhr.responseText);
            var err = js["response"];
            alert (err);
        }
    }

    console.log("Sending request");
    return React.createElement(
        'div',
        { },
        React.createElement(
            'h2',
            {},
            'Choice: ' + choice_name
        ),
        React.createElement(
            'p',
            {},
            'Description: ' + choice_desc
        )
    );
  }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.choice')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(Choice, { commentID: commentID }),
      domContainer
    );
  });