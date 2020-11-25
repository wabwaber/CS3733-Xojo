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
    // xhr.open("GET", list_alt_url, true);
    // xhr.send(js);

    // xhr.onloadend = function () {
    //     console.log(xhr);
    //     if (xhr.readyState == XMLHttpRequest.DONE) {
    //         if (xhr.status == 200) {
    //             console.log("XHR:" + xhr.responseText);
    //             // Do something with xhr.responseText
    //         } else {
    //             console.log("actual:" + xhr.responseText)
    //             var js = JSON.parse(xhr.responseText);
    //             var err = js["response"];
    //             alert (err);
    //         }
    //     }
    // };

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

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.choice_list')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(ChoiceList, { commentID: commentID }),
      domContainer
    );
  });