'use strict';

class Choice extends React.Component {
    constructor(props) {
        super(props);
        this.state = { id: props.id, description: props.description, creation_date: props.creation_date, choice_name: props.choice_name, choice_desc: props.choice_desc};
    }


  render() {
        var date = new Date(this.state.creation_date); 

        return (
            <div className="choice_box">
                <div className="choice_name">
                    <h2>{this.state.choice_name}</h2>
                </div>
                <div className="choice_id">
                    <p>{this.state.id}</p>
                </div>
                <div className="choice_date">
                    <p>{date.toString()}</p>
                </div>
                <div className="choice_is_completed">
                    <p>{"not completed"}</p>
                </div>
            </div>
        );
    }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.choice')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(Choice, { commentID: commentID }),
      domContainer
    );
  });
