import React from 'react';



const MyComponent = ({label, onSelect}) => {
    return (
        <li>
            <button onClick={onSelect}>{label}</button>
        </li>
    );
};


export default MyComponent;
