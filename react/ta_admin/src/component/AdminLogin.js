import { useRef } from "react";
import { useHistory } from "react-router-dom";
import "./css/AdminLogin.css";
export default function AdminLogin(props) {
  const history = useHistory();
  const id = useRef(null);
  const pwd = useRef(null);
  const login = () => {
    console.log("클릭", id.current.value, pwd.current.value);
    fetch("http://localhost:9999/ta_back/admin/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        admin_id: id.current.value,
        admin_pwd: pwd.current.value,
      }),
      credentials: "include",
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log("--->", data);
        if (data.status === 1) {
          alert("성공");
          props.setUserInfo(data.loginInfo);
          history.push("/ta_front/admin");
        } else if (data.status === 0) {
          alert("정보가 일치 하지 않습니다.");
        }
        //history.push("/ta_front/debrecruit.html");
        // console.log("로그인완료", loginInfo);
      });
  };
  return (
    <>
      <h1>관리자 페이지</h1>
      <div className="login" style={{ textAlign: "center" }}>
        <div className="loginBox">
          <div className="col">
            <div className="inputBox">
              <input type="text" name="id" ref={id} required="required" />
              <span className="text">관리자 아이디</span>
              <span className="line"></span>
            </div>
          </div>
          <div className="col">
            <div className="inputBox">
              <input type="password" name="pwd" ref={pwd} required="required" />
              <span className="text">비밀번호</span>
              <span className="line"></span>
            </div>
          </div>
          <button className="btn btn-success loginbutton" onClick={login}>
            로그인
          </button>
        </div>
      </div>
    </>
  );
}
