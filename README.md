# 작업내역
## 210701
- DTO 1차 제작
- ERD 2차 제작
[링크](https://www.erdcloud.com/d/YYWimyRYK7asSbXMN)  
___

## 210702
- NoticeComment DTO 수정
```java
// com\talkabout\dto\NoticeComment.java

// 수정 전
private Board com_board; // com_board.board_no

// 수정 후
private Notice com_notice; // com_notice.notice_no
```
- ERD 수정
___

## 210704
- DAO 구현
___

## 210705
- Member 테이블 member_able 변수 추가(탈퇴확인 변수)
  - 1 : 활동회원(Default) / 2 : 탈퇴회원
- MemberDTO 수정
- ERD 수정
- 세팅 SQL구문 수정
___

## 210707
- DTO Date 타입 -> String 타입으로 수정
```
NOTICE
- NOTICE_DATE : 공지사항 작성 시각 / SYSDATE

NOTICECOMMENT
- COM_DATE : 공지사항 댓글 작성 시각 / SYSDATE

BOARD
- BOARD_DATE : 자유게시판 게시글 작성 시각 / SYSDATE

BOARDCOMMENT
- COM_DATE : 자유게시판 댓글 작성 시각 / SYSDATE

DEBATE
- DEBATE_DATE : 토론 예정일 / 사용자 입력값
- DEBATE_STARTDATE : 토론의 실제 시작 시각 / SYSDATE
- DEBATE_ENDDATE : 토론의 실제 종료 시각 / SYSDATE

DEBATEDETAIL
- IN_TIME : 토론자의 실제 입장 시각 / SYSDATE

DEBATECOMMENT
- COM_DATE : 토론결과 댓글 작성 시각 / SYSDATE
```
- DAO 바인딩 구문 수정
```java
/* 수정 전 */
try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Date in_time = rs.getDate("IN_TIME");
			}
}

/* 수정 후 */
try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(select_SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String in_time = rs.getString("IN_TIME");
			}
}
```
___
#

# Todo
- 메인 페이지 제작
- Service, DAO 구현 마무리
