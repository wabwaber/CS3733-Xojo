// An alternative object that renders the description, feedback and vote of the alternative
'use strict';

const emptyUpvoteUrl = "emptyUpvote.png";
const emptyDownvoteUrl = "emptyDownvote.png";
const fullUpvoteUrl = "upvote.png";
const fullDownvoteUrl = "downvote.png";

class Alternative extends React.Component {
    constructor(props) {
        super(props);
        this.state = { id: props.id, description: props.description, 
            vote: props.vote, feedback: props.feedback, key: props.key};
    }

    upvote() {
        // Register a click on the upvote button and send an update to backend
        console.log("Upvoting");
        console.log(this.state.id);
        // Check if upvote is already cast, deselect
        if (this.state.vote == 1) {

            // Update page state
            document.getElementById('upvote'+this.state.id).src = emptyUpvoteUrl;
            this.state.vote = 0;

            // Send vote update XHR
        } else {

            // Update page state
            document.getElementById('upvote'+this.state.id).src = fullUpvoteUrl;
            document.getElementById('downvote'+this.state.id).src = emptyDownvoteUrl;
            this.state.vote = 1;
        }
    }

    downvote() {
        // Register a click on the downvote button and send an update to the backend
        console.log("Downvoting");

        // Check if downvote is already cast, deselect
        if (this.state.vote == -1) {

            // Update page state
            document.getElementById('downvote'+this.state.id).src = emptyDownvoteUrl;
            this.state.vote = 0;

            // Send vote update XHR
        } else {
            document.getElementById('downvote'+this.state.id).src = fullDownvoteUrl;
            document.getElementById('upvote'+this.state.id).src = emptyUpvoteUrl;
            this.state.vote = -1;
        }
    }

    addFeedback() {
        // Prompt the user for feed back and send it to backend
        console.log("Getting feedback");
    }

    render() {
        // Construct the alternative box
        console.log(this.state.id);
        console.log(this.state.description);
        console.log(this.state.vote);
        console.log(this.state.feedback);
        console.log(this.state.key);
        // this.state.description = "A short description";
        this.state.vote = 0;
        this.state.feedback = [["Joe Smith", "Some feedback"], ["Jack Smith", "Other Feedback"]];
        return (
            <div className="alt_box" style={{margin: "5px", position: "relative"}}>
                <div className="alt_desc" style={{margin: "5px", width: "80%", float: "left"}}>
                    <p>{this.state.description}</p>
                </div>
                <div className="vote_buttons" style={{width: "10%", margin: "5px", float: "left"}}>
                    <img id={"upvote"+this.state.id} style={{width: "30px", height: "30px"}}
                        src={emptyUpvoteUrl}
                        onClick={() => this.upvote()}
                    />
                    <img id={"downvote"+this.state.id} style={{width: "30px", height: "30px"}}
                        src={emptyDownvoteUrl}
                        onClick={() => this.downvote()}
                    />
                </div>
                <div className="feedback" style={{margin: "5px", width: "100%", float: "left"}}>
                    <FeedbackList feedback={this.state.feedback}/>
                </div>
                <div className="add_feedback" style={{width: "100%", float: "left"}}>
                    <button onClick={() => this.addFeedback()}>Add Feedback</button>
                </div>
            </div>
        );

    }
}

class FeedbackList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { feedback: this.props.feedback };
    }

    render() {
        const feedback_comments = this.state.feedback.map((fb) =>
            <p><b>{fb[0]}</b>{': '}{fb[1]}</p>
        );
        return (
            <div className="feedback_list">
                {feedback_comments}
            </div>
        )
    }
}

document.querySelectorAll('.alternative')
.forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
        ReactDOM.render(
            React.createElement(Alternative, { }),
            domContainer
        );
});