import React, { Component } from "react";
import { Link } from "react-router-dom";
import ApiService from "../../ApiService";

import Pagination from "./NoticePaging";
import { paginate } from "./paginate";
import { Table, Button } from "react-bootstrap";
import "../../css/bootstrap.css";
// import { Button } from '@material-ui/core';

class NoticeListComponent extends Component {
  /*
        Constructor
        - 생성자 메소드로 컴포넌트가 처음 생성 시 실행
        - 이 메소드에서 기본 state를 설정 가능

        state
    */
  constructor(props) {
    super(props);

    this.state = {
      notices: [],
      filteredData: [],
      searchKeyword: "",
      pageSize: 5,
      currentPage: 1,
      loginInfo: null,
      message: null,
    };
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  /*
        componentDidMount
        - 컴포넌트가 만들어지고 첫 렌더링을 완료 후 실행되는 메소드
        - 이 메소드 내에서 다른 JS 프레임워크를 연동하거나 setTimeout, setInterval 및 AJAX 처리 등을 삽입
    */

  componentDidMount() {
    this.reloadNoticeList();
  }

  reloadNoticeList = () => {
    if (this.state.filteredData.length === 0) {
      ApiService.fetchNotices()
        .then((res) => {
          console.log("res.data.notices", res.data.notices.length);
          this.setState({
            notices: res.data.notices,
            loginInfo: res.data.loginInfo,
          });
        })
        .catch((err) => {
          console.log("reloadNoticeList() Error!", err);
        });
    } else {
      this.setState({
        notices: this.state.filteredData,
        filteredData: null,
      });
    }
  };

  // 검색 기능
  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value,
    });
  };

  handleSubmit = (e) => {
    e.preventDefault();

    let value = this.state.searchKeyword;
    let result = [];

    result = this.state.notices.filter((data) => {
      console.log("data : ", data.notice_title, " / ", data.notice_contents);
      console.log("value : ", value);
      return (
        data.notice_title.search(value) !== -1 ||
        data.notice_contents.search(value) !== -1
      );
    });

    this.setState({ filteredData: result }, () => {
      this.reloadNoticeList();
    });

    this.setState({
      searchKeyword: "",
      currentPage: 1,
    });
  };

  // 페이징
  handlePageChange = (page) => {
    this.setState({
      currentPage: page,
    });
  };

  addNotice = () => {
    window.localStorage.removeItem("noticeNo");
    // this.props.history.push('/add-notice');
    this.props.history.push("./notice/write");
  };

  /*
        Edit 버튼을 누르면 브라우저 로컬스토리지를 사용하여 해당 유저 id를 일시적으로 저장
        route를 통해 EditUserComponent.jsx로 이동
        route에서 "/edit-user" url로 이동 시 EditUserComponent.jsx를 출력하도록 설정
    */

  editNotice = (noticeNo) => {
    window.localStorage.setItem("noticeNo", noticeNo);
    // this.props.history.push('/edit-notice');
    this.props.history.push("./notice/modify");
  };

  /*
        Delete 버튼을 누르면 API 통신을 사용하여 DB에서 해당 유저를 삭제
        filter 기능을 통해 state에 있는 users 배열에서 삭제된 해당 유저 id를 제외하고 다시 배열로 표현
    */
  deleteNotice = (noticeNo) => {
    ApiService.deleteNotice(noticeNo)
      .then((res) => {
        this.setState({
          message: "Notice Deleted Successfully.",
        });
        this.setState({
          notices: this.state.notices.filter(
            (notice) => notice.notice_no !== noticeNo
          ),
        });
      })
      .catch((err) => {
        console.log("deleteNotice() Error!", err);
      });
  };

  render() {
    const count = this.state.notices.length;

    const pagingNotice = {
      notices: this.state.notices,
      pageSize: this.state.pageSize,
      currentPage: this.state.currentPage,
    };

    const pagedNotice = paginate(
      pagingNotice.notices,
      pagingNotice.currentPage,
      pagingNotice.pageSize
    );
    return (
      <div className="container" style={{ marginTop: "5%" }}>
        <h2 style={{ display: "flex", justifyContent: "center" }}>공지사항</h2>

        {/* <Button variant="contained" color="primary" onClick={this.addNotice}> Add Notice </Button> */}
        <form
          onSubmit={this.handleSubmit}
          style={{
            display: "flex",
            justifyContent: "flex-end	",
            margin: "0 auto",
            marginTop: "5%",
          }}
        >
          <input
            size="25"
            placeholder="검색할 단어를 입력하세요"
            value={this.state.searchKeyword}
            onChange={this.handleChange}
            name="searchKeyword"
            style={{ marginRight: "5px" }}
          />
          <Button className="btn btn-success" type="submit">
            검색
          </Button>
        </form>
        <div style={{ marginTop: "30px" }}>
          <Table
            hover
            className="table table-hover"
            style={{ textAlign: "center", marginTop: "10px" }}
          >
            <thead style={{ fontSize: "15pt", marginTop: "10px" }}>
              <tr className="table-primary">
                <th>번호</th>
                <th>타입</th>
                <th>제목</th>
                <th>작성일</th>
                <th>조회수</th>
                <th>작성자</th>
                {/* <th>Edit</th>
                    <th>Delete</th> */}
              </tr>
            </thead>
            <tbody className="table-light">
              {pagedNotice.map((notice, index) => (
                <tr className="data1" key={notice.notice_no}>
                  <td className="board_no">
                    {(this.state.currentPage - 1) * this.state.pageSize +
                      index +
                      1}
                  </td>
                  <td className="board_type">{notice.notice_type}</td>
                  <td className="board_title">
                    {/* <Link to={`/notices/${notice.notice_no}`}>{ notice.notice_title }</Link> */}
                    {/* <Link to={`notice/${notice.notice_no}`} state={ loginInfo }>{ notice.notice_title }</Link>  */}
                    <Link
                      to={{
                        pathname: `notice/${notice.notice_no}`,
                        state: { loginInfo: this.state.loginInfo },
                      }}
                    >
                      {notice.notice_title}
                    </Link>
                  </td>
                  <td className="board_date">{notice.notice_date}</td>
                  <td className="board_views">{notice.notice_views}</td>
                  <td className="board_mem">{notice.notice_admin}</td>
                  {/* <td>
                        <Button variant="contained" color="primary" onClick={()=> this.editNotice(notice.notice_no)}> Edit </Button>
                      </td>
                      <td>
                        <Button variant="contained" color="primary" onClick={()=> this.deleteNotice(notice.notice_no)}> Delete </Button>
                      </td> */}
                </tr>
              ))}
            </tbody>
          </Table>
          <Pagination
            itemsCount={count}
            pageSize={this.state.pageSize}
            currentPage={this.state.currentPage}
            onPageChange={this.handlePageChange}
          />
        </div>
      </div>
    );
  }
}

export default NoticeListComponent;
