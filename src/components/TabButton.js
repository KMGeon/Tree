import React from 'react';



const MyComponent = ({label, onSelect, isSelected}) => {
    return (
        <li>
            <button
                className={isSelected ? 'active' : undefined}
                onClick={onSelect}>
                {label}
            </button>
        </li>
    );
};


export default MyComponent;
