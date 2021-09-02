import React, { Component } from 'react';
import ApiService from "../../ApiService";

import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";

import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

class EditNoticeComponent extends Component{

  constructor(props){
    super(props);

    this.state = {
      notice_no: window.localStorage.getItem("noticeNo"),
      notice_type: '',
      notice_title: '',
      notice_contents: '',
      message: null
    }
  }

  componentDidMount(){
    // this.loadUser();
  }

  loadUser = () => {
    ApiService.fetchNoticeByNo(window.localStorage.getItem("userID"))
      .then( res => {
        let user = res.data;
        this.setState({
          id: user.id,
          username: user.username,
          firstName: user.firstName,
          lastName: user.lastName,
          age: user.age,
          salary: user.salary
        })
      })
      .catch(err => {
        console.log('loadUser() 에러', err);
      });
  }

  onChange = (e) => {
    this.setState({
      [e.target.name] : e.target.value
    });
  }

  saveNotice = (e) => {
    e.preventDefault();

    let notice = {
      notice_no: this.state.notice_no,
      notice_type: this.state.notice_type,
      notice_title: this.state.notice_title,
      notice_contents: this.state.notice_contents,
    }

    ApiService.editNotice(notice)
      .then( res => {
        this.setState({
          message : 'saveNotice Completed'
        })
        this.props.history.push('/');
      })
      .catch(err => {
        console.log('saveNotice() 에러', err);
      })
  }

  render(){
    return(
      <div>
        <Typography variant="h4" style={style}>Edit Notice</Typography>
        <form>

          <FormControl variant="outlined" fullWidth margin="normal">
            <InputLabel id="demo-simple-select-outlined-label">공지사항 분류</InputLabel>
            <Select
              labelId="demo-simple-select-outlined-label"
              id="demo-simple-select-outlined"
              value={this.state.notice_type}
              onChange={this.onChange}
              label="Age"
              name="notice_type"
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              <MenuItem value={'공지사항'}>공지사항</MenuItem>
              <MenuItem value={'이벤트'}>이벤트</MenuItem>
              <MenuItem value={'업데이트'}>업데이트</MenuItem>
              <MenuItem value={'점검'}>점검</MenuItem>
            </Select>
          </FormControl>

          <TextField type="text" placeholder="please input type" name="notice_type" 
          fullWidth margin="normal" value={this.state.notice_type} onChange={this.onChange} />

          <TextField type="text" placeholder="please input title" name="notice_title" 
          fullWidth margin="normal" value={this.state.notice_title} onChange={this.onChange} />

          <TextField type="text" placeholder="please input contents" name="notice_contents" 
          fullWidth margin="normal" value={this.state.notice_contents} onChange={this.onChange} />
          
          <Button variant="contained" color="primary" onClick={this.saveNotice}>Save</Button>

        </form>
      </div>
    );
  }
}

const style = {
  display: 'flex',
  justifyContent: 'center'
}

export default EditNoticeComponent;       