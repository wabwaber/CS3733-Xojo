'use strict';

class DescriptionBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: null }
  }

  render() {
    var self = this;
    return React.createElement(
        'textarea',
        { type: 'text', id: this.props.commentID, rows: 4, cols: 50, onChange: function(syntheticEvent) {
            self.state.value = syntheticEvent.target.value;}
        }
     );
    }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.description_box')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(DescriptionBox, { commentID: commentID }),
      domContainer
    );
  });