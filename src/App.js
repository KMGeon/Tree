import React, {Component} from "react";
import "./App.css"

export default class App extends Component {

    btnStyle = {
        color: "#fff",
        border: "none",
        padding: "5px 9px",
        borderRadius: "50%",
        cursor: "pointer",
        float: "right"
    }

    todoData = [
        {
            "id": 1,
            "title": "공부하기",
            "completed": "true"
        },
        {
            "id": 2,
            "title": "청소하기",
            "completed": "false"
        },
        {
            "id": 3,
            "title": "운동하기",
            "completed": "true"
        }
    ]

    getStyle = () => {
        return {
            padding: "10px",
            borderBottom: "1px #ccc dotted",
            textDecoration: 'none'
        }
    }

    render() {
        return (
            <div className="container">
                <div className="todoBlock">

                    <div className="title">
                        <h1>할 일 목록</h1>
                    </div>
                    {this.todoData.map((todoData) => (
                        <div key={todoData.id}>
                            <div style={this.getStyle()}>
                                <input type="checkbox" defaultChecked={false}/>
                                {todoData.title}
                                <button style={this.btnStyle} onClick={() => this.handleClick(todoData.id)}>x</button>
                            </div>
                        </div>
                    ))}

                </div>
            </div>
        )
    }

    handleClick = (id) => {
        let newTodoData = this.todoData.filter((data) => data.id !== id);
        console.table("newTodoData", newTodoData);
    }
}