'use strict';

class AltConstruction extends React.Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.state = { alt_list : alternatives };
    }

    render() {
        const rendered_alternatives = this.state.alt_list.map((alt, index) => 
            <p key={index}>{(index + 1) + ". " + alt}</p>
        );

        return (
            <div>
                <h3>Alternatives:</h3>
                <div>
                    {this.state.alt_list.length != 0 ? rendered_alternatives : "Alternatives will appear here as you add them."}
                </div>
            </div>
        )
    }
}

document.querySelectorAll('.alt_construction')
.forEach(domContainer => {
    ReactDOM.render(
    React.createElement(AltConstruction, { alt_list: [] }),
    domContainer
    );
});