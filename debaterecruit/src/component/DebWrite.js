import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import "./css/debateWrite.css";
import { Button, Form, Alert } from "react-bootstrap";
import { Link, useHistory } from "react-router-dom";
import React, { useEffect, useState } from "react";
import Datepick from "./Datepick";
import "react-datepicker/dist/react-datepicker.css";
// import AlertDismissible from "./AlertDismissible";

export default function DebWrite(props) {
  const debate = props?.location?.state.debate;
  const detail = props?.location?.state.detail;
  const [debateTime, setDebateTime] = useState("30");
  const [debateDate, setDebateDate] = useState();
  const [pickerDate, setPickerDate] = useState();
  const [discuss1, setDiscuss1] = useState("");
  const [discuss2, setDiscuss2] = useState("");
  const [alertMent, setAlertMent] = useState("");
  const [buttonState, setButtonState] = useState();
  // const editDiscuss1 = detail[0]?.discuss;
  // const editDiscuss2 = detail[1]?.discuss;

  const [editData, setEditData] = useState("");
  // const [ckeditor, setCkeditor] = useState({}); //ckeditor 객체

  const [show, setShow] = useState(false);
  const history = useHistory();

  const [loginInfo, setLoginInfo] = useState();
  const [writeButton, setwriteButton] = useState(false);
  const ocDebateDate = (e) => {
    //시작날짜 설정
    //  console.log(getCurrentDate(e));
    //const year = e.getYears
    //setDebateDate(getCurrentDate(e));
    // console.log("debateDate", debateDate);
    // console.log("pickerDate", pickerDate);
  };
  const getPickerDate = (date) => {
    setDebateDate(getCurrentDate(date));
    // console.log(
    //   "getPickerDate",
    //   date,
    //   getCurrentDate(date),
    //   "=>",
    //   new Date(getCurrentDate(date))
    // );
  };

  useEffect(() => {
    setButtonState(true);
    const edit = () => {
      if (props?.location) {
        //편집
        setPickerDate(new Date(debate.debate_startDate));
        setButtonState(false);
        setDiscuss1(detail[0]?.discuss);
        setDiscuss2(detail[1]?.discuss);
        setDebateTime(debate?.debate_time + "");
        setEditData(debate?.debate_content);
        // console.log("setDebateDate", new Date(debate?.debate_startDate));
        setDebateDate(new Date(debate?.debate_startDate));
        // discuss2 = detail[1]?.discuss;
        // console.log("편집", props?.location.state);
        // console.log("=>", detail[0]);
        // console.log(
        //   //
        //   "useState=>",
        //   debateDate,
        //   editData,
        //   debateTime,
        //   discuss1,
        //   discuss2
        // );
      }
    };
    edit();
  }, [props]);

  const ocDiscuss1 = (e) => {
    //console.log(discuss1);
    setDiscuss1(e.target.value);
  };
  const ocDiscuss2 = (e) => {
    setDiscuss2(e.target.value);
  };
  const debWrite = (Event) => {
    Event.preventDefault();
    //console.log(Event.target);
    // console.log(discuss1, " / ", discuss2);
    // console.log("에디터->", editData);
    if (
      debateDate === "" ||
      editData === "" ||
      discuss1 === "" ||
      discuss2 === ""
    ) {
      // AlertDismissibleExample();
      setAlertMent("내용을 모두 입력해주세요.");
      setShow(true);
      // alert("모두 입력해주세요");
    } else {
      const Debate = {
        //debate_no: 1,
        debate_content: editData,
        debate_topic: discuss1 + " VS " + discuss2,
        discuss1: discuss1,
        discuss2: discuss2,
        debateDate: debateDate,
        debateTime: debateTime,
      };
      // const detail = { discuss: discuss1 };
      //const data = { Debate, detail };
      //console.log("작성하기=>", Debate);

      const url = `http://localhost:9999/ta_back/debrecruit/write`;
      fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(Debate),
        credentials: "include",
      })
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          if (data.status == 1) {
            // console.log("결과->", data);
            history.push("/ta_front/debrecruit.html");
          } else if (data.status == 0) {
            alert("작성실패");
          }
        });
      // console.log(Debate);
    }
  };
  const debModify = (e) => {
    //토론 수정
    // console.log(e);
    // Event.preventDefault();
    //console.log(Event.target);
    // console.log(discuss1, " / ", discuss2);
    // console.log("에디터->", editData);
    // console.log(debateDate, getCurrentDate(debateDate));
    if (
      getCurrentDate(debateDate) === debate.debate_startDate &&
      editData === debate.debate_content &&
      debateTime === debate.debate_time &&
      discuss1 === detail[0].discuss &&
      discuss2 === detail[1].discuss
    ) {
      // AlertDismissibleExample();
      setAlertMent("수정할 내용이 없습니다.");
      setShow(true);
      // alert("모두 입력해주세요");
    } else {
      const Debate = {
        debate_no: debate.debate_no,
        debate_content: editData,
        debate_topic: discuss1 + " VS " + discuss2,
        discuss1_no: detail[0].detail_no,
        discuss2_no: detail[1].detail_no,
        discuss1: discuss1,
        discuss2: discuss2,
        debateDate: getCurrentDate(debateDate),
        debateTime: debateTime,
      };

      const url = `http://localhost:9999/ta_back/debrecruit/update`;
      // console.log("수정내용=>", Debate);
      fetch(url, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(Debate),
        credentials: "include",
      })
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          if (data.status == 1) {
            // console.log("결과->", data);
            history.push("/ta_front/debrecruit.html");
          } else if (data.status == 0) {
            alert("작성실패");
          }
        });
      // console.log("Debate", Debate);
    }
  };
  const changeEditor = (event, editor) => {
    const data = editor.getData();
    setEditData(data);
    // console.log(data);
    //console.log({ event, editor, data });
  };
  function getCurrentDate(e) {
    //현재시간 구하는 함수
    var date = new Date(e);
    var year = date.getFullYear().toString();
    var month = date.getMonth() + 1;
    month = month < 10 ? "0" + month.toString() : month.toString();
    var day = date.getDate();
    day = day < 10 ? "0" + day.toString() : day.toString();
    var hour = date.getHours();
    hour = hour < 10 ? "0" + hour.toString() : hour.toString();
    if (Number.parseInt(hour) + 1 === 24) {
      hour = "00";
    }
    var minites = date.getMinutes();
    minites = minites < 10 ? "0" + minites.toString() : minites.toString();

    return year + "-" + month + "-" + day + " " + hour + ":" + minites + ":00"; // 현재시간보다 1시간 추가 ,최소 시작시간은 한시간 뒤부터 가능.
  }
  const ocDebateTime = (e) => {
    //토론제한시간 설정..
    setDebateTime(e.target.value);
    // console.log("e.target", e.target.value);
  };
  function login() {
    // const mem = { member_social_no: "118153287897731040607" };
    fetch(
      "http://localhost:9999/ta_back/member/login?socialNo=118153287897731040607",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          member_social_no: "118153287897731040607",
        }),
        credentials: "include",
      }
    )
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setLoginInfo(data.member);
        // console.log("--->", data);
        // console.log("로그인완료", loginInfo);
        if (loginInfo) {
          setwriteButton(writeButton);
        } else {
          setwriteButton(false);
        }
      });
  }

  return (
    <>
      {/* <button onClick={login}>로긴</button> */}
      {/* https://www.youtube.com/watch?v=_-vCsD7jHh4 */}
      {/* <button onClick={click}>버튼</button> */}
      <Alert show={show} variant="warning">
        <Alert.Heading>{alertMent}</Alert.Heading>
        <p style={{ fontSize: "13pt" }}>
          토론 일자, 주장, 내용을 확인해주세요^___^
        </p>
        <div className="d-flex justify-content-end">
          <Button
            onClick={() => setShow(false)}
            variant="outline-danger"
            style={{ border: "none" }}
          >
            Close
          </Button>
        </div>
      </Alert>
      <div className="writeView">
        <form onSubmit={debWrite}>
          <div className="debDate" style={{ fontSize: "20pt" }}>
            <label className="labelDebDate" style={{ marginRight: "130px" }}>
              토론일자
              <Datepick
                setPickerDate={pickerDate}
                // onChange={setDate1}
                // setDate={ocDebateDate}
                // startDate={debateDate}
                selectedDate={getPickerDate}
              />
            </label>
            <label className="labelDebDate">
              토론제한시간
              <br />
              <Form.Group
                controlId="exampleForm.SelectCustom"
                style={{ marginTop: "10px" }}
              >
                <Form.Control
                  as="select"
                  onChange={ocDebateTime}
                  value={debateTime}
                  custom
                >
                  <option value={30}>30분</option>
                  <option value={60}>60분</option>
                  <option value={120}>120분</option>
                </Form.Control>
              </Form.Group>
              {/* <select
                id="selectDebate_time"
                name="selectDebate_time"
                onChange={ocDebateTime}
                value={debateTime}
              >
                <option value="30">30분</option>
                <option value="60">60분</option>
                <option value="120">120분</option>
              </select> */}
            </label>
          </div>
          <div className="divDiscuss" style={{ width: "100%" }}>
            <label className="labelDiscuss">
              <div className="col">
                <div className="inputBox">
                  <Form.Control
                    type="text"
                    className="inputDiscuss1"
                    name="discuss1"
                    style={{
                      width: "100%",
                      textAlign: "center",
                      fontSize: "15pt",
                      fontWeight: "800",
                    }}
                    onChange={ocDiscuss1}
                    value={discuss1}
                    required="required"
                  />
                  <span className="text">주장1</span>
                  <span className="line"></span>
                </div>
              </div>
              {/* <input
                className="inputDiscuss1"
                name="discuss1"
                style={{ width: "100%" }}
                onChange={ocDiscuss1}
                vlaue={discuss1}
              ></input> */}
            </label>
            <label className="vs"> VS </label>
            <label className="labelDiscuss">
              <div className="col">
                <div className="inputBox">
                  <Form.Control
                    type="text"
                    className="inputDiscuss2"
                    name="discuss2"
                    style={{
                      width: "100%",
                      textAlign: "center",
                      fontSize: "15pt",
                      fontWeight: "800",
                    }}
                    onChange={ocDiscuss2}
                    value={discuss2}
                    required="required"
                  />
                  <span className="text">주장2</span>
                  <span className="line"></span>
                </div>
              </div>
              {/* <input
                className="inputDiscuss2"
                name="discuss2"
                style={{ width: "100%" }}
                onChange={ocDiscuss2}
                vlaue={discuss2}
              ></input> */}
            </label>
          </div>
          <div className="divEditor" style={{ minHeight: "100px" }}>
            <CKEditor
              editor={ClassicEditor}
              data={debate?.debate_content}
              config={{
                toolbar: {
                  items: [
                    "heading",
                    "|",

                    "bold",
                    "italic",

                    "link",
                    "bulletedList",
                    "numberedList",
                    "|",

                    "blockQuote",
                    "insertTable",
                    "mediaEmbed",
                    "undo",
                    "redo",
                  ],
                },

                // toolbar: [
                //   "heading",
                //   "|",
                //   "bold",
                //   "italic",
                //   "link",
                //   "bulletedList",
                //   "numberedList",
                //   "|",
                //   "outdent",
                //   "indent",
                //   "|",
                //   "blockQuote",
                //   "insertTable",
                //   "mediaEmbed",
                //   "undo",
                //   "redo",
                // ],
                placeholder: "내용을 입력해주세요",
              }}
              onReady={(editor) => {
                //setCkeditor(editor);
                //editor.isReadOnly = { readOnly };
                // You can store the "editor" and use when it is needed.
                //console.log("Editor is ready to use!", editor);
              }}
              onChange={changeEditor}
              // onBlur={(event, editor) => {
              //   console.log("Blur.", editor);
              // }}
              // onFocus={(event, editor) => {
              //   console.log("Focus.", editor);
              // }}
            />
          </div>
          <div
            className="divWriteButton"
            style={{ textAlign: "right", display: "inline-block" }}
          >
            {buttonState ? (
              <Button
                className="buttonWrite"
                variant="outline-success"
                size="sm"
                style={{ margin: "10px" }}
                type="submit"
              >
                작성하기
              </Button>
            ) : (
              <Button
                className="buttonWrite"
                variant="outline-success"
                size="sm"
                style={{ margin: "10px" }}
                onClick={debModify}
              >
                수정하기
              </Button>
            )}
            <Link to="/ta_front/debrecruit.html">
              <Button
                className="buttonBack"
                variant="outline-success"
                size="sm"
                style={{ margin: "10px" }}
              >
                돌아가기
              </Button>
            </Link>
          </div>
        </form>
      </div>
    </>
  );
}
