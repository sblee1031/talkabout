# 토론 커뮤니티 (Talk About)
작성중....
## 사용기술
### * Back-End : JDK 1.8, SpringBoot(2.5.3), Eclipse, Maven, Mybatis, OracleDB 11g, WebSocket
### * Front-End :  Ajax, Jquery, BootStrap4, React
### * 버전관리 : SourceTree, GitHub
### * 추가 오픈소스 : javax.mail(1.6.2), React-DatePicker, CKEditor5

## 프로젝트 일정
[프로젝트 기간]
총 개발 기간 : 2021/08/09 ~ 09/06 (5주)


|      구분      |     기간      |                             활동                             |        비고        |
| :------------: | :-----------: | :----------------------------------------------------------: | :----------------: |
|      기획      | 08/09 ~ 08/13 |                  프로젝트 기획 및 ERD 구성                  |   주제 및 시나리오    |
|      개발      | 08/14 ~ 08/22 |               DTO 설계 및 RESTful API 설계                 |  Back-End  |
|            | 08/23 ~ 08/31 | Front-End 제작, React사용 | React 및 JS |
| 수정/보완/발표 | 09/01 ~ 09/06 |           코드 정리, 수정, 보완 & 결과 보고서 작성           |     오류 수정      |

## 나의 역할
  * 프로젝트 PM
  
  ### 소셜 로그인 
    * 카카오, 구글 API를 사용하여, 회원 가입 및 인증
  
  ### 토론 모집 게시판
    * CRUD (토론 주제 제안, 토론일자, 토론시간, 토론내용)
    * 토론참여 및 참여취소
    * 토론 승인 요청 메일 발송
    * React-DatePicker를 사용한 날짜 및 시간 선택
    * CKEditor5를 사용하여, 에디터 기능 및 외부링크(URL첨부, YouTube영상 첨부)
  ### 토론 배틀
    * WebSocket을 사용한 실시간 채팅 및 근거 등록 기능.
    * 토론자1, 토론자2 경우 토론자 영역에서 채팅.
    * 토론자가 아닐 경우 관중 영역에서 채팅.
    * 주제에 뒷받침 되는 근거1, 근거2, 근거3을 WebSocket을 사용하여 참여한 인원 전부에게 표시.
    * 해당 주제에 대한 실시간 투표.
  ### 관리자 페이지
    1. 공지사항 게시판 
       * CRUD (공지사항 작성, 수정, 삭제)
       * 공지사항 댓글 삭제
    2. 자유게세판
       * 게시글 삭제
       * 댓글 삭제
    3. 토론 관리
       * 토론 삭제
       * 토론 승인, 토론 취소
       * 토론 승인 후 토론자1, 토론자2에게 메일 발송
       * 토론 결과 댓글 보기, 댓글 삭제
 
## 주요 기능
### 토론Process
#### 로그인 [![화살표](https://img.shields.io/static/v1?label=&message=-->&color=blue)]()  토론 주제 작성 [![화살표](https://img.shields.io/static/v1?label=&message=-->&color=blue)]() 토론자1, 토론자2 선정 [![화살표](https://img.shields.io/static/v1?label=&message=-->&color=blue)]() 관리자 토론 승인 [![화살표](https://img.shields.io/static/v1?label=&message=-->&color=blue)]()토론배틀(채팅, 투표) [![화살표](https://img.shields.io/static/v1?label=&message=-->&color=blue)]() 토론 종료 후 [![화살표](https://img.shields.io/static/v1?label=&message=-->&color=blue)]() 토론결과

1. 로그인
   * 구글, 카카오 로그인을 통한 회원 가입
  * 신규 회원의 경우 닉네임, 생년월일, 성별 값을 입력 받음.
 * 기존 회원의 경우 DB에 저장되어있는 닉네임 값으로 로그인.
 * 비회원일 경우 모든 게시판 읽기만 가능.
 
2. 공지사항
 * 공지정보, 업데이트, 이벤트 등 게시물 확인.
 * 게시글 댓글 작성
 
3. 자유게시판
 * 자유로운 게시글 작성, 댓글 작성 가능
 * 본인 글은 수정 및 삭제 가능
4. 

## 이슈사항
