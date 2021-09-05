import { useRef, useState, useEffect } from "react";
import { Table, button, Image } from "react-bootstrap";
import "./css/battle.css";
import ApiService from "../ApiService";
import SockJsClient from "react-stomp";

import { Button, TextField, Grid, Typography } from "@material-ui/core";

export default function DebateBattleDetail(props) {
  // console.log("detail-logininfo : ", props.logininfo);
  const debate = props?.location?.state.debate;
  const logininfo = props?.location?.state.logininfo;
  const [debDetail, setDebDetail] = useState();

  console.log("상속 데이터 : ", props.location.state.debate);

  const [name] = useState(logininfo?.member_nickName); // logininfo?.member_nickName
  console.log("이름 : ", name);
  const [room] = useState(debate.debate_no);
  const [topics, setTopics] = useState([`/topic/${room}`]);
  const [message, setMessage] = useState("");
  const [messages, setMessages] = useState([]);
  const [messages2, setMessages2] = useState([]);
  const [clientRef, setClientRef] = useState();
  const [inputMessage, setInputMessage] = useState("");

  const sendMessage = () => {
    clientRef.sendMessage(
      `/app/sendMessage/${room}`,
      JSON.stringify({
        name: name,
        message: message,
        server: false,
      })
    );
    setMessage("");
  };

  useEffect(() => {
    ApiService.fetchDiscussors(debate.debate_no).then((res) => {
      setDebDetail(res.data.list);
    });
  }, []);

  console.log("디테일 : ", debDetail);

  return (
    <>
      <div className="wrapper">
        <div className="discussor1">
          <Table hover>
            <thead className="table-info">
              <tr>
                <td colSpan="2">
                  <div>
                    <Image
                      src={
                        "http://k.kakaocdn.net/dn/E1SPk/btq7PTNrZT0/Kd0PxOFVNqK7F8w6M6wRaK/img_640x640.jpg"
                      }
                      style={{ height: "120px", marginLeft: "20px" }}
                      alt={"썸네일"}
                      roundedCircle
                    />
                    <br></br>
                    토론자 A : {"앙리"}
                  </div>
                </td>
              </tr>
            </thead>
            <tbody className="table-light">
              <tr>
                <td>근거 1</td>
                <td>
                  <button className="btn btn-info">등록</button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A" colSpan="2">
                  <textarea className="evidence_A1" type="text" />
                </td>
              </tr>
              <tr>
                <td>근거 2</td>
                <td>
                  <button className="btn btn-info btn_evid" value="A2">
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A2" colSpan="2">
                  <textarea className="evidence_A2" type="text" />
                </td>
              </tr>
              <tr>
                <td>근거 3</td>
                <td>
                  <button className="btn btn-info btn_evid" value="A3">
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A3" colSpan="2">
                  <textarea className="evidence_A3" type="text" />
                </td>
              </tr>
            </tbody>
          </Table>
        </div>
        <div className="chatting">
          <Table hover>
            <thead className="table-success">
              <tr>
                <td colSpan="3">
                  <div id="" className="battle_topic">
                    호날두 vs 메시
                  </div>
                </td>
              </tr>
            </thead>
            <tbody className="table-light">
              <tr>
                <td colSpan="3">
                  <div id="" className="battle-timer">
                    00:00<span className="sec-style">:00</span>
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="3">
                  <div className="battle_vote">
                    호날두 XX% / 중립 XX% / 메시 XX%
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="3">
                  <div style={{ marginBottom: "5px" }}>토론자 채팅</div>
                  <div
                    id="discussorWindow"
                    style={{
                      height: "145px",
                      overflowY: "auto",
                      display: "flex",
                      flexDirection: "column-reverse",
                    }}
                  >
                    <div style={{}}>
                      {messages.map((e, i) => {
                        return e.name ==
                          debDetail[0].discussor.member_nickName ? (
                          <div className="discussor1message" key={i}>
                            {e.server ? (
                              <Grid
                                item
                                style={{
                                  padding: "10px",
                                  fontStyle: "italic",
                                  textAlign: "center",
                                }}
                                key={i}
                              >
                                <Typography style={{ fontWeight: "800" }}>
                                  {e.message}
                                </Typography>
                              </Grid>
                            ) : (
                              <Grid
                                item
                                style={{
                                  padding: "10px",
                                  wordWrap: "break-word",
                                }}
                                key={i}
                              >
                                <Typography>
                                  {e.name}
                                  <br />
                                  {e.message}
                                </Typography>
                              </Grid>
                            )}
                          </div>
                        ) : (
                          <div className="discussor2message" key={i}>
                            {e.server ? (
                              <Grid
                                item
                                style={{
                                  padding: "10px",
                                  fontStyle: "italic",
                                  textAlign: "center",
                                }}
                                key={i}
                              >
                                <Typography style={{ fontWeight: "800" }}>
                                  {e.message}
                                </Typography>
                              </Grid>
                            ) : (
                              <Grid
                                item
                                style={{
                                  padding: "10px",
                                  wordWrap: "break-word",
                                }}
                                key={i}
                              >
                                <Typography>
                                  {e.name}
                                  <br /> {e.message}
                                </Typography>
                              </Grid>
                            )}
                          </div>
                        );
                      })}
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="3">
                  <div className="set_time-group">
                    <button id="start" className="btn btn-primary btn_settime">
                      시작
                    </button>
                    <button id="end" className="btn btn-secondary btn_settime">
                      종료
                    </button>
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="3">
                  <div>중계방 채팅</div>
                  <div
                    id="audienceWindow"
                    style={{
                      height: "140px",
                      overflowY: "auto",
                      display: "flex",
                      flexDirection: "column-reverse",
                    }}
                  >
                    <div style={{ textAlign: "left" }}>
                      {messages2.map((e, i) => {
                        return (
                          <div key={i}>
                            {e.server ? (
                              <Grid
                                item
                                style={{
                                  padding: "10px",
                                  fontStyle: "italic",
                                }}
                                key={i}
                              >
                                <Typography
                                  style={{
                                    fontWeight: "800",
                                    textAlign: "center",
                                  }}
                                >
                                  {e.message}
                                </Typography>
                              </Grid>
                            ) : (
                              <Grid item style={{ padding: "10px" }} key={i}>
                                <Typography style={{ wordWrap: "break-word" }}>
                                  {e.name}
                                  <br />
                                  {e.message}
                                </Typography>
                              </Grid>
                            )}
                          </div>
                        );
                      })}
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="3">
                  <div className="vote_div_group">
                    <button className="btn btn-info btn_vote" value="1">
                      찬성
                    </button>
                    <button className="btn btn-success btn_vote" value="2">
                      중립
                    </button>
                    <button className="btn btn-danger btn_vote" value="3">
                      반대
                    </button>
                  </div>
                </td>
              </tr>
              <tr>
                <td>
                  <input
                    id="user"
                    type="text"
                    value={logininfo?.member_nickName}
                    style={{
                      width: "70px",
                      border: "none",
                      fontSize: "10pt",
                      fontStyle: "italic",
                      fontWeight: "800",
                      color: "darkslategrey",
                    }}
                    readOnly="readonly"
                  />
                </td>
                <td>
                  <input
                    id="textMessage"
                    type="text"
                    style={{ width: "100%" }}
                    value={message}
                    onChange={(e) => {
                      setMessage(e.target.value);
                    }}
                    onKeyPress={(e) => {
                      console.log(e.key);
                      if (e.key == "Enter") {
                        sendMessage();
                      }
                    }}
                  />
                </td>
                <td>
                  <button
                    className="btn btn-success"
                    onClick={() => {
                      sendMessage();
                    }}
                  >
                    전송
                  </button>
                </td>
              </tr>
            </tbody>
          </Table>
        </div>
        <div className="discussor2">
          <Table hover>
            <thead className="table-secondary">
              <tr>
                <td colSpan="2">
                  <div>
                    <Image
                      src={
                        "http://k.kakaocdn.net/dn/bqciso/btq4dKTf6Qu/NX3jJ6l74awvHHKr32vCRK/img_640x640.jpg"
                      }
                      style={{ height: "120px", marginLeft: "20px" }}
                      alt={"썸네일"}
                      roundedCircle
                    />
                    <br></br>
                    토론자 B : {"앙리"}
                  </div>
                </td>
              </tr>
            </thead>
            <tbody className="table-light">
              <tr>
                <td>근거 1</td>
                <td>
                  <button className="btn btn-secondary">등록</button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A" colSpan="2">
                  <textarea className="evidence_A1" type="text" />
                </td>
              </tr>
              <tr>
                <td>근거 2</td>
                <td>
                  <button className="btn btn-secondary btn_evid" value="A2">
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A2" colSpan="2">
                  <textarea className="evidence_A2" type="text" />
                </td>
              </tr>
              <tr>
                <td>근거 3</td>
                <td>
                  <button className="btn btn-secondary btn_evid" value="A3">
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A3" colSpan="2">
                  <textarea className="evidence_A3" type="text" />
                </td>
              </tr>
            </tbody>
          </Table>
        </div>
      </div>
      <SockJsClient
        url="http://localhost:9999/ta_back/websocket-chat/"
        topics={topics}
        onConnect={() => {
          console.log("connected");
          clientRef.sendMessage(
            `/app/addUser/${room}`,
            JSON.stringify({
              name: name,
              message: name + " has connected!",
              server: true,
            })
          );
        }}
        onDisconnect={() => {
          console.log("disconnected");
        }}
        onMessage={(e) => {
          // console.log(e)
          // 토론자일 경우
          if (
            name == debDetail[0].discussor.member_nickName ||
            name == debDetail[1].discussor.member_nickName
          ) {
            const temp = [...messages];
            temp.push(e);
            setMessages(temp);
          }
          // 관중일 경우
          else {
            const temp = [...messages2];
            temp.push(e);
            setMessages2(temp);
          }
        }}
        ref={(client) => {
          setClientRef(client);
        }}
      />
    </>
  );
}
