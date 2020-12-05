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
            vote: props.vote, approvals: props.approvals, disapprovals: props.disapprovals,
            feedback: props.feedback, key: props.key };
        const urlParams = new URLSearchParams(window.location.search);
        this.choice_id = urlParams.get('id');
        this.member_id = urlParams.get('memberid');
    }

    upvote() {
        // Register a click on the upvote button and send an update to backend
        console.log("Upvoting");
        console.log(this.state.id);
        // Check if upvote is already cast, deselect
        if (this.state.vote == 1) {

            // Update page state
            document.getElementById('upvote'+this.state.id).src = emptyUpvoteUrl;

            // Send vote update XHR
            this.log_vote(null);
            this.state.vote = 0;

        } else {

            // Update page state
            document.getElementById('upvote'+this.state.id).src = fullUpvoteUrl;
            document.getElementById('downvote'+this.state.id).src = emptyDownvoteUrl;

            this.log_vote(true);
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

            // Send vote update XHR
            this.log_vote(null);
            this.state.vote = 0;

        } else {
            document.getElementById('downvote'+this.state.id).src = fullDownvoteUrl;
            document.getElementById('upvote'+this.state.id).src = emptyUpvoteUrl;

            this.log_vote(false);
            this.state.vote = -1;
        }
    }

    log_vote(vote) {
        var data = {};
        data["alternativeID"] = this.state.id;
        data["memberID"] = this.member_id;
        data["isUpvote"] = vote;

        var self = this;
        var last_vote = self.state.vote;

        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", change_vote_url, true);

        xhr.send(js);

        xhr.onloadend = function () {
            console.log(xhr);
            if (xhr.readyState == XMLHttpRequest.DONE) {

                // Good response
                if (xhr.status == 200) {
                    var js = JSON.parse(xhr.responseText);

                    if (js["httpCode"] == 200) {
                        console.log("Vote successfully logged");
                    } else {

                        // Vote not logged, reset to former state
                        if (last_vote == 1) {
                            document.getElementById('upvote'+self.state.id).src = fullUpvoteUrl;
                            document.getElementById('downvote'+self.state.id).src = emptyDownvoteUrl;
                        } else if (last_vote == 0) {
                            document.getElementById('downvote'+self.state.id).src = emptyDownvoteUrl;
                            document.getElementById('upvote'+self.state.id).src = emptyUpvoteUrl;
                        } else {
                            document.getElementById('downvote'+self.state.id).src = fullDownvoteUrl;
                            document.getElementById('upvote'+self.state.id).src = emptyUpvoteUrl;
                        }

                        self.state.vote = last_vote;
                    }
                }
            }
        }
    }

    addFeedback() {
        // Prompt the user for feed back and send it to backend
        console.log("Getting feedback");
    }

    render() {
        // Construct the alternative box

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
                <div className="votes" style={{margin: "5px", width: "100%", float: "left"}}>
                    <div className="approvals">
                        <VoteList isUpvote={true} members={this.state.approvals} this_member={this.member_id}/>
                    </div>
                    <div className="disapprovals">
                        <VoteList isUpvote={false} members={this.state.disapprovals} this_member={this.member_id}/>
                    </div>
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

class VoteList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { isUpvote: props.isUpvote, members: props.members, current_member: props.this_member };
    }

    render() {
        if (this.state.members) {
            const vote_element = this.state.members.map((member) =>
                <div style={{display: (member.memberID != this.state.current_member ? "block" : "none")}}>
                    <img src={this.state.isUpvote ? fullUpvoteUrl : fullDownvoteUrl} style={{width: "30px", height: "30px", display: "inline"}}/>
                    <p style={{display: "inline"}}>{member.memberID}</p> 
                </div>
            );

            return (
                <div className="vote_list">
                    {vote_element}
                </div>
            )
        } else {
            return null;
        }
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