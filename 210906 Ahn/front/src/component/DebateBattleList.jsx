import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { InputGroup, FormControl } from "react-bootstrap";
import ApiService from "../ApiService";

import Pagination from "./pagination/DebatePaging";
import { paginate } from "./pagination/paginate";

export default function DebateBattleList(props) {
  const [deblist, setDebList] = useState();
  const [word, setWord] = useState();
  const [currentPage, setCurpage] = useState(1);
  const [logininfo, setLogininfo] = useState();

  // 페이징 기능
  const pageSize = 5;
  const pagedDebate = paginate(deblist, currentPage, pageSize);

  const deb_length = deblist?.length;

  function handlePageChange(page) {
    setCurpage(page);
  }

  // 검색 기능
  const search = (e) => {
    setWord(e.target.value);
  };

  const btnsearch = (e) => {
    if (word) {
      setCurpage(1);
      let result = [];

      result = deblist.filter((data) => {
        return data.debate_topic.search(word) !== -1;
      });
      setDebList(result);
    } else {
      e.preventDefault();
      alert("검색어를 입력해주세요");
    }
  };

  useEffect(() => {
    ApiService.fetchDebates().then((res) => {
      setDebList(res.data.list);
      // console.log(props);
      // props.setLoginInfo(res.data.logininfo);
      setLogininfo(res.data.logininfo);
    });
  }, []);

  //로그인 테스트
  function login() {
    // const mem = { member_social_no: "118153287897731040607" };
    fetch(
      "http://localhost:9999/ta_back/member/login?socialNo=347856298374982379",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },

        credentials: "include",
      }
    )
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setLogininfo(data.member);
        console.log(data.member);
        console.log("login--->", data.member);
      });
  }

  function login2() {
    // const mem = { member_social_no: "118153287897731040607" };
    fetch(
      "http://localhost:9999/ta_back/member/login?socialNo=773598399242",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },

        credentials: "include",
      }
    )
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setLogininfo(data.member);
        console.log(data.member.member_nickName);
        console.log("login--->", data.member);
      });
  }
  function login3() {
    // const mem = { member_social_no: "118153287897731040607" };
    fetch(
      "http://localhost:9999/ta_back/member/login?socialNo=116094386597148560988",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },

        credentials: "include",
      }
    )
      .then((res) => {
        // console.log("로그인 : ", res.json());
        return res.json();
      })
      .then((data) => {
        setLogininfo(data.member);
        console.log(data.member.member_nickName);
        console.log("login--->", data.member);
      });
  }

  function logout() {
    // const mem = { member_social_no: "118153287897731040607" };
    fetch("http://localhost:9999/ta_back/member/logout", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        member_social_no: "118153287897731040607",
      }),
      credentials: "include",
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        // setLoginInfo(data.member);
        console.log("logout--->", data);
      });
  }
  //로그인 테스트 끝

  return (
    <>
      <button onClick={login}>토론자 A 로그인</button>
      <button onClick={login2}>토론자 B 로그인</button>
      <button onClick={login3}>관중 로그인 </button>
      <button onClick={logout}>로그아웃</button>
      {/* <Alert show={searchAlert} variant="warning">
            <Alert.Heading>검색어를 입력해주세요!</Alert.Heading>
            <p>검색어는 필수 입니다 ^__^</p>
            <div className="d-flex justify-content-end">
              {
                <Button
                  onClick={() => setSearchAlert(false)}
                  variant="outline-danger"
                  style={{ border: "none" }}
                >
                  Close
                </Button>
              }
            </div>
          </Alert> */}
      <h2 style={{ display: "flex", justifyContent: "center" }}>토론 배틀</h2>
      <div
        style={{
          textAlign: "right",
          marginRight: "10px",
          display: "block",
        }}
      >
        <label style={{ fontSize: "15pt", fontWeight: "600" }}>
          <InputGroup className="mb-3">
            <InputGroup.Text id="inputGroup-sizing-default">
              검색
            </InputGroup.Text>

            <FormControl
              aria-label="Default"
              aria-describedby="inputGroup-sizing-default"
              placeholder="검색할 단어를 입력하세요"
              onChange={search}
            />
          </InputGroup>
        </label>

        <Button
          style={{ marginLeft: "10px" }}
          className="buttons"
          variant="success"
          onClick={btnsearch}
        >
          검색
        </Button>
      </div>
      <Table responsive="xl" hover>
        <thead className="table-primary">
          <tr style={{ fontSize: "14pt" }}>
            <th style={{ width: "10%" }}>번호</th>
            <th style={{ width: "20%" }}>주제</th>
            <th style={{ width: "20%" }}>현황</th>
            <th style={{ width: "10%" }}>토론 시간</th>
            <th style={{ width: "20%" }}>시작 시각</th>
            <th style={{ width: "20%" }}>종료 시각</th>
          </tr>
        </thead>
        <tbody className="table-light">
          {pagedDebate?.map((debate, index) => (
            <tr key={debate.debate_no}>
              <td>{(currentPage - 1) * pageSize + index + 1}</td>
              <td>
                <Link
                  to={{
                    pathname: `/ta_front/debbattle/${debate.debate_no}`,
                    state: { debate: debate, logininfo: logininfo }, // 
                  }}
                >
                  {debate.debate_topic}
                </Link>
              </td>
              <td>{debate.debate_status}</td>
              <td>{debate.debate_time + "분"}</td>
              <td>{debate.debate_startDate}</td>
              <td>{debate.debate_endDate}</td>
            </tr>
          ))}
        </tbody>
      </Table>
      <Pagination
        itemsCount={deb_length} //
        pageSize={pageSize}
        currentPage={currentPage}
        onPageChange={handlePageChange}
      />
    </>
  );
}