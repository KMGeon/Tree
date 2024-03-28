import React, {useRef, useState} from 'react';
import ResultModal from "./ResultModal";

const MyComponent = ({title, targetTime}) => {
    const [timerStarted, setTimerStarted] = useState(false);
    const [timerExpired, setTimerExpired] = useState(false);

    let timer = useRef();
    const dialog = useRef();


    function handleStart() {
        timer = setTimeout(() => {
            setTimerExpired(true)
            // dialog.current.showModal();
        }, targetTime * 1000);
        setTimerStarted(true);
    }

    function handleStop() {
        clearTimeout(timer.current);
    }


    return (
        <>
            {timerExpired &&
                <ResultModal
                    ref={dialog}
                    targetTime={targetTime}
                    result='lost'/>
            }
            <section className='challenge'>
                <h2>{title}</h2>
                {timerExpired && <p>You Lost!</p>}
                <p className='challenge-time'>
                    {targetTime} second {targetTime > 1 ? 's' : ''}
                </p>

                <p>
                    <button onClick={timerStarted ? handleStop : handleStart}>
                        {timerStarted ? 'Stop' : 'Start'} Challenge
                    </button>
                </p>

                <p className={timerStarted ? 'active' : undefined}>
                    {timerStarted ? 'Time is running...' : 'Timer inactive'}
                </p>
            </section>
        </>

    );
};

export default MyComponent;
