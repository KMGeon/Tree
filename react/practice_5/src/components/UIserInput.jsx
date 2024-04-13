import React, {useState} from 'react';

const MyComponent = () => {
    const [userInput, setUserInput] = useState({
        initialInvestment : 10000,
        annualInvestment:1200,
        expectedReturn:6,
        duration: 120
    });


    function handleChange(inputIdentifier, newValue){
        setUserInput(prevState => {
            return {
              ...prevState,
                [inputIdentifier]:newValue
            };
        });
    }

    return (
        <>
            <section id='user-input'>
                <div className='input-group'>
                    <p>
                        <label>Initial Investment</label>
                        <input type='number' required onChange={handleChange}/>
                    </p>
                    <p>
                        <label>Annual Investment</label>
                        <input type='number' required/>
                    </p>
                </div>

                <div className='input-group'>
                    <p>
                        <label>Expected Return</label>
                        <input type='number' required/>
                    </p>
                    <p>
                        <label>Duration</label>
                        <input type='number' required/>
                    </p>
                </div>
            </section>
        </>
    );
};

export default MyComponent;
