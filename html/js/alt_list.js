'use strict';

class AltList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { alternatives: [] };
  }

  render() {
    var data = {};
    var alt_str;

    const urlParams = new URLSearchParams(window.location.search);
    console.log(urlParams.get('id'));
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
            var i = 0;
            
            for (const alt of js["list"]) {
                console.log("Val of i: " + i);
                console.log(alt["description"]);
                this.state.alternatives.push(React.createElement(Alternative, 
                    {id: alt["alternativeID"], 
                    description: alt["description"],
                    vote: 0,
                    feedback: [],
                    key: i
                }))
                i++;
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
        //'Alternatives:' + alt_str
        this.state.alternatives
    );
  }
}

document.querySelectorAll('.alt_list')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(AltList, { commentID: commentID }),
      domContainer
    );
  });