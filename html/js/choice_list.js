'use strict';

class ChoiceList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { };
  }

  render() {
    var data = {}
    data["choiceID"] = window.location.pathname;

    var js = JSON.stringify(data);
    console.log("JS:" + js);

    var xhr = new XMLHttpRequest();

    xhr.open("GET", get_all_choices_url, true);
    xhr.send(js);

    xhr.onloadend = function () {
        console.log(xhr);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                console.log("XHR:" + xhr.responseText);

                var js = JSON.parse(xhr.responseText);
            console.log(js["list"]);

            var i = 0;
		for (const choice of js["list"]) {
                console.log("Val of i: " + i);
                console.log(choice["choiceID"]);
                this.state.alternatives.push(React.createElement(Choice, 
                    {id: choice["choiceID"], 
                    description: choice["description"],
                    creation_date: choice["dateCreated"]
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
     };

    console.log("Sending request");
    return React.createElement(
      'div',
      { },
      React.createElement(
        'div',
        { },
        'Choices will appear here'
      )
    );
  }
}

document.querySelectorAll('.choice_list')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(ChoiceList, { commentID: commentID }),
      domContainer
    );
  });
