import React, {useRef, useState} from 'react';

const MyComponent = () => {
    const playerName = useRef();
    const [enteredPlayerName, setEWnteredPlayerName] = useState('');
    // const [submitted, setSubmitted] = useState(false);


    function handleState(event){
        // setSubmitted(false);
        setEWnteredPlayerName(event.target.value);
    }

    function handleClick(){
        // setSubmitted(true);
        let value = playerName.current.value;
        console.log(value);
        setEWnteredPlayerName(value);
    }


    return (
        <section id="player">
            <h2>Welcome {enteredPlayerName}</h2>
            <p>
                <input
                    ref={playerName}
                    type="text"
                    value={enteredPlayerName}
                    onChange={handleState}
                />
                <button onClick={handleClick}>Set Name</button>
            </p>
        </section>
    );
};

export default MyComponent;

