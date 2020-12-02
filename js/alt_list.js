'use strict';

class AltList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { };
  }

  render() {
    var data = {};
    var alt_str = "";

    const urlParams = new URLSearchParams(window.location.search);
    data["choiceID"] = urlParams.get('id');

    var js = JSON.stringify(data);
    console.log("JS:" + js);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", list_alt_url, false);
    xhr.send(js);

    console.log(xhr);
    if (xhr.readyState == XMLHttpRequest.DONE) {
        if (xhr.status == 200) {
            var js = JSON.parse(xhr.responseText);
            console.log("XHR:" + xhr.responseText);
            console.log(js["list"]);
            var i = 1;
            
            for (const alt of js["list"]) {
                console.log(alt["description"]);
                alt_str = alt_str + "\n\n " + i + ". " + alt["description"];
                i = i + 1;
            }

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
        'Alternatives:' + alt_str
    );
  }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.alt_list')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(AltList, { commentID: commentID }),
      domContainer
    );
  });