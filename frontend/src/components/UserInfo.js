import React, { useEffect, useState } from "react";
import axios from "axios";

export default function RequestUserInfo() {
  const [userInfo, setUserInfo] = useState("");
  useEffect(() => {
    axios
      .get("/ezsms/user/select/1")
      .then((response) => setUserInfo(response.data))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div>
      <p> /ezsms/user/select/1 의 결과 : {userInfo}</p>
      <button onClick={() => setUserInfo("no info")}>
        getUser 컴포넌트를 리렌더링 : {userInfo}
      </button>
    </div>
  );
}
