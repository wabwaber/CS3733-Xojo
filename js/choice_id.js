'use strict';

class ChoiceID extends React.Component {
  constructor(props) {
    super(props);
    this.state = { };
  }

  render() {
    return React.createElement(
      'h2',
      { },
      'Choice ID: ' + window.location.search.substring(4)
    );
  }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.choice_id')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(ChoiceID, { commentID: commentID }),
      domContainer
    );
  });