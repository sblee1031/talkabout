import { useState, useEffect } from "react";
import { Table, button, Image } from "react-bootstrap";
import "./css/battle.css";
import ApiService from "../ApiService";
import SockJsClient from "react-stomp";

import { Grid, Typography } from "@material-ui/core";
import Timer from "./Timer";

export default function DebateBattleDetail(props) {
  const [debate] = useState(props?.location?.state.debate);
  const [logininfo] = useState(props?.location?.state.logininfo);
  const [debateDetail, setdebateDetail] = useState();

  const [name] = useState(logininfo?.member_nickName);
  const [room] = useState(debate.debate_no);
  const [topics] = useState([`/topic/${room}`]);
  const [message, setMessage] = useState("");
  const [messages, setMessages] = useState([]);
  const [messages2, setMessages2] = useState([]);
  const [clientRef, setClientRef] = useState();

  const [evid_A1, setEvid_A1] = useState("");
  const [evid_A2, setEvid_A2] = useState("");
  const [evid_A3, setEvid_A3] = useState("");
  const [evid_B1, setEvid_B1] = useState("");
  const [evid_B2, setEvid_B2] = useState("");
  const [evid_B3, setEvid_B3] = useState("");

  const [audience, setAudience] = useState([]);
  const [votecnt, setVoteCnt] = useState();
  const [agree, setAgree] = useState(0);
  const [disAgree, setDisAgree] = useState(0);
  const [neutrality, setNeutrality] = useState(0);
  const [startStatus, setStartState] = useState(false);

  useEffect(() => {
    ApiService.fetchDiscussors(debate.debate_no).then((res) => {
      setdebateDetail(res.data.list);
      setEvid_A1(res.data.list[0].evi_one);
      setEvid_A2(res.data.list[0].evi_two);
      setEvid_A3(res.data.list[0].evi_three);
      setEvid_B1(res.data.list[1].evi_one);
      setEvid_B2(res.data.list[1].evi_two);
      setEvid_B3(res.data.list[1].evi_three);
    });
  }, []);

  useEffect(() => {
    const params = {
      deb_no: debate.debate_no, //
      mem_no: logininfo?.member_no, //
    };
    ApiService.fetchAudOneByTwo(params).then((res) => {
      if (res.data.audience == null) {
        ApiService.addVote(params).then(() => {
          console.log("audience 생성 완료");
          ApiService.fetchAudOneByTwo(params).then((res) => {
            setAudience(res.data.audience);
          });
        });
      } else {
        setAudience(res.data.audience);
      }
    });
  }, []);

  const sendMessage = () => {
    clientRef.sendMessage(
      `/app/sendMessage/${room}`,
      JSON.stringify({
        name: name,
        message: message,
        votecnt: votecnt,
        server: false,
      })
    );
    setMessage("");
  };

  // 근거 등록
  const handleChange = (e) => {
    const btn_no = e.target.className;
    if (btn_no == "evidence_A1") {
      setEvid_A1(e.target.value);
    } else if (btn_no == "evidence_A2") {
      setEvid_A2(e.target.value);
    } else if (btn_no == "evidence_A3") {
      setEvid_A3(e.target.value);
    } else if (btn_no == "evidence_B1") {
      setEvid_B1(e.target.value);
    } else if (btn_no == "evidence_B2") {
      setEvid_B2(e.target.value);
    } else if (btn_no == "evidence_B3") {
      setEvid_B3(e.target.value);
    }
  };

  const btn_a1 = (e) => {
    if (debateDetail[0].discussor.member_nickName == name) {
      const params = {
        detail_no: debateDetail[0].detail_no,
        word: "evidence",
        evi_no: 1,
        setdata: evid_A1,
      };

      ApiService.editDetail(params);

      alert("수정이 완료되었습니다.");
    } else {
      alert("토론자 A만 수정이 가능합니다.");
    }
    updateEvi(e);
  };

  const btn_a2 = (e) => {
    if (debateDetail[0].discussor.member_nickName == name) {
      const params = {
        detail_no: debateDetail[0].detail_no,
        word: "evidence",
        evi_no: 2,
        setdata: evid_A2,
      };

      ApiService.editDetail(params);

      alert("수정이 완료되었습니다.");
    } else {
      alert("토론자 A만 수정이 가능합니다.");
    }
    updateEvi(e);
  };

  const btn_a3 = (e) => {
    if (debateDetail[0].discussor.member_nickName == name) {
      const params = {
        detail_no: debateDetail[0].detail_no,
        word: "evidence",
        evi_no: 3,
        setdata: evid_A3,
      };

      ApiService.editDetail(params);

      alert("수정이 완료되었습니다.");
    } else {
      alert("토론자 A만 수정이 가능합니다.");
    }
    updateEvi(e);
  };

  const btn_b1 = (e) => {
    if (debateDetail[1].discussor.member_nickName == name) {
      const params = {
        detail_no: debateDetail[1].detail_no,
        word: "evidence",
        evi_no: 1,
        setdata: evid_B1,
      };

      ApiService.editDetail(params);

      alert("수정이 완료되었습니다.");
    } else {
      alert("토론자 B만 수정이 가능합니다.");
    }
    updateEvi(e);
  };

  const btn_b2 = (e) => {
    if (debateDetail[1].discussor.member_nickName == name) {
      const params = {
        detail_no: debateDetail[1].detail_no,
        word: "evidence",
        evi_no: 2,
        setdata: evid_B2,
      };

      ApiService.editDetail(params);

      alert("수정이 완료되었습니다.");
    } else {
      alert("토론자 B만 수정이 가능합니다.");
    }
    updateEvi(e);
  };

  const btn_b3 = (e) => {
    if (debateDetail[1].discussor.member_nickName == name) {
      const params = {
        detail_no: debateDetail[1].detail_no,
        word: "evidence",
        evi_no: 3,
        setdata: evid_B3,
      };

      ApiService.editDetail(params);

      alert("수정이 완료되었습니다.");
    } else {
      alert("토론자 B만 수정이 가능합니다.");
    }
    updateEvi(e);
  };

  // 투표 Update
  const setVote = (e) => {
    console.log(e.target.value);
    setMessage("");
    const params = {
      audi_no: audience.audi_no,
      vote_no: e.target.value, //
    };
    console.log(params);
    ApiService.editVote(params).then((res) => {
      console.log("투표 수정 완료");
      updateEvi(e);
      alert("투표되었습니다.");
    });
  };

  const updateEvi = (e) => {
    setTimeout(() => {
      clientRef.sendMessage(
        `/app/sendMessage/${room}`,
        JSON.stringify({
          name: null,
          message: null,
        })
      );
    }, 1000);
  };

  // 시작 버튼 클릭
  const setStart = (e) => {
    if (
      debateDetail[0].discussor.member_nickName == name ||
      debateDetail[1].discussor.member_nickName == name
    ) {
      // http://localhost:9999/ta_back/debbattle/debatedetail/상세번호
      // params : "detail_no", "word", "evi_no", "setdata"
      const params = {
        detail_no: debateDetail[0].detail_no,
        word: "intime", //
      };
      ApiService.editDetail(params).then(() => {
        alert("토론이 시작되었습니다.");
        // setStartState((startStatus) => !startStatus);
        setStartState(true);
      });
    } else {
      alert("토론자만 시작 가능합니다.");
    }
  };

  // 종료 버튼 클릭
  const setEnd = (e) => {
    if (
      debateDetail[0].discussor.member_nickName == name ||
      debateDetail[1].discussor.member_nickName == name
    ) {
      const params = {
        deb_no: debate.debate_no,
        word: "enddate",
      };
      ApiService.editDebate(params).then(() => {
        alert("토론이 종료되었습니다.");
        const params2 = {
          deb_no: debate.debate_no,
          word: "status",
          setdata: "종료",
        };
        ApiService.editDebate(params2);
        setStartState(false);
      });
    } else {
      alert("토론자만 종료 가능합니다.");
    }
  };

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
                        debateDetail
                          ? debateDetail[0].discussor.member_thumb
                          : ""
                      }
                      style={{ height: "120px", marginLeft: "20px" }}
                      alt={"썸네일"}
                      roundedCircle
                    />
                    <br></br>
                    토론자 A :{" "}
                    {debateDetail
                      ? debateDetail[0].discussor.member_nickName
                      : ""}
                  </div>
                </td>
              </tr>
            </thead>
            <tbody className="table-light">
              <tr>
                <td>근거 1</td>
                <td>
                  <button
                    className="btn btn-info"
                    onClick={() => {
                      btn_a1();
                    }}
                  >
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A" colSpan="2">
                  <textarea
                    className="evidence_A1"
                    value={evid_A1}
                    type="text"
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>근거 2</td>
                <td>
                  <button
                    className="btn btn-info"
                    onClick={() => {
                      btn_a2();
                    }}
                  >
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A" colSpan="2">
                  <textarea
                    className="evidence_A2"
                    value={evid_A2}
                    type="text"
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>근거 3</td>
                <td>
                  <button
                    className="btn btn-info"
                    onClick={() => {
                      btn_a3();
                    }}
                  >
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_A" colSpan="2">
                  <textarea
                    className="evidence_A3"
                    value={evid_A3}
                    type="text"
                    onChange={handleChange}
                  />
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
                    {debate.debate_topic}
                  </div>
                </td>
              </tr>
            </thead>
            <tbody className="table-light">
              <tr>
                <td colSpan="3">
                  <div id="" className="battle-timer">
                    {debate.debate_time}분
                    <Timer
                      hour={debate.debate_time / 60}
                      min={debate.debate_time % 60}
                      sec={0}
                      status={startStatus}
                    />
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="3">
                  <div className="battle_vote">
                    {/* <div>{messages}</div> */}
                    {debateDetail ? debateDetail[0].discuss : ""} {agree} 표 /
                    중립 {neutrality} 표 /{" "}
                    {debateDetail ? debateDetail[1].discuss : ""} {disAgree}표
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
                        return debateDetail &&
                          e.name ==
                            debateDetail[0]?.discussor.member_nickName ? (
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
                  <div className="set_time-group">
                    <button
                      id="start"
                      className="btn btn-primary btn_settime"
                      onClick={() => {
                        setStart();
                      }}
                    >
                      시작
                    </button>
                    <button
                      id="end"
                      className="btn btn-secondary btn_settime"
                      onClick={() => {
                        setEnd();
                      }}
                    >
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
                        );
                      })}
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td colSpan="3">
                  <div className="vote_div_group">
                    <button
                      className="btn btn-info btn_vote"
                      value="1"
                      onClick={(e) => {
                        setVote(e);
                      }}
                    >
                      {debateDetail ? debateDetail[0].discuss : ""}
                    </button>
                    <button
                      className="btn btn-success btn_vote"
                      value="2"
                      onClick={(e) => {
                        setVote(e);
                      }}
                    >
                      중립
                    </button>
                    <button
                      className="btn btn-danger btn_vote"
                      value="3"
                      onClick={(e) => {
                        setVote(e);
                      }}
                    >
                      {debateDetail ? debateDetail[1].discuss : ""}
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
                        debateDetail
                          ? debateDetail[1].discussor.member_thumb
                          : ""
                      }
                      style={{ height: "120px", marginLeft: "20px" }}
                      alt={"썸네일"}
                      roundedCircle
                    />
                    <br></br>
                    토론자 B :{" "}
                    {debateDetail
                      ? debateDetail[1].discussor.member_nickName
                      : ""}
                  </div>
                </td>
              </tr>
            </thead>
            <tbody className="table-light">
              <tr>
                <td>근거 1</td>
                <td>
                  <button
                    className="btn btn-info"
                    onClick={() => {
                      btn_b1();
                    }}
                  >
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_B" colSpan="2">
                  <textarea
                    className="evidence_B1"
                    value={evid_B1}
                    type="text"
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>근거 2</td>
                <td>
                  <button
                    className="btn btn-info"
                    onClick={() => {
                      btn_b2();
                    }}
                  >
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_B" colSpan="2">
                  <textarea
                    className="evidence_B2"
                    value={evid_B2}
                    type="text"
                    onChange={handleChange}
                  />
                </td>
              </tr>
              <tr>
                <td>근거 3</td>
                <td>
                  <button
                    className="btn btn-info"
                    onClick={() => {
                      btn_b3();
                    }}
                  >
                    등록
                  </button>
                </td>
              </tr>
              <tr>
                <td className="evidence_B" colSpan="2">
                  <textarea
                    className="evidence_B3"
                    value={evid_B3}
                    type="text"
                    onChange={handleChange}
                  />
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
              votecnt: votecnt,
              server: true,
            })
          );
        }}
        onDisconnect={() => {
          console.log("disconnected");
        }}
        onMessage={(e) => {
          console.log("e", e);
          //투표 반영
          if (e.votecnt) {
            setAgree(e.votecnt.agree);
            setNeutrality(e.votecnt.neutrality);
            setDisAgree(e.votecnt.disagree);
          }
          //근거 업데이트
          if (e.name == null) {
            console.log("name null");
            console.log("e ddlist:", e.ddList);
            setEvid_A1(e.ddList[0].evi_one);
            setEvid_A2(e.ddList[0].evi_two);
            setEvid_A3(e.ddList[0].evi_three);
            setEvid_B1(e.ddList[1].evi_one);
            setEvid_B2(e.ddList[1].evi_two);
            setEvid_B3(e.ddList[1].evi_three);
          } // 토론자일 경우
          else if (
            e.name == debateDetail[0].discussor.member_nickName ||
            e.name == debateDetail[1].discussor.member_nickName
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
