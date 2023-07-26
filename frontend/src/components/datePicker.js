import React, { useState } from "react";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

export function BeginDate() {
  const [startDate, setStartDate] = useState(new Date());
  return (
    <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
  );
}
export function EndDate() {
  const [endDate, setEndDate] = useState(new Date());
  return (
    <DatePicker selected={endDate} onChange={(date) => setEndDate(date)} />
  );
}
