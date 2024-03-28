import React, {useRef, useState} from 'react';

const MyComponent = () => {
    let ref = useRef(0);

    function handleClick(){
        ref.current = ref.current + 1;
        alert('You clicked ' + ref.current + ' times!');
    }

    return (
        <>
            {/*
            이때 다시 랜더링을 하지 않기 때문에 값은 그대로 0으로 나온다.
            */}
            {ref.current}
            <button onClick={handleClick}>버튼</button>
        </>
    );
};

export default MyComponent;
