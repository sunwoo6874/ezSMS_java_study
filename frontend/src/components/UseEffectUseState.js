import "../App.css";
import React, { useEffect, useState } from "react";

const UseEffectUseState = () => {
  // useState로 number 변수 값을 0으로 초기화
  const [number, setNumber] = useState(0);

  // useState로 name 변수 값을 'Josh'으로 초기화
  const [name, setName] = useState("josh");

  useEffect(() => {
    console.log("hello");
  });

  const counter = () => setNumber(number + 1);
  const nameChanger = () => {
    setName("Mike");
  };

  return (
    <div>
      <button onClick={counter}>click</button>
      <button onClick={nameChanger}>change Name</button>
      <div>{number}</div>
      <div>{name}</div>
    </div>
  );
};

export default UseEffectUseState;
