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
        this.state.pressed = false;
      }
      else if (this.props.commentID == 'Create') {
        request_choice();
        this.state.pressed = false;
      }
      else if (this.props.commentID == 'Participate') {
        request_login();
        this.state.pressed = false;
      }
    }

    return React.createElement(
      'button',
      { onClick: () => this.setState({ pressed: true }) },
      this.props.commentID
    );
  }
}

document.querySelectorAll('.button')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(Button, { commentID: commentID }),
      domContainer
    );
  });