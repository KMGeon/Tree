import React from 'react';
import PropTypes from 'prop-types';

const reactDescriptions = ['Fundamental', 'Crucial', 'Core'];

function genRandomInt(max) {
    return Math.floor(Math.random() * (max + 1));
}
const MyComponent = () => {

    let random = reactDescriptions[genRandomInt(3)];

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
                {random} React concepts you will need for almost any app you are going to build!
            </p>
        </header>
    );
};

export default MyComponent;
