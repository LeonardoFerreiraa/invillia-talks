import React, { Component } from "react";

class Counter extends Component {
    state = {
        number: 0
    };

    handleIncrement = () => {
        this.setState(({ number }) => ({
            number: number + 1
        }))
    };

    handleDecrement = () => {
        this.setState(({ number }) => ({
            number: number - 1
        }))
    };

    componentWillMount() {
        console.log("componentWillMount")
    }

    componentDidMount() {
        console.log("componentDidMount")
    }

    render() {
        console.log("render");

        return <div>
            <h1>{this.state.number}</h1>
            <button onClick={this.handleIncrement}>Incrementar</button>
            <button onClick={this.handleDecrement}>Decrementar</button>
        </div>
    }
}

export default Counter;
