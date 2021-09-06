import {
  Table,
  Modal,
  InputGroup,
  FormControl,
  Dropdown,
  DropdownButton,
} from "react-bootstrap";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Paging from "./pagination/Paging";

export default function Board(props) {
  console.log("board props", props.userInfo);
  const [list, setList] = useState({});
  const [page, setPage] = useState(1);
  const [count, setCount] = useState(1);
  const pageSize = 5;
  const [word, setWord] = useState();
  const [requestData, setRequestData] = useState(new Date());
  const [modalContent, setModalContent] = useState();
  const [show, setShow] = useState(false);
  const [noticeType, setNoticeType] = useState("");
  const [noticeTitle, setNoticeTitle] = useState();
  const [noticeContent, setNoticeContent] = useState();
  const [noticeNo, setNoticeNo] = useState();
  const [replyShow, setReplyShow] = useState();
  const [replyList, setReplyList] = useState();

  const [url, setUrl] = useState(
    `http://localhost:9999/ta_back/admin/board/list?pageNo=${page}&pageSize=${pageSize}`
  );

  function setPage1(page) {
    const listUrl = `http://localhost:9999/ta_back/admin/board/list?pageNo=${page}&pageSize=${pageSize}`;
    const searchUrl = `http://localhost:9999/ta_back/admin/board/list/${word}?pageNo=${page}&pageSize=${pageSize}`;
    // console.log(url);
    // console.log(page);
    if (word) {
      setUrl(searchUrl);
      setPage(page);
    } else {
      setUrl(listUrl);
      setPage(page);
    }
  }
  useEffect(() => {
    // setLoading(true);
    fetch(url, {
      method: "GET",
      credentials: "include",
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log("--->", data);
        //setRequestData(new Date());
        setList(data);
        setCount(data.lastRow);
        // setLoginInfo(data.logininfo);
        // console.log("로그인정보->", loginInfo);
        // setLoading(false);
      });
  }, [page, requestData]);

  const replyView = (e) => {
    const { id } = e.target;
    const url = "http://localhost:9999/ta_back/boardcomment/list/" + id;
    console.log("no=>", url);

    fetch(url, {
      method: "get",
      headers: { "Content-Type": "application/json" },

      credentials: "include",
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log("--->", data);
        setReplyList(data.boardcommentlist);
        setReplyShow(true);
        // setLoading(false);
      });
  };
  const noticeDel = (e) => {
    const { id } = e.target;
    // console.log("=>", id);
    if (window.confirm("삭제 하시겠습니까?")) {
      const url = "http://localhost:9999/ta_back/admin/board/" + id;
      fetch(url, {
        method: "DELETE",
        credentials: "include",
      })
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log("--->", data);
          setRequestData(new Date());
          setShow(false);
          // setLoginInfo(data.logininfo);
          // console.log("로그인정보->", loginInfo);
          // setLoading(false);
        });
    } else {
      return false;
    }
  };
  const replyDelete = (e) => {
    const { id } = e.target;
    const url = "http://localhost:9999/ta_back/boardcomment/" + id;
    console.log("no=>", url);
    if (window.confirm("삭제 하시겠습니까?")) {
      fetch(url, {
        method: "delete",
        headers: { "Content-Type": "application/json" },

        credentials: "include",
      })
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log("--->", data);
          if (data.status == 1) {
            alert("삭제 성공");
            setReplyShow(false);
          } else if (data.status == 0) {
            alert("삭제 실패", data.msg);
          }
          //setReplyList(data.list);
          //setReplyShow(true);
          // setLoading(false);
        });
    } else {
      return false;
    }
  };
  return (
    <>
      <h1>자유게시판</h1>
      <Table hover>
        <thead
          className="table-info"
          style={{ fontSize: "15pt", fontWeight: "700" }}
        >
          <tr>
            <td style={{ width: "5%" }}>번호</td>
            <td style={{ width: "5%" }}>종류</td>
            <td style={{ width: "20%" }}>제목</td>
            <td style={{ width: "15%" }}>날짜</td>
            <td style={{ width: "5%" }}>조회수</td>
            <td style={{ width: "5%" }}>작성자</td>
            <td style={{ width: "15%" }}>작업</td>
          </tr>
        </thead>
        <tbody className="table-light">
          {list.boardlist?.map((notice, index) => (
            <tr className="table-light" key={notice.board_no}>
              <td>{notice.board_no}</td>
              <td>{notice.board_type}</td>
              <td>
                <Link
                  to="/"
                  onClick={(e) => {
                    e.preventDefault();
                    // console.log("notice=>", notice);
                    setShow(true);
                    setModalContent(notice);
                  }}
                >
                  {notice.board_title}
                </Link>
              </td>
              <td>{notice.board_date}</td>
              <td>{notice.board_views}</td>
              <td>{notice.board_member.member_nickName}</td>
              <td>
                <button
                  className="btn btn-outline-success"
                  style={{ marginRight: "10px" }}
                  id={notice.board_no}
                  onClick={replyView}
                >
                  댓글
                </button>

                <button
                  className="btn btn-outline-danger"
                  id={notice.board_no}
                  onClick={noticeDel}
                >
                  삭제
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      <div className="paging">
        <Paging page={page} count={count} setPage={setPage1} />
      </div>
      <div className="viewModal">
        <Modal
          show={show}
          onHide={() => setShow(false)}
          dialogClassName="modal-90w"
          aria-labelledby="example-custom-modal-styling-title"
          fullscreen="xxl-down"
          size="lg"
        >
          <Modal.Header closeButton>
            <Modal.Title id="example-custom-modal-styling-title">
              {modalContent?.board_title}
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <InputGroup size="lg" style={{ minHeight: "400px" }}>
              <InputGroup.Text>내용</InputGroup.Text>
              <FormControl
                as="textarea"
                aria-label="With textarea"
                // onChange={(e) => {
                //   setNoticeContent(e.target.value);
                // }}
                readOnly
                value={modalContent?.board_contents}
              />
            </InputGroup>
            {/* <p>{modalContent?.notice_contents}</p> */}
          </Modal.Body>
          <Modal.Footer>
            <button
              className="btn btn-outline-success"
              id={modalContent?.board_no}
              onClick={noticeDel}
            >
              삭제
            </button>
          </Modal.Footer>
        </Modal>
      </div>
      <div className="replyModal">
        <Modal
          show={replyShow}
          onHide={() => setReplyShow(false)}
          dialogClassName="modal-100w"
          aria-labelledby="example-custom-modal-styling-title"
          // fullscreen="xxl-down"
          size="lg"
          centered
        >
          <Modal.Header closeButton>
            <Modal.Title
              id="example-custom-modal-styling-title"
              style={{
                marginLeft: "auto",
                marginRight: "auto",
                width: "100%",
              }}
            >
              <div style={{ marginBottom: "20px" }}>댓글 리스트</div>
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Table bordered hover style={{ textAlign: "center" }}>
              <thead className="table-success">
                <tr>
                  <td>댓글 번호</td>
                  <td>댓글 내용</td>
                  <td>댓글 작성자</td>
                  <td>삭제</td>
                </tr>
              </thead>
              <tbody className="table-light">
                {replyList?.map((list) => (
                  <tr key={list.com_no}>
                    <td>{list.com_no}</td>
                    <td>{list.com_contents}</td>
                    <td>{list.com_member.member_nickName}</td>
                    <td>
                      <button
                        className="btn btn-danger"
                        id={list.com_no}
                        onClick={replyDelete}
                      >
                        삭제
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </Modal.Body>
        </Modal>
      </div>
    </>
  );
}
