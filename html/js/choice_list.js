'use strict';

class ChoiceList extends React.Component {
    constructor(props) {
        super(props);
        this.state = { choices: [] };
    }

    render() {
        var data = {}
        data["choiceID"] = window.location.pathname;

        var js = JSON.stringify(data);
        console.log("JS:" + js);

        var xhr = new XMLHttpRequest();

        xhr.open("GET", get_all_choices_url, false);
        xhr.send(js);

        console.log(xhr);
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                console.log("XHR:" + xhr.responseText);

                var js = JSON.parse(xhr.responseText);

                for (const choice of js["list"]) {
                    console.log(choice["choiceID"]);
                    this.state.choices.push(React.createElement(Choice, 
                        { id: choice["choiceID"], 
                        choice_name: choice["name"],
                        description: choice["description"],
                        creation_date: choice["timeCreated"],
                        completed: choice["completed"]}))
                }
            } else {
                console.log("actual:" + xhr.responseText)
                var js = JSON.parse(xhr.responseText);
                var err = js["response"];
                alert (err);
            }
        }

        return (
            <table>
                <thead>
                    <tr>
                        <th>
                            {"Name"}
                        </th>
                        <th>
                            {"ID"}
                        </th>
                        <th>
                            {"Created"}
                        </th>
                        <th>
                            {"Completed"}
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {this.state.choices}
                </tbody>
            </table>
        );
    }

    newMethod() {
        return this.state;
    }
}

document.querySelectorAll('.choice_list')
  .forEach(domContainer => {
    // Read the comment ID from a data-* attribute.
    const commentID = domContainer.dataset.commentid;
    ReactDOM.render(
      React.createElement(ChoiceList, { commentID: commentID }),
      domContainer
    );
  });
