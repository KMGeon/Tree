import React from 'react';

const MyComponent = ({title, description}) => {
    return (
        <li>
            <h3>{title}</h3>
            <p>
                {description}
            </p>
        </li>
    );
};

MyComponent.propTypes = {};

export default MyComponent;
