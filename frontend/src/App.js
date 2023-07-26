import DateRanger from "./components/dateRange.js";
import UserInfo from "./components/UserInfo.js";
import Element from "./components/Element.js";
import EffectState from "./components/UseEffectUseState.js";

function App() {
  return (
    <div>
      <img src={"images/llsollu.jpg"} alt='llsollu logo' className='logo' />
      <h1 className='header'> ezSMS (Demo)</h1> <br></br>
      <DateRanger />
      <br></br>
      <br></br>
      유저 조회:
      <UserInfo />
      <br></br>
      <div>
        createElement:
        <Element />
        <EffectState />
      </div>
    </div>
  );
}

export default App;

// 모달용 App.js
// https://velog.io/@jjunyjjuny/React-useState는-어떻게-동작할까
// https://react.dev/learn
// import React, { useState } from "react";
// import Modal from "./components/Modal";

// function App() {
//   // useState를 사용하여 open상태를 변경한다. (open일때 true로 만들어 열리는 방식)
//   const [modalOpen, setModalOpen] = useState(false);

//   const openModal = () => {
//     setModalOpen(true);
//   };
//   const closeModal = () => {
//     setModalOpen(false);
//   };

//   return (
//     <React.Fragment>
//       <button onClick={openModal}>모달팝업</button>
//       <Modal open={modalOpen} close={closeModal} header='Modal heading'>
//         함수형 모달 팝업창입니다. 쉽게 만들 수 있어요. 같이 만들어봐요!
//       </Modal>
//     </React.Fragment>
//   );
// }

// export default App;
