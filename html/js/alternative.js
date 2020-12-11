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
            feedback: props.feedback, showFeedback: false, username: props.user, choice_id: props.choice_id };
        const urlParams = new URLSearchParams(window.location.search);
        this.choice_id = urlParams.get('id');
        this.member_id = urlParams.get('memberid');

        if (this.state.approvals.some(v => v === this.state.username)) {
            console.log("Upvote");
            this.state.vote = 1;
        } else if (this.state.disapprovals.some(v => v === this.state.username)) {
            console.log("Downvote");
            this.state.vote = -1;
        }

        this.sendFeedback = this.sendFeedback.bind(this);
        this.closeFeedback = this.closeFeedback.bind(this);
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

    openFeedback() {
        // Prompt the user for feed back and send it to backend
        console.log("Getting feedback");
        this.setState({showFeedback: true});
    }

    sendFeedback(fb) {
        console.log("Sending feedback " + fb);
        console.log("NAME: " + this.state.username);
        
        var data = {};
        data["alternativeID"] = this.state.id;
        data["memberID"] = this.member_id;
        data["feedbackDesc"] = fb;

        var self = this;

        var js = JSON.stringify(data);
        console.log("JS:" + js);
        var xhr = new XMLHttpRequest();
        xhr.open("POST", add_feedback_url, true);

        xhr.send(js);

        xhr.onloadend = function () {
            console.log(xhr);
            if (xhr.readyState == XMLHttpRequest.DONE) {

                // Good response
                if (xhr.status == 200) {
                    var js = JSON.parse(xhr.responseText);

                    if (js["httpCode"] == 200) {
                        console.log("Feedback sent successfully");

                        var timestamp = js["timeCreated"]; 
                        self.updateFeedbackList(fb, timestamp);

                    } else {
                        alert("There was a problem sending feedback.")
                    }
                }
            }
        }
    }

    updateFeedbackList(fb, time) {
        var fb_list = this.state.feedback;
        fb_list.push({"name": this.state.username, "description": fb, "timeCreated": time})
        console.log(fb_list);
        console.log(typeof(fb_list));
        this.setState({feedback: fb_list});
    }

    closeFeedback() {
        console.log("Closing feedback");
        this.setState({showFeedback: false});
    }

    render() {
        // Construct the alternative box
        //this.state.feedback = [["Joe Smith", "Some feedback", 1607718455147], ["Jack Smith", "Other Feedback", 1607718455147]];
        return (
            <div className="alt_box" key={this.state.id} style={{margin: "5px", border: "solid", position: "relative", display: "block", overflow: "auto", overflowX: "hidden"}}>
                <div className="alt_desc" style={{margin: "5px", width: "80%", float: "left"}}>
                    <p>{this.state.description}</p>
                </div>
                <div className="vote_buttons" style={{width: "10%", margin: "5px", float: "right"}}>
                    <img id={"downvote"+this.state.id} style={{width: "30px", height: "30px", float: "right"}}
                        src={this.state.vote == -1 ? fullDownvoteUrl : emptyDownvoteUrl}
                        onClick={() => this.downvote()}
                    />
                    <img id={"upvote"+this.state.id} style={{width: "30px", height: "30px", float: "right"}}
                        src={this.state.vote == 1 ? fullUpvoteUrl: emptyUpvoteUrl}
                        onClick={() => this.upvote()}
                    />
                </div>
                <div className="votes" style={{margin: "5px", width: "100%", float: "left"}}>
                    <div className="approvals">
                        <VoteList isUpvote={true} members={this.state.approvals} this_username={this.state.username}/>
                    </div>
                    <div className="disapprovals">
                        <VoteList isUpvote={false} members={this.state.disapprovals} this_username={this.state.username}/>
                    </div>
                    <div className="totals">
                        <p>{"Approvals: " + this.state.approvals.length + " Disapprovals: " + this.state.disapprovals.length}</p>
                    </div>
                </div>
                <div className="feedback" style={{margin: "5px", width: "100%", float: "left"}}>
                    <FeedbackList feedback={this.state.feedback}/>
                </div>
                <div className="add_feedback" style={{margin: "5px", width: "100%", float: "left"}}>
                    <div className="feedback_box" style={{display: (this.state.showFeedback ? "block" : "none")}}>
                        <FeedbackBox sendHandler={this.sendFeedback.bind(this)} closeHandler={this.closeFeedback}/>
                    </div>

                    <div className="feedback_button" style={{display: (this.state.showFeedback ? "none" : "block")}}>
                        <button onClick={() => this.openFeedback()}>Add Feedback</button>
                    </div>
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
        const feedback_comments = this.state.feedback.map((fb, index) =>
            <p key={index}><b>{fb["name"]}</b>{': '}{fb["description"]}</p>
        );
        return (
            <div className="feedback_list">
                {feedback_comments}
            </div>
        )
    }
}

class FeedbackBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};
        this.onChange = this.onChange.bind(this);
    }

    onChange(e) {
        this.setState({ value: e.target.value });
    }

    render() {
        return (
            <div>
                <input type="text" value={this.state.value} onChange={this.onChange} />
                <button onClick={() => this.props.sendHandler(this.state.value)}>Send</button>
                <button onClick={this.props.closeHandler}>Close</button>
            </div>
        )
    }
}

class VoteList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { isUpvote: props.isUpvote, members: props.members, current_member: props.this_username };
    }

    render() {
        if (this.state.members) {
            const vote_element = this.state.members.map((member, index) =>
                <div key={index}>
                    <img src={this.state.isUpvote ? fullUpvoteUrl : fullDownvoteUrl} style={{width: "30px", height: "30px", display: "inline"}}/>
                    <p style={{display: "inline"}}>{member}</p> 
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
        ReactDOM.render(
            React.createElement(Alternative, { }),
            domContainer
        );
});