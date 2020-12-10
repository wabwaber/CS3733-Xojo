'use strict';

// declare loading in global scope
var loading = false;

function set_loading(state) {
    // Set loading notification
    loading = state;
    console.log("Setting loading to: " + loading);

    ReactDOM.render(
        React.createElement(LoadingNotification, { }),
        document.getElementById('loading_choice')
    )
}

class LoadingNotification extends React.Component {
    constructor(props) {
        super(props);
        this.state = { commentID: props.commentID, isVisible: loading };
    }

    render() {
        var draw_method;
        if (loading) {
            draw_method = "flex";
        } else {
            draw_method = "none";
        }

        return (
            <div style={{width: "100%", height: "100%", position: "absolute", display: draw_method,
            alignItems: "center", justifyContent: "center"}}>
                <h3>{this.state.commentID}</h3>
            </div>
        )
    }
}

document.querySelectorAll('.loading_notification')
.forEach(domContainer => {
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
        React.createElement(LoadingNotification, { commentID: commentID }),
        domContainer
);
});