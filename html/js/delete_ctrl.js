'use strict';

class DeleteCtrl extends React.Component {
    constructor(props) {
        super(props);
        this.state = { days: '' };
        this.onChange = this.onChange.bind(this);
        this.deleteHandler.bind(this);
    }

    onChange(e) {
        this.setState({ days: e.target.value });
    }

    deleteHandler() {
        const DAY_MS = 86400000;

        if (parseFloat(this.state.days) != NaN) {
            var date = new Date();
            var ms = date.getTime() - (parseFloat(this.state.days) * DAY_MS);

            var data = {}
            data["time"] = ms;

            var js = JSON.stringify(data);
            console.log("JS:" + js);

            var xhr = new XMLHttpRequest();

            xhr.open("POST", delete_choices_url, false);
            xhr.send(js);

            console.log(xhr);
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    console.log("XHR:" + xhr.responseText);

                    var js = JSON.parse(xhr.responseText);

                    if (js["httpCode"] == 200) {
                        alert("Deleted choices, refresh to view new report");
                    } else {
                        alert("Unable to delete choices");
                    }
                } else {
                    console.log("actual:" + xhr.responseText)
                    var js = JSON.parse(xhr.responseText);
                    var err = js["response"];
                    alert (err);
                }
            }
        } else {
            alert("Must be a float in the form of days (6 hours = .25)");
        }
    }

    render() {
        return (
            <div>
                <div>
                    <p>{"Delete all choices days older than:"}</p>
                    <input type={"text"} value={this.state.days} onChange={this.onChange}/>
                    <button onClick={() => this.deleteHandler()}>{"Delete"}</button>
                </div>
            </div>
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