import React from 'react';
import CORE_CONCEPTS from "../data/data";
import CoreConcept from "./CoreConcept";

const MyComponent = () => {
    return (
        <div>
            <section id="core-concepts">
                <h2>Core Concepts</h2>

                <ul>
                    {CORE_CONCEPTS.map((item) => <CoreConcept key={item.title} {...item}/>)}
                </ul>

            </section>
        </div>
    );
};

export default MyComponent;
