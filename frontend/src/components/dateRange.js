import React, { useState } from "react";
import DatePicker from "react-datepicker";
import { ko } from "date-fns/esm/locale";

import "react-datepicker/dist/react-datepicker.css";

export default function DateRange() {
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());

  return (
    <>
      <h3 className='calender'>STT 목록 조회 </h3>
      <div>
        <DatePicker
          locale={ko} // 언어설정 기본값은 영어
          dateFormat='yyyy-MM-dd'
          selected={startDate}
          onChange={(date) => setStartDate(date)}
          selectsStart
          startDate={startDate}
          endDate={endDate}
        />
      </div>
      <div>
        <DatePicker
          locale={ko} // 언어설정 기본값은 영어
          dateFormat='yyyy-MM-dd'
          selected={endDate}
          onChange={(date) => setEndDate(date)}
          selectsEnd
          startDate={startDate}
          endDate={endDate}
          minDate={startDate}
        />
      </div>
    </>
  );
}
// https://jaimemin.tistory.com/1432
// https://kth990303.tistory.com/155
// https://velog.io/@remon/React-React-Datepicker-달력-라이브러리-사용법-커스텀-하기-CSS-변경하기
