import React, { Component } from 'react';
import ApiService from "../../ApiService";
import { Link } from 'react-router-dom';

import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table'
import TableBody from '@material-ui/core/TableBody'
import TableCell from '@material-ui/core/TableCell'
import TableHead from '@material-ui/core/TableHead'
import TableRow from '@material-ui/core/TableRow'
import Button from '@material-ui/core/Button'
import DeleteIcon from '@material-ui/icons/Delete'
import TextField from '@material-ui/core/TextField';

const styles = () => ({
    boardTopBackground: {
        zIndex: 1,
        backgroundColor: 'rgba(0, 0, 0, 0.5)',
        width: '100%',
        height: '12rem',
        position: 'absolute'
    },
    boardTop: {
        height: '12em',
        width: '100%',
        position: 'relative',
    },
    boardTopText: {
        zIndex: 2,
        position: 'absolute',
        bottom: '3rem',
        left: '3rem',
        fontSize: "2rem",
        fontWeight: 'bold',
        color: 'white',
    },
})

class DetailNoticeComponent extends Component {
    
    constructor(props){
        super(props);

        this.state = {
            notice_no: this.props.match.params.no,
            notice_type: '',
            notice_title: '',
            notice_contents: '',
            notice_admin: '',
            notice_comments: [],
            notice_date: '',
            notice_views:'',
            input_comment: '',
            message:'',
        }
    }

    componentDidMount() {
        this.reloadNotice();
    }

    reloadNotice = () => {
        ApiService.fetchNoticeByNo(this.state.notice_no)
            .then( res => {
                this.setState({
                    notice_type: res.data.notice.notice_type,
                    notice_title: res.data.notice.notice_title,
                    notice_contents: res.data.notice.notice_contents,
                    notice_date: res.data.notice.notice_date,
                    notice_views: res.data.notice.notice_views,
                    notice_admin: res.data.notice.notice_admin,
                })
            })
            .catch(err => {
                console.log('reloadNotice() Error!', err);
            })
        ApiService.fetchNC(this.state.notice_no)
            .then( res => {
                this.setState({
                    notice_comments: res.data.list
                })
            })
            .catch(err => {
                console.log('reloadNoticeList() Error!', err);
            })
    }

    deleteNC = (comNo) => {
        ApiService.deleteNC(comNo)
            .then( res => {
                this.setState({
                    notice_comments: this.state.notice_comments.filter( comment =>
                        comment.com_no !== comNo)
                });
            })
            .catch(err => {
                console.log('deleteNC() Error!', err);
            })
    }

    handleChange = (e) => {
        this.setState({
          [e.target.name]: e.target.value, // <- 변경 후
        });
    };

    handleSubmit = e => {
        e.preventDefault();

        let noticeComment = {
            com_notice:this.state.notice_no,
            com_contents:this.state.input_comment,
            com_mem:{
                member_no:2,
            },
        }

        ApiService.addNC(noticeComment)
            .then( res => {
                this.setState({
                    message: 'addNC Completed'
                })
                console.log(this.state.message);
            })
            .catch( err => {
                console.log('addNC() 에러', err);
            });
        
        this.reloadNotice();

        this.setState({
            input_comment: ''
        })
    }

