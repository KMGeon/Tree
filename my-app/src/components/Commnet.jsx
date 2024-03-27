import React, {useEffect, useMemo, useState} from 'react';

const MyComponent = () => {
    const [old, setOld] = useState(0);

    function handleClick() {
        setOld(old + 1);
    }

    // useEffect(() => {
    //   alert("하이");
    // }, [old]);


    let memoValue = useMemo(
        ()=>{
            return computeExpensiveValue(old, +1);
        },[]
    );

    function computeExpensiveValue(a,b){
        return a+b;
    }

    return (
        <div>
            {old}
            <button onClick={handleClick}>클릭</button>
        </div>
    );
};

export default MyComponent;
