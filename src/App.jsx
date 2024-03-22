import reactImg from './asset/logo.png';
import { CORE_CONCEPTS} from "./data";


const reactDescriptions = ['Fundamental', 'Crucial', 'Core'];

function genRandomInt(max) {
    return Math.floor(Math.random() * (max + 1));
}

function Header() {
    const descriptions = reactDescriptions[genRandomInt(2)];

    return (
        <header>
            <h1>React Essentials</h1>
            <p>
                {/* 중갈호 사이에 js 표현을 사용할 수 있다.
                코드가 실행되는 시점에서 변수 또는 상수로 표현살 수 있다.

                - 모든 코드가 변환 및 최적화 되어서 함께 묶여진다. 이 과정에서 로딩된 이미지 파일은 유실될 수 있다.
                기존의 이미지 -> src/assets/...

                */}
                {/*<img src={reactImg} alt="atom"/>*/}
                {descriptions} React concepts you will need for almost any app you are going to build!
            </p>
        </header>
    );
}

function CoreConcept({title, description}) {
    return(
    <li>
        <h3>{title}</h3>
        <p>
            {description}
        </p>
    </li>
    );
}

function App() {
    return (
        <div>

            <section id="core-concepts">
                <h2>Core Concepts</h2>

                <ul>
                    {/*
                        여기서 title, description, img는 props라고 부른다.
                    */}
                    <CoreConcept
                        title={CORE_CONCEPTS[0].title}
                        description={CORE_CONCEPTS[0].description}
                    />
                    <CoreConcept {...CORE_CONCEPTS[1]}/>
                    <CoreConcept
                        title={CORE_CONCEPTS[2].title}
                        description={CORE_CONCEPTS[2].description}
                    />
                    <CoreConcept
                        title={CORE_CONCEPTS[3].title}
                        description={CORE_CONCEPTS[3].description}
                    />
                </ul>

            </section>
            <Header/>
            <main>
                <h2>Time to get started!</h2>
            </main>
        </div>
    );
}

export default App;

/**
 core react concepts
 -
 */

// import React from 'react';
//
// function Header(){
//     return(
//         <header>
//             <h1>My First React App</h1>
//         </header>
//     )
// }
//
//
// const  MyComponent = () => {
//     return (
//         <div>
//             <Header/>
//             asdf
//         </div>
//     );
// };
//
// export default MyComponent;

// import React, {Component} from "react";
// import "./App.css"
//
// export default class App extends Component {
//
//     handleClick = (id) => {
//         let newTodoData = this.state.todoData.filter((data) => data.id !== id);
//         this.setState({todoData: newTodoData});
//     }
//
//     handleChange = (e) => {
//         this.setState({value: e.target.value})
//     }
//
//     state = {
//         todoData: [],
//         value: ""
//     }
//
//
//     btnStyle = {
//         color: "#fff",
//         border: "none",
//         padding: "5px 9px",
//         borderRadius: "50%",
//         cursor: "pointer",
//         float: "right"
//     }
//
//     getStyle = (completed) => {
//         return {
//             padding: "10px",
//             borderBottom: "1px #ccc dotted",
//             textDecoration: completed ? 'line-through' : 'none'
//
//         }
//     }
//
//
//     handleSubmit = (e) => {
//         // form 안에 input을 전송할 때 페이지 리로드 되는 걸 막아줌
//         // form에서 submit을 하면 화면을 reload를 한다. 이것을 막아줌
//         e.preventDefault();
//
//         //새로운 할 일 데이터
//         let newData = {
//             id: Date.now(),
//             title: this.state.value,
//             completed: false
//         };
//
//         //원래 있던 할 일에 새로운 일 더하기
//         // ...은 전개 연산자
//         this.setState({todoData: [...this.state.todoData, newData], value: ""});
//     }
//     handleComplete = (id) => {
//         let newTodoData = this.state.todoData.map((data) => {
//             if (data.id === id) {
//                 data.completed = !data.completed
//             }
//             return data;
//         });
//         this.setState({todoData: newTodoData});
//     };
//
//     render() {
//         return (
//             <div className="container">
//                 <div className="todoBlock">
//
//                     <div className="title">
//                         <h1>할 일 목록</h1>
//                     </div>
//                     {this.state.todoData.map((todoData) => (
//                         <div key={todoData.id}>
//                             <div style={this.getStyle(todoData.completed)}>
//                                 <input type="checkbox"
//                                        onChange={() => this.handleComplete(todoData.id)}
//                                        defaultChecked={false}
//                                 />
//                                 {todoData.title}
//                                 <button style={this.btnStyle} onClick={() => this.handleClick(todoData.id)}>x</button>
//                             </div>
//                         </div>
//                     ))}
//
//                     <form style={{display: "flex"}} onSubmit={this.handleSubmit}>
//
//                         <input
//                             type="text"
//                             name="value"
//                             style={{flex: '10', padding: '5px'}}
//                             placeholder="해야 할 일을 입력하세요."
//                             value={this.state.value}
//                             onChange={this.handleChange}
//                         />
//                         <input type="submit"
//                                value="입력"
//                                className="btn"
//                                style={{flex: '1px'}
//                                }/>
//                     </form>
//                 </div>
//             </div>
//         )
//     }
//
//
// }