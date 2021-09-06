import React, { Component } from "react";
import ApiService from "../../ApiService";
import { Link } from "react-router-dom";

import "../../css/bootstrap.css";
import { Table, Button } from "react-bootstrap";

// const styles = () => ({
//     boardTopBackground: {
//         zIndex: 1,
//         backgroundColor: 'rgba(0, 0, 0, 0.5)',
//         width: '100%',
//         height: '12rem',
//         position: 'absolute'
//     },
//     boardTop: {
//         height: '12em',
//         width: '100%',
//         position: 'relative',
//     },
//     boardTopText: {
//         zIndex: 2,
//         position: 'absolute',
//         bottom: '3rem',
//         left: '3rem',
//         fontSize: "2rem",
//         fontWeight: 'bold',
//         color: 'white',
//     },
// })

class DetailNoticeComponent extends Component {
  constructor(props) {
    super(props);

    console.log("테스트 1 : ", props);

    this.state = {
      notice_no: this.props.match.params.no,
      notice_type: "",
      notice_title: "",
      notice_contents: "",
      notice_admin: "",
      notice_comments: [],
      notice_date: "",
      notice_views: "",
      input_comment: "",
      message: "",
      loginInfo: this.props.location.state.loginInfo,
    };
    console.log("테스트 2 : ", this.state.loginInfo);
  }

  componentDidMount() {
    this.reloadNotice();
  }

  reloadNotice = () => {
    ApiService.fetchNoticeByNo(this.state.notice_no)
      .then((res) => {
        this.setState({
          notice_type: res.data.notice.notice_type,
          notice_title: res.data.notice.notice_title,
          notice_contents: res.data.notice.notice_contents,
          notice_date: res.data.notice.notice_date,
          notice_views: res.data.notice.notice_views,
          notice_admin: res.data.notice.notice_admin,
        });
      })
      .catch((err) => {
        console.log("reloadNotice() Error!", err);
      });
    ApiService.fetchNC(this.state.notice_no)
      .then((res) => {
        this.setState({
          notice_comments: res.data.list,
        });
      })
      .catch((err) => {
        console.log("reloadNoticeList() Error!", err);
      });
  };

  deleteNC = (comNo) => {
    ApiService.deleteNC(comNo)
      .then((res) => {
        this.setState({
          notice_comments: this.state.notice_comments.filter(
            (comment) => comment.com_no !== comNo
          ),
        });
      })
      .catch((err) => {
        console.log("deleteNC() Error!", err);
      });
  };

  handleChange = (e) => {
    this.setState({
      [e.target.name]: e.target.value, // <- 변경 후
    });
  };

  handleSubmit = (e) => {
    e.preventDefault();

    let noticeComment = {
      com_notice: this.state.notice_no,
      com_contents: this.state.input_comment,
      com_mem: this.state.loginInfo,
    };

    ApiService.addNC(noticeComment)
      .then((res) => {
        this.setState({
          message: "addNC Completed",
        });
        console.log(this.state.message);
      })
      .catch((err) => {
        console.log("addNC() 에러", err);
      });

    this.reloadNotice();

    this.setState({
      input_comment: "",
    });
  };

  render() {
    return (
      <div className="container" style={{ marginTop: "5%" }}>
        <h2 style={{ display: "flex", justifyContent: "center" }}>
          상세페이지
        </h2>
        <div>
          <Table className="table" hover>
            <thead></thead>
            <tbody className="table-light">
              <tr>
                <td className="success text-center" style={{ width: "25%" }}>
                  번호
                </td>
                <td className="board_no text-center" style={{ width: "25%" }}>
                  {this.state.notice_no}
                </td>
                <td className="success text-center" style={{ width: "25%" }}>
                  타입
                </td>
                <td className="board_type text-center" style={{ width: "25%" }}>
                  {this.state.notice_type}
                </td>
              </tr>
              <tr>
                <td className="success text-center" style={{ width: "25%" }}>
                  제목
                </td>
                <td
                  className="board_title text-center"
                  style={{ width: "25%" }}
                >
                  {this.state.notice_title}
                </td>
                <td className="success text-center" style={{ width: "25%" }}>
                  작성자
                </td>
                <td className="board_mem text-center" style={{ width: "25%" }}>
                  {this.state.notice_admin}
                </td>
              </tr>
              <tr>
                <td className="success text-center" style={{ width: "25%" }}>
                  조회수
                </td>
                <td
                  className="board_views text-center"
                  style={{ width: "25%" }}
                >
                  {this.state.notice_views}
                </td>
                <td className="success text-center" style={{ width: "25%" }}>
                  작성일
                </td>
                <td className="board_date text-center" style={{ width: "25%" }}>
                  {this.state.notice_date}
                </td>
              </tr>
              <tr></tr>
              <tr>
                <td
                  className="board_contents text-center"
                  colSpan="4"
                  height="200"
                  valign="top"
                >
                  {this.state.notice_contents}
                </td>
              </tr>
              <tr>
                <td className="text-right" colspan="4">
                  {/* <Link to="/"> */}
                  <Link to="/ta_front/notice.html">
                    <Button
                      className="btn btn-success"
                      variant="contained"
                      color="primary"
                    >
                      Back
                    </Button>
                  </Link>
                </td>
              </tr>
            </tbody>
          </Table>
        </div>
        <form
          onSubmit={this.handleSubmit}
          style={{
            display: "flex",
            justifyContent: "center",
            margin: "0 auto",
            marginTop: "5%",
          }}
        >
          <input
            size="60"
            placeholder="댓글을 입력하세요"
            value={this.state.input_comment}
            onChange={this.handleChange}
            name="input_comment"
            style={{ marginRight: "5px" }}
          />
          <button className="btn btn-success" type="submit">
            작성
          </button>
        </form>

        {/* <form onSubmit={this.handleSubmit}>
                    <TextField 
                        required 
                        placeholder='댓글을 입력하세요' 
                        name='input_comment' 
                        value={this.state.input_comment}
                        onChange={this.handleChange}
                    ></TextField>
                    <Button type="submit" variant="contained" color="primary">입력</Button>
                </form> */}

        <div>
          <Table
            className="table"
            id="comment"
            style={{ margin: "10px", textAlign: "center" }}
            hover
          >
            <thead className="table-success">
              <tr>
                <th>작성자</th>
                <th>내용</th>
                <th>작성일</th>
                <th>Delete</th>
              </tr>
            </thead>
            <tbody>
              {this.state.notice_comments.map((comment) => (
                <tr className="data2" key={comment.com_no}>
                  <td class="com_mem">{comment.com_mem.member_nickName}</td>
                  <td class="com_contents">{comment.com_contents}</td>
                  <td class="com_date">{comment.com_date}</td>
                  <td class="com_delete">
                    {this.state.loginInfo?.member_no !== undefined &&
                    comment.com_mem.member_no ===
                      this.state.loginInfo?.member_no ? (
                      <input
                        class="com_delete_button btn btn-xs btn-outline-success"
                        type="submit"
                        value="삭제"
                        onClick={() => this.deleteNC(comment.com_no)}
                      />
                    ) : (
                      <p>삭제 불가</p>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

export default DetailNoticeComponent;
