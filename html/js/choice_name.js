'use strict';

var complete_choice = (function () { });

class ChoiceName extends React.Component {
    constructor(props) {
        super(props);
        this.state = { choice_name: "Loading name...", choice_desc: "Loading description...", user: "Loading user...", complete: false, completed_choice: null };
    }

    componentDidMount() {
        setTimeout(() => {
            this.get_name()
            this.get_user()
        }, 400);
    }

    get_user() {
        var data = {}
        
        const urlParams = new URLSearchParams(window.location.search);
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
                this.setState({user: js["member"]["name"]});
            }
        }
    }

    get_name() {
        var data = {};

        const urlParams = new URLSearchParams(window.location.search);
        data["choiceID"] = urlParams.get('id');

        var js = JSON.stringify(data);
        console.log("JS:" + js);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", get_choice_url, false);
        xhr.send(js);

        console.log(xhr);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                var js = JSON.parse(xhr.responseText);
                this.setState({choice_name: js["choice"]["name"]});
                this.setState({choice_desc: js["choice"]["description"]});

                // Update alt_list if choice is complete
                if (js["choice"]["completed"]) {
                    complete_choice.callback(true, this.get_completed_choice());
                }
            }
        }
    }

    get_completed_choice() {
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
                
                for (const alt of js["list"]) {
                    console.log(alt["description"]);
                    
                    if (alt["selected"]) {
                        return alt["description"];
                    }
                }
            }
        }

        return "error finding selected alternative";
    }

    render() { 
        return (
            <div>
                <div className="name">
                    <h2>{"Choice: " + this.state.choice_name}</h2>
                </div>
                <div className="desc">
                    <p>{"Description: " + this.state.choice_desc}</p>
                </div>  
                <div className="signed_in">
                    <p>{"Signed in as: " + this.state.user}</p>
                </div>
            </div>
        )
    }
}

document.querySelectorAll('.choice_name')
    .forEach(domContainer => {
        ReactDOM.render(
        React.createElement(ChoiceName, { }),
        domContainer
        );
    });