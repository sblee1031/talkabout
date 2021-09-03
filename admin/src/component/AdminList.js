import { useEffect, useState } from "react";
import { Route, useHistory } from "react-router-dom";
import Notice from "./Notice";
import Board from "./Board";
import DebRecruit from "./DebRecruit";
import DebBattle from "./DebBattle";
import DebResult from "./DebResult";
import { Dropdown, DropdownButton } from "react-bootstrap";
import "./css/bootstrap.css";

export default function AdminList(props) {
  //console.log(props);
  const [selectList, setSelectList] = useState("notice");
  const url = "/ta_front/admin/" + selectList;
  const history = useHistory();
  const select = (e) => {
    // console.log("select=>", e);
    setSelectList(e);
  };
  useEffect(() => {
    // console.log("Effect", selectList);
    history.push(url);
  }, [url]);

  return (
    <>
      <h1>관리자 {props.userInfo}님 환영합니다.</h1>
      <div style={{ display: "inline", textAlign: "right" }}>
        <h3>
          게시판 선택
          <DropdownButton
            style={{ margin: "20px 20px" }}
            id="dropdown-basic-button"
            title="게시판"
          >
            <Dropdown.Item
              onClick={() => {
                select("notice");
              }}
            >
              공지사항
            </Dropdown.Item>
            <Dropdown.Item
              onClick={() => {
                select("board");
              }}
            >
              자유게시판
            </Dropdown.Item>
            <Dropdown.Item
              onClick={() => {
                select("debrecruite");
              }}
            >
              토론관리
            </Dropdown.Item>
            {/* <Dropdown.Item
                onClick={() => {
                  select("debbattle");
                }}
              >
                토론배틀
              </Dropdown.Item> */}
            {/* <Dropdown.Item
                onClick={() => {
                  select("debresult");
                }}
              >
                토론결과
              </Dropdown.Item> */}
          </DropdownButton>
        </h3>
      </div>
      <div id="view">
        <Route path="/ta_front/admin/notice">
          <Notice userInfo={props.userInfo} />
        </Route>
        <Route path="/ta_front/admin/board">
          <Board userInfo={props.userInfo} />
        </Route>
        <Route path="/ta_front/admin/debrecruite">
          <DebRecruit userInfo={props.userInfo} />
        </Route>
        <Route path="/ta_front/admin/debbattle">
          <DebBattle userInfo={props.userInfo} />
        </Route>
        <Route path="/ta_front/admin/debresult">
          <DebResult userInfo={props.userInfo} />
        </Route>
      </div>
    </>
  );
}
