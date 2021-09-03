import { Table, button } from "react-bootstrap";
import "./css/battle.css";
export default function battle(props) {
  return (
    <>
      <div className="wrapper">
        <div className="discussor1">
          <Table hover>
            <thead className="table-info">
              <tr>
                <td colspan="2">근거 목록</td>
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
                <td className="evidence_A" colspan="2">
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
                <td className="evidence_A2" colspan="2">
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
                <td className="evidence_A3" colspan="2">
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
                <td colspan="3">
                  <div id="" className="battle_topic">
                    호날두 vs 메시
                  </div>
                </td>
              </tr>
            </thead>
            <tbody className="table-light">
              <tr>
                <td colspan="3">
                  <div id="" className="battle-timer">
                    00:00<span className="sec-style">:00</span>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="3">
                  <div className="battle_vote">
                    호날두 XX% / 중립 XX% / 메시 XX%
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="3">
                  <div id="discussorWindow">
                    <div>토론자 채팅</div>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="3">
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
                <td colspan="3">
                  <div id="audienceWindow">
                    <div>중계방 채팅</div>
                  </div>
                </td>
              </tr>
              <tr>
                <td colspan="3">
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
                  <input id="user" type="text" value="A" readonly="readonly" />
                </td>
                <td>
                  <input id="textMessage" type="text" />
                </td>
                <td>
                  <button className="btn btn-success">전송</button>
                </td>
              </tr>
            </tbody>
          </Table>
        </div>
        <div className="discussor2">
          <Table hover>
            <thead className="table-secondary">
              <tr>
                <td colspan="2">근거 목록</td>
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
                <td className="evidence_A" colspan="2">
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
                <td className="evidence_A2" colspan="2">
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
                <td className="evidence_A3" colspan="2">
                  <textarea className="evidence_A3" type="text" />
                </td>
              </tr>
            </tbody>
          </Table>
        </div>
      </div>
    </>
  );
}
