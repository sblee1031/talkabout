# 안진성 테스트
# 진행상황

## 1. 회원

- DAO : 로그인,로그아웃, 회원가입,회원조회, 회원정보변경, 탈퇴
- Sevlet : 로그인,로그아웃, 회원가입,회원조회, 회원정보변경, 탈퇴
- Front : 로그인,로그아웃, 회원가입,회원조회, 회원정보변경, 탈퇴

## 2. 공지사항

## 3. 자유게시판

- DAO : 게시글 검색, 리스트보기, 상세보기, 작성, 수정, 삭제, 조회수 추가, 좋아요
- Servlet : 리스트, 상세보기, 작성
- Front: 리스트, 상세보기, 작성

## 4. 토론모집

- DAO :토론제안, 토론자A,B 등록, 토론삭제, 토론조회,수정
- Sevlet : :토론제안, 토론자A,B 등록, 토론삭제, 토론조회,수정
- Front : 구현된거 까지...

#### 210815 광복절

- TalkAbout SpringBoot 프로젝트 생성, DebateRecruit React 변경
- Debate DTO변경사항(회원Member타입, 날짜 Date타입)으로 변경

# 210816

-Debate 검색기능, 작성하기 화면 구성. -작성 후 DB연결 필요

## 5. 토론배틀

- DAO : 전체리스트 / select Two(주장 A, B) / select One / DebateDetail 생성 / 토론자 수정 / 실제입장시간 수정 / 근거 수정
- Servlet : 아직 X
- Front : 토론배틀 게시판 / 토론배틀 상세

## 6. 토론결과

#

# 작업내역

## 210701

- DTO 1차 제작
- ERD 2차 제작
  [링크](https://www.erdcloud.com/d/YYWimyRYK7asSbXMN)

---

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

---

## 210704

- DAO 구현

---

## 210705

- Member 테이블 member_able 변수 추가(탈퇴확인 변수)
  - 1 : 활동회원(Default) / 2 : 탈퇴회원
- MemberDTO 수정
- ERD 수정
- 세팅 SQL구문 수정

---

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

---

#
