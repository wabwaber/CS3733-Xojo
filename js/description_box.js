'use strict';

class DescriptionBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = { value: this.props.commentID }
  }

  render() {
    return React.createElement(
        'textarea',
        { type: 'text', rows: 4, cols: 50, onChange: function(syntheticEvent) {
            console.log(syntheticEvent.target.value)}
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