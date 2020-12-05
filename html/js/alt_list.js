'use strict';

class AltList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { alternatives: [] };
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

    render() {
        var data = {};
        var alt_str;

        const urlParams = new URLSearchParams(window.location.search);
        console.log(urlParams.get('id'));
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
                console.log(js["list"]);
                
                for (const alt of js["list"]) {
                    console.log(alt["description"]);

                    var votes = this.get_votes(alt["alternativeID"]);
                    console.table(votes);
                    // Construct list of alternatives
                    this.state.alternatives.push(React.createElement(Alternative, 
                        {id: alt["alternativeID"], 
                        description: alt["description"],
                        vote: 0,
                        feedback: [],
                        approvals: votes.approvals,
                        disapprovals: votes.disapprovals,
                    }))
                }

            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        }

        console.log("Sending request");
        return React.createElement(
            'div',
            { },
            //'Alternatives:' + alt_str
            this.state.alternatives
        );
    }
}

document.querySelectorAll('.alt_list')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(AltList, { commentID: commentID }),
      domContainer
    );
  });