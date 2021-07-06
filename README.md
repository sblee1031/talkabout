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
### 1. DAO 구현

## 210705
### 1. Member 테이블 member_able 변수 추가(탈퇴확인 변수)
- 1 : 활동회원(Default) / 2 : 탈퇴회원
- MemberDTO 수정
- ERD 수정
- 세팅 SQL구문 수정
___
# Todo
1. DebateDetail DTO 수정
```java
// com\talkabout\dto\DebateDetail.java

// 수정 전
private int debate_no;

// 수정 후
private int detail_no; 
```
  -  -> 
