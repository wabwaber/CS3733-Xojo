'use strict';

class Button extends React.Component {
  constructor(props) {
    super(props);
    this.state = { pressed: false };
  }

  render() {
    if (this.state.pressed) {
      if (this.props.commentID == 'Add') {
        add_alternative();
      }
    }

    return React.createElement(
      'button',
      { onClick: () => this.setState({ pressed: true }) },
      this.props.commentID
    );
  }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.button')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(Button, { commentID: commentID }),
      domContainer
    );
  });