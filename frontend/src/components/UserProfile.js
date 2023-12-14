import React, { useState } from 'react';

const UserProfile = (props) => {
    const [age, setAge] = useState(props.initialAge); // State to manage age

    const increaseAge = () => {
        setAge(age + 1); // Update the age in the state
    };

    return (
        <div>
            <h2>User Profile</h2>
            <p>Name: {props.name}</p>
            <p>Age: {age}</p>
            <button onClick={increaseAge}>Increase Age</button>
        </div>
    );
};

export default UserProfile;
