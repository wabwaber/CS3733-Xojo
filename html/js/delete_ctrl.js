'use strict';

class DeleteCtrl extends React.Component {
  constructor(props) {
    super(props);
    this.state = { days: null, pressed: false };
  }

  render() {
    var self = this;
    return React.createElement(
      'div',
      { },
      React.createElement(
          'div',
          { },
          React.createElement(
              'p',
              {},
              'Delete all choices days older than: '
          ),
          React.createElement(
              'input',
              { type: 'text', onChange: function(syntheticEvent) {
                console.log(syntheticEvent.target.value);
                self.state.days = syntheticEvent.target.value;}
              }
          ),
          React.createElement(
              'button',
              { onClick: () => delete_choices(self.days) },
              'Delete'
          )

        )  
      );
  }
}

document.querySelectorAll('.del_ctrl')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(DeleteCtrl, { commentID: commentID }),
      domContainer
    );
  });