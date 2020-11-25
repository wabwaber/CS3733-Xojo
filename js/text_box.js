'use strict';

class TextBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: null  }
  }

  render() {
    var self = this;
    return React.createElement(
        'input',
        { type: 'text', id: this.props.commentID, onChange: function(syntheticEvent) {
            self.state.value = syntheticEvent.target.value;}
        }
     );
    }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.text_box')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(TextBox, { commentID: commentID }),
      domContainer
    );
  });