'use strict';

class Choice extends React.Component {
    constructor(props) {
        super(props);
        this.state = { id: props.id, description: props.description, creation_date: props.creation_date, choice_name: props.choice_name, choice_desc: props.choice_desc, completed: props.completed};
    }

    render() {
        var date = new Date(this.state.creation_date); 

        return (
            <tr key={this.state.id}>
                <td style={{border: "1px solid black"}}>
                    {this.state.choice_name}
                </td>
                <td style={{border: "1px solid black"}}>
                    {this.state.id}
                </td>
                <td style={{border: "1px solid black"}}>
                    {date.toString()}
                </td>
                <td style={{border: "1px solid black"}}>
                    {this.state.completed.toString()}
                </td>
            </tr>

        );
    }
}
