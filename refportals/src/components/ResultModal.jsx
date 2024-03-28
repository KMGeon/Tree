import React, {forwardRef} from 'react';

const MyComponent = forwardRef(function ({result, targetTime}, ref) {
    return (
        <>
            <dialog ref={ref} className='result-modal' open>
                <h2>You {result}</h2>
                <p>The target time was <string>{targetTime} seconds.</string></p>
                <p>You stopped the timer with <string>X seconds left.</string></p>

                <form method='dialog'>
                    <button>Close</button>
                </form>

            </dialog>
        </>
    );
});

export default MyComponent;
