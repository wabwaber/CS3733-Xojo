'use strict';

class Choice extends React.Component {
    constructor(props) {
        super(props);
        this.state = { id: props.id, description: props.description, creation_date: props.creation_date, choice_name: props.choice_name, choice_desc: props.choice_desc};
    }

    render() {
        var date = new Date(this.state.creation_date); 

        return (
            <div className="choice_box" style={{margin: "5px", border: "solid", position: "relative", display: "block", overflow: "auto", overflowX: "hidden"}}>
                <div className="choice_name" style={{margin: "5px"}}>
                    <h2>{"Name: " + this.state.choice_name}</h2>
                </div>
                <div className="choice_id" style={{margin: "5px"}}>
                    <p>{"ID: " + this.state.id}</p>
                </div>
                <div className="choice_date" style={{margin: "5px"}}>
                    <p>{"Created: " + date.toString()}</p>
                </div>
                <div className="choice_is_completed" style={{margin: "5px"}}>
                    <p>{"Completed? " + "false"}</p>
                </div>
            </div>
        );
    }
}
