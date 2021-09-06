# 구조
## index.html
- 리액트에서는 html 페이지 하나로 웹 앱을 생성(SPA)
- 앞으로 작성할 모든 리액트 컴포넌트는 id가 root인 div 태그 내에서 렌더링

## index.js
- 리액트 앱 내에 존재하는 redner 중 최상위에 위치한 render가 존재하는 파일
- React.DOM.render(...) : App.js 파일에서 렌더링한 App 컴포넌트를 index.html 파일 내의 id가 root인 div 태그 내에서 실행하라는 의미

## src 폴더
- 리액트 앱 전체(global, 전역)에 적용되는 자바스크립트 파일과 CSS 파일이 위치
- 앞으로 작성할 모든 리액트 컴포넌트를 해당 폴더 내에 생성

## App.js
- 루트 컴포넌트
- 브라우저에서 실제로 렌더링되는 컴포넌트를 포함
- JSX라는 확장자 파일을 리턴

# 컴포넌트
- 리액트로 만든 하나의 웹앱을 구성하는 각 레고 블럭
- 각 컴포넌트는 props와 state를 통해 데이터를 입력받아 브라우저에서 이를 렌더링하여 UI를 통해 결과물을 출력
- 컴포넌트는 사용자가 직접 생성 가능하며 다른 웹앱에서도 재사용 가능
- 함수형 / 클래스형
## 함수형 / 클래스형 컴포넌트
```js
// 함수형
import React from 'react';

const UserComponent = (props) => {
  return(
    <div className="container">
      컴포넌트 내용 작성부분
    </div>
  );
}

export default UserComponent;
```

```js
// 클래스형
import React, { Component } from 'react';

class UserComponent extends Component{
  render(){
    return(
      <div className="container">
        컴포넌트 내용 작성부분
      </div>
    );
  }
}

export default UserComponent;
```
- 함수형이 클래스형보다 단순한 형태로 구성
- 컴포넌트에 하나의 함수만 사용하는 경우라면 함수형을 사용하며, 그 이상으로 복잡하고 정교한 형태의 컴포넌트를 구성 시 클래스형을 사용

# props / state
## props

## state

## props / state 차이점

# Component LifeCycle API
- 컴포넌트를 생성 시 `constructor` → `componentWillMount` → `render` → `componentDidMount` 순으로 진행
- 컴포넌트를 제거 시 `componentWillUnMount` 메소드만 실행
- 컴포넌트의 prop이 변경 시 `componentWillReceiveProps` → `shouldComponentUpdate` → `componentWillUpdate` → `render` → `componentDidUpdate` 순으로 진행
- state가 변경 시 props를 받았을 때와 비슷하지만 `shouldComponentUpdate` 메소드부터 시작

# Reference
[리액트+스프링부트 게시판 1](https://m.blog.naver.com/rudnfskf2/222148407272)  
[리액트+스프링부트 게시판 2](https://dsc-sookmyung.tistory.com/21?category=929828)  
[동적 라우팅](https://velog.io/@hyounglee/TIL-57)  
[리액트 공부 블로그](https://velog.io/@edie_ko)  
[material UI 예제](https://velog.io/@eunjin/React-Material-UI-Select-Box-%EC%82%AC%EC%9A%A9%EB%B0%A9%EB%B2%95-%EC%BB%A4%EC%8A%A4%ED%85%80%ED%95%98%EA%B8%B0)   
[토이 예제 - lunchbox](https://gitlab.com/n113345/lunchbox/-/tree/master/)  
[토이 예제 - 드림 리멤버](https://github.com/seongbinko/remember_dream)  