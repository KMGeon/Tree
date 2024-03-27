import React, {useRef, useState} from 'react';

const MyComponent = () => {
    const playerName = useRef();
    const [enteredPlayerName, setEWnteredPlayerName] = useState('');
    const [submitted, setSubmitted] = useState(false);


    function handleState(event){
        setSubmitted(false);
        setEWnteredPlayerName(event.target.value);
    }

    function handleClick(){
        setSubmitted(true);
    }


    return (
        <section id="player">
            <h2>Welcome {!submitted? 'unknown entity' : enteredPlayerName}</h2>
            <p>
                <input type="text"  value={enteredPlayerName} onChange={handleState}/>
                <button onClick={handleClick}>Set Name</button>
            </p>
        </section>
    );
};

export default MyComponent;

