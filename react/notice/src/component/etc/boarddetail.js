import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Reply from './Reply';
import { withStyles } from '@material-ui/core/styles';
import myFireBase from '../../config/MyFireBase'
import { Redirect } from 'react-router-dom';
// Storage and Database from firebase
const storageRef = (new myFireBase).storageRef;
const databaseRef = (new myFireBase).databaseRef;
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
    boardTopImg: {
        height: '12em',
        width: '100%',
        objectFit: 'cover',
        position: 'relative'
    },
    boardDetailMain: {
        height: "auto",
        marginTop: "1rem",
        marginBottom: "3rem",
        border: "1px solid black"
    },
    boardDetailTitle: {
        height: "3rem",
        lineHeight: "3rem",
        paddingLeft: "1rem",
        boxSizing: "border-box",
        float: "left"
    },
    boardDetailDate: {
        height: "3rem",
        float: "right",
        marginRight: "1rem",
        lineHeight: "3rem",
        textAlign: "center"
    },
    boardDetailCreatedby: {
        height: "2rem",
        lineHeight: "2rem",
        padding: "1rem",
        boxSizing: "border-box",
        borderTop: "1px Solid black"
    },
    boardDetailContent: {
        height: "29rem",
        padding: "2rem"
    },
    boardDetailReplyHitNum: {
        height: "2rem",
        lineHeight: "2rem",
        paddingLeft: "1rem",
        boxSizing: "border-box"
    },
    boardDetailReplyNum: {
        borderRight: "1px solid black",
        width: "7rem",
        float: "left",
        textAlign: "center"
    },
    boardDetailHitNum: {
        width: "7rem",
        float: "left",
        textAlign: "center"
    },
    boardDetailReply: {
        backgroundColor: "#eeeeee",
        height: "auto",
        margin: "1rem",
        padding: "1rem",
        boxSizing: "border-box"
    },
    boardDetailReplySubmitContent: {
        width: "90%",
        height: "5rem",
        float: "left",
        marginTop: '1rem',
        padding: "0.5rem",
        backgroundColor: "white",
        boxSizing: "border-box",
        fontSize: "1rem",
        outline: "0px",
        color: "#494949",
        fontWeight: "normal",
        '@media (max-width:960px)': {
            width: '70%',
        }
    },
    boardDetailReplySubmitButton: {
        width: "10%",
        height: "5rem",
        float: "left",
        marginTop: '1rem',
        paddingLeft: "1rem",
        boxSizing: "border-box",
        '@media (max-width:960px)': {
            width: '30%',
        }
    }
})

class BoardDetail extends Component {
    // Init
    constructor(props) {
        super(props);
        this.state = {
            imgSrcBoardTop: '',
            replyData: {},
            replySubmitContent: '',
            replyNum: 0
        }
    }
    componentDidMount() {
        this.getImage();
        this.getReplyData();
    }
    // Get Image
    getImage() {
        storageRef.child('boardtop.jpg').getDownloadURL().then((url) => {
            this.setState({ imgSrcBoardTop: url });
        }).catch((error) => {
        })
    }
    // Get Reply Data
    getReplyData() {
        databaseRef.child('reply/').once('value').then(data=>{
            this.setState({ replyData: this.filterReplyData(data.val(), this.props.location.state.id) })
        })
    }
    // Post Reply Data
    postReplyData(reply) {
        let postData ={
            boardId: reply.boardId,
            content: reply.content,
            name: reply.name,
            date: reply.date
        }
        let postKey =  databaseRef.child('reply/').push().key;
        let updates = {};
        updates['/reply/'+postKey] = postData;
        databaseRef.update(updates);
        this.getReplyData();
    }
    // Filter Reply Data with Board ID
    filterReplyData = (totalReplyData, boardId) => {
        let filteredReplyData = [];
        let replyNum = 0;
        Object.keys(totalReplyData).map((idx) => {
            const r = totalReplyData[idx];
            if(r.boardId === boardId){
                filteredReplyData.push(r);
                replyNum += 1;
            }
        })
        this.setState({replyNum: replyNum});
        return filteredReplyData;
    }
    // Submit Reply
    handleSubmit = () => {
        const reply = {
            boardId: this.props.location.state.id,
            content: this.state.replySubmitContent,
            name: 'Unknown User',
            date: (new Date()).getFullYear() +'-'+(new Date()).getMonth() + '-' + (new Date()).getDate()
        }
        if (!reply.content || !reply.boardId) {
            alert('Write Reply Content');
            return;
        }
        this.postReplyData(reply);
        this.setState({replySubmitContent:''});
        this.setState({replyNum:this.state.replyNum+1});
    }
    // Handle Change Value
    handleValueChange = (e) => {
        let nextState = {};
        nextState[e.target.name] = e.target.value;
        this.setState(nextState);
    }
    render() {
        // Set classes
        const { classes } = this.props;
        if (this.props.location.state === undefined) {
            return (
                <Redirect to="/main/board" />
            )
        } else {
            // Return
            return (
                <div>
                    <div className={classes.boardTop}>
                        <div className={classes.boardTopBackground}></div>
                        <img className={classes.boardTopImg} src={this.state.imgSrcBoardTop} alt="boardtop" />
                        <div className={classes.boardTopText}>
                            Notices of Sallab
                        </div>
                    </div>
                    <div style={{ 'margin-left': '5%', 'margin-right': '5%' }}>
                        <Link to="/main/board">
                            <button className="lunchBox-btn-rec-line" style={{ "width": "5rem", "margin-top": "1rem" }}>
                                Back
                            </button>
                        </Link>
                        <div className={classes.boardDetailMain}>
                            <div style={{ "height": "3rem" }}>
                                <div className={classes.boardDetailTitle}>
                                    <div className="lunchBox-textField-black-title">
                                        Title
                                    </div>
                                </div>
                                <div className={classes.boardDetailDate}>
                                    <div className="lunchBox-textField-black-content-small">
                                        {this.props.location.state.createdDate}
                                    </div>
                                </div>
                            </div>
                            <div className={classes.boardDetailCreatedby}>
                                <div className="lunchBox-textField-black-content-small">
                                    {this.props.location.state.createdby}
                                </div>
                            </div>
                            <div className={classes.boardDetailContent}>
                                <div className="lunchBox-textField-black-content-small">
                                    {this.props.location.state.content}
                                </div>
                            </div>
                            <div className={classes.boardDetailReplyHitNum}>
                                <div className={classes.boardDetailReplyNum}>
                                    <div className="lunchBox-textField-black-content-small">Reply ({this.state.replyNum})</div>
                                </div>
                                <div className={classes.boardDetailHitNum}>
                                    <div className="lunchBox-textField-black-content-small">Hit ({this.props.location.state.hit})</div>
                                </div>
                            </div>
                            <div className={classes.boardDetailReply}>
                                <div>
                                    {Object.keys((this.state.replyData)).map((idx) => {
                                        const r = this.state.replyData[idx];
                                        return (
                                            <Reply name={r.name} date={r.date} content={r.content} />
                                        )
                                    })}
                                </div>
                                <div style={{ "overflow": "auto" }}>
                                    <input value={this.state.replySubmitContent} name="replySubmitContent" onChange={this.handleValueChange} className={classes.boardDetailReplySubmitContent} />
                                    <div className={classes.boardDetailReplySubmitButton}>
                                        <button className="lunchBox-btn-rec-line" style={{ "width": "100%", "height": "100%" }} onClick={this.handleSubmit}>Submit</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p style={{ "display": "none" }}>{this.props.location.state.id}</p>
                </div>
            )
        }

    }
}

export default withStyles(styles)(BoardDetail);
