'use strict';

class AltList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { alternatives: [] };
        this.get_alternatives();
    }

    get_username(urlParams) {

		var data = {}
		
		data["memberID"] = urlParams.get("memberid")
		
	    var js = JSON.stringify(data);
        console.log("JS:" + js);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", get_member_url, false);
        xhr.send(js);

		console.log(xhr);
		if (xhr.readyState == XMLHttpRequest.DONE) {
			if (xhr.status == 200) {
				var js = JSON.parse(xhr.responseText);
				console.log("XHR: " + xhr.responseText);
				console.log("NAME" + js["member"]["name"]);
				return js["member"]["name"];
			} else {
				return null
			}
		} else {
			return null
        }
    }

    get_votes(alt_id) {
        // Gets a list of approvals and disapprovals

        var data = {}
        data["alternativeID"] = alt_id;

        var js = JSON.stringify(data);
        console.log("JS:" + js);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", get_votes_url, false);
        xhr.send(js);

        console.log(xhr);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                var js = JSON.parse(xhr.responseText);
                console.log("XHR:" + xhr.responseText);
                return { approvals: js["approvals"], disapprovals: js["disapprovals"] };
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    get_feedback(alt_id) {
        // Gets a list of all feedback for an alternative

        var data = {}
        data["alternativeID"] = alt_id;

        var js = JSON.stringify(data);
        console.log("JS:" + js);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", get_feedback_url, false);
        xhr.send(js);

        console.log(xhr);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                var js = JSON.parse(xhr.responseText);
                console.log("XHR:" + xhr.responseText);
                return js["feedback"];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    get_alternatives() {
        // Set loading
        set_loading(true);

        var data = {};

        const urlParams = new URLSearchParams(window.location.search);
        data["choiceID"] = urlParams.get('id');

        var js = JSON.stringify(data);
        console.log("JS:" + js);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", list_alt_url, false);
        xhr.send(js);

        console.log(xhr);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                var js = JSON.parse(xhr.responseText);
                console.log("XHR:" + xhr.responseText);
                
                for (const [i, alt] of js["list"].entries()) {
                    console.log(alt["description"]);
                    
                    var username = this.get_username(urlParams);
                    var votes = this.get_votes(alt["alternativeID"]);
                    var feedback = this.get_feedback(alt["alternativeID"]);
                    
                    // Construct list of alternatives
                    this.state.alternatives.push(React.createElement(Alternative, 
                        {id: alt["alternativeID"], 
                        description: alt["description"],
                        vote: 0,
                        feedback: feedback,
                        approvals: votes.approvals,
                        disapprovals: votes.disapprovals,
                        user: username,
                        choice_id: urlParams.get('id'),
                        key: alt["alternativeID"]
                    }))
                }

                set_loading(false);

            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        }
    }

    render() {
        console.log("Sending request");
        return (
            <div>
                {this.state.alternatives}
            </div>
        )
    }
}

document.querySelectorAll('.alt_list')
  .forEach(domContainer => {
    ReactDOM.render(
      React.createElement(AltList, { }),
      domContainer
    );
  });
