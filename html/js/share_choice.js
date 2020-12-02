'use strict';

class ShareChoice extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: null }
  }

  render() {
    const urlParams = new URLSearchParams(window.location.search);
    var choiceID = urlParams.get('id');

    return React.createElement(
        'p',
        {},
        'Share this choice with others: https://3733xojo.s3.us-east-2.amazonaws.com/html/user_login.html?id=' + choiceID
     );
    }
}

document.querySelectorAll('.share_choice')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(ShareChoice, { commentID: commentID }),
      domContainer
    );
  });