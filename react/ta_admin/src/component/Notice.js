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

export default function Notice(props) {
  //console.log("notice props", props.userInfo);
  const [list, setList] = useState({});
  const [page, setPage] = useState(1);
  const [count, setCount] = useState(1);
  const [word, setWord] = useState();
  const [requestData, setRequestData] = useState(new Date());
  const [modalContent, setModalContent] = useState();
  const pageSize = 5;
  const [show, setShow] = useState(false);
  const [writeShow, setWriteShow] = useState(false);
  const [editButton, setEditButton] = useState(false);
  const [noticeType, setNoticeType] = useState("");
  const [noticeTitle, setNoticeTitle] = useState();
  const [noticeContent, setNoticeContent] = useState();
  const [noticeNo, setNoticeNo] = useState();
  const [replyShow, setReplyShow] = useState();
  const [replyList, setReplyList] = useState();

  const [url, setUrl] = useState(
    `http://localhost:9999/ta_back/admin/notice/list?pageNo=${page}&pageSize=${pageSize}`
  );
  function setPage1(page) {
    const listUrl = `http://localhost:9999/ta_back/admin/notice/list?pageNo=${page}&pageSize=${pageSize}`;
    const searchUrl = `http://localhost:9999/ta_back/admin/notice/list/${word}?pageNo=${page}&pageSize=${pageSize}`;
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
  const noticeDel = (e) => {
    const { id } = e.target;
    // console.log("=>", id);
    if (window.confirm("삭제 하시겠습니까?")) {
      const url = "http://localhost:9999/ta_back/notice/" + id;
      fetch(url, {
        method: "DELETE",
        credentials: "include",
      })
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          //console.log("--->", data);
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

  const noticeWrite = () => {
    const url = "http://localhost:9999/ta_back/notice/";
    fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        notice_type: noticeType,
        notice_title: noticeTitle,
        notice_contents: noticeContent,
        notice_admin: props.userInfo,
      }),
      credentials: "include",
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        //console.log("--->", data);
        setRequestData(new Date());
        setWriteShow(false);
        // setLoginInfo(data.logininfo);
        // console.log("로그인정보->", loginInfo);
        // setLoading(false);
      });
  };
  const noticeUpdate = () => {
    const url = "http://localhost:9999/ta_back/notice/" + noticeNo;
    console.log("no=>", url);

    fetch(url, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        notice_no: noticeNo,
        notice_type: noticeType,
        notice_title: noticeTitle,
        notice_contents: noticeContent,
        //notice_admin: props.userInfo,
      }),
      credentials: "include",
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        //console.log("--->", data);
        setRequestData(new Date());
        setWriteShow(false);
        // setLoginInfo(data.logininfo);
        // console.log("로그인정보->", loginInfo);
        // setLoading(false);
      });
  };
  const noticeEdit = (e) => {
    const { id } = e.target;
    console.log(id);
    setShow(false);
    setWriteShow(true);
    setEditButton(true);
    setNoticeNo(list.noticelist[id].notice_no);
    setNoticeType(list.noticelist[id].notice_type);
    setNoticeTitle(list.noticelist[id].notice_title);
    setNoticeContent(list.noticelist[id].notice_contents);
  };
  const modalEdit = (e) => {
    setShow(false);
    setWriteShow(true);
    setEditButton(true);
    setNoticeNo(modalContent.notice_no);
    setNoticeType(modalContent.notice_type);
    setNoticeTitle(modalContent.notice_title);
    setNoticeContent(modalContent.notice_contents);
  };
  const replyView = (e) => {
    const { id } = e.target;
    const url = "http://localhost:9999/ta_back/noticecomment/list/" + id;
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
        setReplyList(data.list);
        setReplyShow(true);
        // setLoading(false);
      });
  };
  const replyDelete = (e) => {
    const { id } = e.target;
    const url = "http://localhost:9999/ta_back/noticecomment/" + id;
    console.log("no=>", url);

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
          if (window.confirm("삭제 하시겠습니까?")) {
            alert("삭제 성공");
            setReplyShow(false);
          } else {
            return false;
          }
        } else if (data.status == 0) {
          alert("삭제 실패", data.msg);
        }
        //setReplyList(data.list);
        //setReplyShow(true);
        // setLoading(false);
      });
  };

  return (
    <>
      <h1>공지사항</h1>
      <div style={{ textAlign: "right", margin: "10px" }}>
        <button
          className="btn btn-success"
          onClick={() => {
            setWriteShow(true);
            setEditButton(false);
            setNoticeNo();
            setNoticeType();
            setNoticeTitle();
            setNoticeContent();
          }}
        >
          공지 작성
        </button>
      </div>
      <Table hover>
        <thead
          className="table-success"
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
          {list.noticelist?.map((notice, index) => (
            <tr className="table-light" key={notice.notice_no}>
              <td>{notice.notice_no}</td>
              <td>{notice.notice_type}</td>
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
                  {notice.notice_title}
                </Link>
              </td>
              <td>{notice.notice_date}</td>
              <td>{notice.notice_views}</td>
              <td>{notice.notice_admin}</td>
              <td>
                <button
                  className="btn btn-outline-success"
                  style={{ marginRight: "10px" }}
                  id={notice.notice_no}
                  onClick={replyView}
                >
                  댓글
                </button>
                <button
                  className="btn btn-outline-info"
                  style={{ marginRight: "10px" }}
                  id={index}
                  onClick={noticeEdit}
                >
                  수정
                </button>
                <button
                  className="btn btn-outline-danger"
                  id={notice.notice_no}
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
              {modalContent?.notice_title}
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
                value={modalContent?.notice_contents}
              />
            </InputGroup>
            {/* <p>{modalContent?.notice_contents}</p> */}
          </Modal.Body>
          <Modal.Footer>
            <button
              className="btn btn-outline-success"
              style={{ marginRight: "10px" }}
              id={modalContent?.notice_no}
              onClick={modalEdit}
            >
              수정
            </button>
            <button
              className="btn btn-outline-success"
              id={modalContent?.notice_no}
              onClick={noticeDel}
            >
              삭제
            </button>
          </Modal.Footer>
        </Modal>
      </div>
      <div className="writeModal">
        <Modal
          show={writeShow}
          onHide={() => setWriteShow(false)}
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
              <div style={{ marginBottom: "20px" }}>
                <DropdownButton
                  variant="outline-secondary"
                  title="공지 종류"
                  id="input-group-dropdown-2"
                  align="end"
                  style={{
                    display: "inline",
                    marginRight: "10px",
                  }}
                >
                  <Dropdown.Item
                    onClick={() => {
                      setNoticeType("공지사항");
                    }}
                  >
                    공지사항
                  </Dropdown.Item>
                  <Dropdown.Item
                    onClick={() => {
                      setNoticeType("이벤트");
                    }}
                  >
                    이벤트
                  </Dropdown.Item>
                  <Dropdown.Item
                    onClick={() => {
                      setNoticeType("업데이트");
                    }}
                  >
                    업데이트
                  </Dropdown.Item>
                </DropdownButton>
                {noticeType}
              </div>
              <InputGroup className="mb-3" size="lg" style={{ width: "95%" }}>
                <InputGroup.Text id="basic-addon1">제목</InputGroup.Text>
                <FormControl
                  placeholder="제목을 입력해주세요."
                  aria-label="Username"
                  aria-describedby="basic-addon1"
                  onChange={(e) => {
                    setNoticeTitle(e.target.value);
                    //console.log(noticeTitle);
                  }}
                  value={noticeTitle}
                />
              </InputGroup>
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <InputGroup size="lg" style={{ minHeight: "400px" }}>
              <InputGroup.Text>내용</InputGroup.Text>
              <FormControl
                as="textarea"
                aria-label="With textarea"
                onChange={(e) => {
                  setNoticeContent(e.target.value);
                }}
                value={noticeContent}
              />
            </InputGroup>
          </Modal.Body>
          <Modal.Footer>
            {editButton ? (
              <button
                className="btn btn-outline-success"
                style={{ marginRight: "10px" }}
                id={modalContent?.notice_no}
                onClick={noticeUpdate}
              >
                수정
              </button>
            ) : (
              <button
                className="btn btn-outline-success"
                style={{ marginRight: "10px" }}
                id={modalContent?.notice_no}
                onClick={noticeWrite}
              >
                작성
              </button>
            )}
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
                    <td>{list.com_mem.member_nickName}</td>
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
