import React, { Component } from 'react';
import ApiService from "../../ApiService";

// import '../../css/NoticeModify.css'

class AddNoticeComponent extends Component{

  constructor(props){
    super(props);

    this.state = {
      notice_type: '',
      notice_title: '',
      notice_contents: '',
      notice_admin: '',
      message: null
    }

  }

  onChange = (e) => {
    this.setState({
      [e.target.name] : e.target.value
    })
  }

  saveNotice = (e) => {
    e.preventDefault();

    let notice = {
      notice_type: this.state.notice_type,
      notice_title: this.state.notice_title,
      notice_contents: this.state.notice_contents,
      notice_admin: 'ad3', // Session으로 변경 필요
    }

    ApiService.addNotice(notice)
    .then( res => {
        this.setState({
          message: 'addNotice Completed'
        })
        console.log(this.state.message);
        this.props.history.push('/');
    })
    .catch( err => {
      console.log('saveNotice() 에러', err);
    });

  }

  render(){
    return(
      <div className="container">
        <h2 style = {{ display : 'flex', justifyContent : 'center', marginTop:'5px' }}>Add Notice</h2>
        <div className="bg"></div>
        <form>
          <input type='text' name='name' placeholder='Name'/>
          <input type='text' name='email' placeholder='Title'/>
          <textarea name='text' placeholder='Contents'/>
          <input type='submit' value='submit' className='submit'/>
        </form>

        <form style = {{ display : 'flex', flexFlow : 'row wrap', justifyContent : 'center', marginTop:'5px' }}>
          <select name="notice_type" value={this.state.notice_type} onChange={this.onChange}>
            <option value="none">공지사항 선택</option>
            <option value="korean">공지사항</option>
            <option value="english">이벤트</option>
            <option value="chinese">업데이트</option>
            <option value="spanish">점검</option>
          </select>
          <input type='text' placeholder="please input type" width='100%'></input>
          <input type='text' placeholder="please input title"></input>
          <input type='text' placeholder="please input contents"></input>
        </form>
      </div>
    );
  }
}

// const formContainer = {
//   display : 'flex',
//   flexFlow : 'row wrap'
// }

// const style = {
//   display : 'flex',
//   justifyContent : 'center',
//   marginTop:'5px'
// }

export default AddNoticeComponent;