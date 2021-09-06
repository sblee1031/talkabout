import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";

export default function DebateSearch() {
  const { word } = useParams();
  console.log("-->", { word });
  const [list, setList] = useState({});
  //const [status, setStatus] = useState();
  const pageNo = 1;
  const pageSize = 5;
  const url = `http://localhost:9999/ta_back/debrecruit/list/${word}?start=${pageNo}&end=${pageSize}`;
  //console.log("url : ", url);
  useEffect(() => {
    fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log("--->", data);

        setList(data);
      });
  }, [url]);

  //console.log("---->", list);

  return (
    <>
      <div className="divButton" style={{ textAlign: "right", margin: "10px" }}>
        <Link
          className="write"
          style={{ textDecoration: "none", textDecorationColor: "black" }}
          to={"/ta_front/debrecruit/write"}
        >
          <Button className="buttons" variant="success">
            토론 작성
          </Button>
        </Link>
      </div>
      <Table responsive="xl" hover>
        <thead>
          <tr style={{ fontSize: "14pt" }}>
            <th style={{ width: "10%" }}>토론번호</th>
            <th style={{ width: "25%" }}>토론제목</th>
            <th style={{ width: "10%" }}>작성자</th>
            <th style={{ width: "15%" }}>작성시간</th>
            <th style={{ width: "10%" }}>토론시간</th>
            <th style={{ width: "10%" }}>진행상태</th>
          </tr>
        </thead>
        <tbody>
          {list.debatelist?.map((debate) => (
            <tr key={debate.debate_no}>
              <td>{debate.debate_no}</td>
              <td>
                <Link to={`/ta_front/debrecruit/${debate.debate_no}`}>
                  {debate.debate_topic}
                </Link>
              </td>
              <td>{debate.debate_writer.member_nickName}</td>
              <td>{debate.debate_date}</td>
              <td>{debate.debate_time}</td>
              <td>{debate.debate_status}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  );
}