    render(){
        return(
            <div>
                <form>
                    <h2 align="center">Notice Detail</h2>
                    <fieldset>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell align="center">번호</TableCell>
                                    <TableCell align="center">종류</TableCell>
                                    <TableCell align="center">제목</TableCell>
                                    <TableCell align="center">내용</TableCell>
                                    <TableCell align="center">작성자</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow key={this.state.notice_no}>
                                    <TableCell align="center">{this.state.notice_no}</TableCell>
                                    <TableCell align="center">{this.state.notice_type}</TableCell>
                                    <TableCell align="center">{this.state.notice_title}</TableCell>
                                    <TableCell align="center">{this.state.notice_contents}</TableCell>
                                    <TableCell align="center">{this.state.notice_admin}</TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </fieldset>
                </form>
                <Link to="/">
                    <Button className="lunchBox-btn-rec-line" variant="contained" color="primary">
                        Back
                    </Button>
                </Link>
                <form onSubmit={this.handleSubmit}>
                    <TextField 
                        required 
                        placeholder='댓글을 입력하세요' 
                        name='input_comment' 
                        value={this.state.input_comment}
                        onChange={this.handleChange}
                    ></TextField>
                    <Button type="submit" variant="contained" color="primary">입력</Button>
                </form>
                
                <form>
                    <h2 align="center">Notice Comment</h2>
                    <fieldset>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell align="center">작성자</TableCell>
                                    <TableCell align="center">내용</TableCell>
                                    <TableCell align="center">작성일</TableCell>
                                    <TableCell align="center">Delete</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {this.state.notice_comments.map( comment => 
                                    <TableRow key={comment.com_no}>
                                        <TableCell align="center">{comment.com_mem.member_nickName}</TableCell>
                                        <TableCell align="center">{comment.com_contents}</TableCell>
                                        <TableCell align="center">{comment.com_date}</TableCell>
                                        
                                        <TableCell align="center" onClick={()=> this.deleteNC(comment.com_no)}>
                                            <DeleteIcon />
                                        </TableCell>
                                    </TableRow>
                                )}
                            </TableBody>
                        </Table>
                    </fieldset>
                </form>

                <div className= 'container' style={{ marginTop: '5%' }}>
                    <h2 style = {{ display: 'flex',justifyContent: 'center'}}>상세페이지</h2>
                    <div>
                        <table className='table'>
                            <tr>
                                <td className="success text-center" style={{ width: '25%' }}>번호</td>
                                <td	className="board_no text-center" style={{ width: '25%' }}>{this.state.notice_no}</td>
                                <td className="success text-center" style={{ width: '25%' }}>타입</td>
                                <td	className="board_type text-center" style={{ width: '25%' }}>{this.state.notice_type}</td>
                            </tr>
                            <tr>
                                <td className="success text-center" style={{ width: '25%' }}>제목</td>
                                <td className="board_title text-center" style={{ width: '25%' }}>{this.state.notice_title}</td>
                                <td className="success text-center" style={{ width: '25%' }}>작성자</td>
                                <td	className="board_mem text-center" style={{ width: '25%' }}>{this.state.notice_admin}</td>                            
                            </tr>
                            <tr>
                                <td className="success text-center" style={{ width: '25%' }}>조회수</td>
                                <td className="board_views text-center" style={{ width: '25%' }}>{this.state.notice_views}</td>
                                <td className="success text-center" style={{ width: '25%' }}>작성일</td>
                                <td className="board_date text-center" style={{ width: '25%' }}>{this.state.notice_date}</td>
                                {/* <td className="success text-center" style={{ width: '25%' }}>좋아요</td>
                                <td className="boardlike text-center" style={{ width: '25%' }}>좋아요</td> */}
                            </tr>
                            <tr>
                                
                            </tr>
                            <tr>
                                <td className="board_contents text-center" colSpan='4' height='200' valign='top'>{this.state.notice_contents}</td>
                            </tr>
                            <tr>
                                <td className='text-right' colspan='4'> 
                                    <Link to="/">
                                        <Button className="lunchBox-btn-rec-line" variant="contained" color="primary">
                                            Back
                                        </Button>
                                    </Link>
                                </td>
                            </tr>	
                        </table>
                    </div>

                    <div>
                        <table className="table" id="comment">
                            <thead>
                                <tr>
                                    <th>작성자</th>
                                    <th>내용</th>
                                    <th>작성일</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.notice_comments.map( comment => 
                                    <tr className="data2" key = {comment.com_no}>
                                        <td class="com_mem">{comment.com_mem.member_nickName}</td>
                                        <td class="com_contents">{comment.com_contents}</td>
                                        <td class="com_date">{comment.com_date}</td>
                                        <td class="com_delete"><input class="com_delete_button btn btn-xs btn-outline-success" type="submit" value="삭제"/></td>
                                    </tr>
                                    // <TableRow key={comment.com_no}>
                                    //     <TableCell align="center">{comment.com_mem.member_nickName}</TableCell>
                                    //     <TableCell align="center">{comment.com_contents}</TableCell>
                                    //     <TableCell align="center">{comment.com_date}</TableCell>
                                        
                                    //     <TableCell align="center" onClick={()=> this.deleteNC(comment.com_no)}>
                                    //         <DeleteIcon />
                                    //     </TableCell>
                                    // </TableRow>
                                )}
                                
                            </tbody>
                            <tr>
                                <form className="write" >
                                    <fieldset>
                                        <td>
                                            <input 
                                                type='text' 
                                                size='70' 
                                                className="add_contents input-sm" 
                                                
                                                width='100%'
                                            />
                                        </td>
                                        <td>
                                            <input type='submit' id="addcomment" value="작성" name="search" className="comment_write btn btn-sm btn-primary" style={{textAlign:'left'}}/>
                                        </td>
                                        <td>
                                        </td>
                                    </fieldset>
                                </form>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>  
        )
      }
}

export default withStyles(styles)(DetailNoticeComponent);