(this.webpackJsonptalkabout=this.webpackJsonptalkabout||[]).push([[0],{158:function(e,t,c){"use strict";c.r(t);var s=c(0),a=c.n(s),n=c(21),i=c.n(n),l=(c(88),c(89),c(16)),r=c(11),b=c(2);function j(){return Object(b.jsx)("div",{className:"header",style:{marginTop:"10px"},children:Object(b.jsx)("a",{style:{textDecoration:"none",color:"black"},href:"/ta_front/debrecruit.html",children:Object(b.jsx)("h1",{children:"\ud1a0\ub860 \ubaa8\uc9d1"})})})}var d=c(9),o=c(167),u=c(168),h=(c(91),c(74)),O=c.n(h),x=function(e){var t=e.page,c=e.count,s=e.setPage;return Object(b.jsx)(O.a,{activePage:t,itemsCountPerPage:5,totalItemsCount:c,pageRangeDisplayed:5,prevPageText:"\u2039",nextPageText:"\u203a",onChange:s})},m=c(166),g=c(75);function p(){var e,t=Object(s.useState)({}),c=Object(d.a)(t,2),a=c[0],n=c[1],i=Object(s.useState)(1),r=Object(d.a)(i,2),j=r[0],h=r[1],O=Object(s.useState)(1),p=Object(d.a)(O,2),f=p[0],v=p[1],y=Object(s.useState)(),S=Object(d.a)(y,2),N=S[0],w=S[1],D=Object(s.useState)("http://localhost:9999/ta_back/debrecruit/list?pageNo=".concat(j,"&pageSize=").concat(5)),_=Object(d.a)(D,2),k=_[0],C=_[1];function T(e){var t="http://localhost:9999/ta_back/debrecruit/list?pageNo=".concat(e,"&pageSize=").concat(5),c="http://localhost:9999/ta_back/debrecruit/list/".concat(N,"?pageNo=").concat(e,"&pageSize=").concat(5);N?(C(c),h(e)):(C(t),h(e))}return Object(s.useEffect)((function(){fetch(k,{method:"GET"}).then((function(e){return e.json()})).then((function(e){n(e),v(e.lastRow)}))}),[k]),Object(b.jsxs)(b.Fragment,{children:[Object(b.jsxs)("div",{style:{textAlign:"right",marginRight:"10px",display:"block"},children:[Object(b.jsx)("label",{style:{fontSize:"15pt",fontWeight:"600"},children:Object(b.jsxs)(m.a,{className:"mb-3",children:[Object(b.jsx)(m.a.Text,{id:"inputGroup-sizing-default",children:"\uac80\uc0c9"}),Object(b.jsx)(g.a,{"aria-label":"Default","aria-describedby":"inputGroup-sizing-default",placeholder:"\uc81c\ubaa9&\ub0b4\uc6a9",onChange:function(e){w(e.target.value)}})]})}),Object(b.jsx)(o.a,{style:{marginLeft:"10px"},className:"buttons",variant:"success",onClick:function(e){N?T(1):(e.preventDefault(),alert("\uac80\uc0c9\uc5b4\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694"))},children:"\uac80\uc0c9"})]}),Object(b.jsx)("div",{className:"divButton",style:{textAlign:"right",margin:"10px"},children:Object(b.jsx)(l.b,{className:"write",style:{textDecoration:"none",textDecorationColor:"black"},to:"/ta_front/debrecruit/write",children:Object(b.jsx)(o.a,{className:"buttons",variant:"success",children:"\ud1a0\ub860 \uc791\uc131"})})}),Object(b.jsxs)(u.a,{responsive:"xl",hover:!0,children:[Object(b.jsx)("thead",{children:Object(b.jsxs)("tr",{style:{fontSize:"14pt"},children:[Object(b.jsx)("th",{style:{width:"10%"},children:"\ud1a0\ub860\ubc88\ud638"}),Object(b.jsx)("th",{style:{width:"25%"},children:"\ud1a0\ub860\uc81c\ubaa9"}),Object(b.jsx)("th",{style:{width:"10%"},children:"\uc791\uc131\uc790"}),Object(b.jsx)("th",{style:{width:"15%"},children:"\uc791\uc131\uc2dc\uac04"}),Object(b.jsx)("th",{style:{width:"10%"},children:"\ud1a0\ub860\uc2dc\uac04"}),Object(b.jsx)("th",{style:{width:"10%"},children:"\uc9c4\ud589\uc0c1\ud0dc"})]})}),Object(b.jsx)("tbody",{children:null===(e=a.debatelist)||void 0===e?void 0:e.map((function(e){return Object(b.jsxs)("tr",{children:[Object(b.jsx)("td",{children:e.debate_no}),Object(b.jsx)("td",{children:Object(b.jsx)(l.b,{to:"/ta_front/debrecruit/".concat(e.debate_no),children:e.debate_topic})}),Object(b.jsx)("td",{children:e.debate_writer.member_nickName}),Object(b.jsx)("td",{children:e.debate_date}),Object(b.jsx)("td",{children:e.debate_time}),Object(b.jsx)("td",{children:e.debate_status})]},e.debate_no)}))})]}),Object(b.jsx)("div",{className:"paging",children:Object(b.jsx)(x,{page:j,count:f,setPage:T})})]})}var f=c(34),v=c(35),y=c.n(v),S=c(171),N=c(172),w=c(77),D=c.n(w),_=function(e){var t=Object(s.useState)(),c=Object(d.a)(t,2),a=c[0],n=c[1],i=new Date;return Object(b.jsx)(D.a,{selected:a,onChange:function(t){n(t),e.setDate(t)},showTimeSelect:!0,minDate:i,filterTime:function(e){var t=new Date,c=new Date(e);return t.getDate()<c.getDate()?t.getHours():t.getHours()+1<c.getHours()},dateFormat:"yyyy/MM/dd/ h:mm aa",children:Object(b.jsx)("div",{style:{color:"green",fontSize:"15pt"},children:"\ud1a0\ub860 \uac00\ub2a5 \uc2dc\uac04"})})};c(73);function k(e){var t=Object(s.useState)(""),c=Object(d.a)(t,2),a=c[0],n=c[1],i=Object(s.useState)(""),j=Object(d.a)(i,2),u=j[0],h=j[1],O=Object(s.useState)(""),x=Object(d.a)(O,2),m=x[0],g=x[1],p=Object(s.useState)({}),v=Object(d.a)(p,2),w=(v[0],v[1]),D=Object(s.useState)(""),k=Object(d.a)(D,2),C=k[0],T=k[1],z=Object(s.useState)("30"),A=Object(d.a)(z,2),E=A[0],B=A[1],P=Object(s.useState)(!1),W=Object(d.a)(P,2),F=W[0],H=W[1],I=Object(r.f)();return Object(b.jsxs)(b.Fragment,{children:[Object(b.jsxs)(S.a,{show:F,variant:"success",children:[Object(b.jsx)(S.a.Heading,{children:"\ube48\uce78\uc774 \uc788\uc2b5\ub2c8\ub2e4!"}),Object(b.jsx)("p",{children:"\ud1a0\ub860 \uc77c\uc790, \uc8fc\uc7a5, \ub0b4\uc6a9\uc744 \ud655\uc778\ud574\uc8fc\uc138\uc694^___^"}),Object(b.jsx)("hr",{}),Object(b.jsx)("div",{className:"d-flex justify-content-end",children:Object(b.jsx)(o.a,{onClick:function(){return H(!1)},variant:"outline-success",children:"Close"})})]}),Object(b.jsx)("div",{className:"writeView",children:Object(b.jsxs)("form",{onSubmit:function(e){if(e.preventDefault(),console.log(a," / ",u),console.log("\uc5d0\ub514\ud130->",m),""===C||""===m||""===a||""===u)H(!0);else{var t={debate_content:m,debate_topic:a+" VS "+u,discuss1:a,discuss2:u,debateDate:C,debateTime:E};fetch("http://localhost:9999/ta_back/debrecruit/write",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(t)}).then((function(e){return e.json()})).then((function(e){console.log("\uacb0\uacfc->",e),I.push("/ta_front/debrecruit.html")}))}},children:[Object(b.jsxs)("div",{className:"debDate",style:{fontSize:"10pt"},children:[Object(b.jsxs)("label",{className:"labelDebDate",children:["\ud1a0\ub860\uc77c\uc790",Object(b.jsx)(_,{setDate:function(e){T(function(){var e=new Date,t=e.getFullYear().toString(),c=e.getMonth()+1;c=c<10?"0"+c.toString():c.toString();var s=e.getDate();s=s<10?"0"+s.toString():s.toString();var a=e.getHours();a=a<10?"0"+a.toString():a.toString(),Number.parseInt(a)+1===24&&(a="00");var n=e.getMinutes();return t+"-"+c+"-"+s+" "+a+":"+(n=n<10?"0"+n.toString():n.toString())+":00"}())}})]}),Object(b.jsxs)("label",{className:"labelDebDate",children:["\ud1a0\ub860\uc81c\ud55c\uc2dc\uac04",Object(b.jsx)("br",{}),Object(b.jsx)(N.a.Group,{controlId:"exampleForm.SelectCustom",children:Object(b.jsxs)(N.a.Control,{as:"select",onChange:function(e){B(e.target.value)},value:E,custom:!0,children:[Object(b.jsx)("option",{value:"30",children:"30\ubd84"}),Object(b.jsx)("option",{value:"60",children:"60\ubd84"}),Object(b.jsx)("option",{value:"120",children:"120\ubd84"})]})})]})]}),Object(b.jsxs)("div",{className:"divDiscuss",style:{width:"100%"},children:[Object(b.jsxs)("label",{className:"labelDiscuss",children:["\uc8fc\uc7a5 1 ",Object(b.jsx)("br",{}),Object(b.jsx)("input",{className:"inputDiscuss1",name:"discuss1",style:{width:"100%"},onChange:function(e){n(e.target.value)},vlaue:a})]}),Object(b.jsx)("label",{className:"vs",children:" VS "}),Object(b.jsxs)("label",{className:"labelDiscuss",children:["\uc8fc\uc7a5 2 ",Object(b.jsx)("br",{}),Object(b.jsx)("input",{className:"inputDiscuss2",name:"discuss2",style:{width:"100%"},onChange:function(e){h(e.target.value)},vlaue:u})]})]}),Object(b.jsx)("div",{className:"divEditor",style:{minHeight:"00px"},children:Object(b.jsx)(f.CKEditor,{editor:y.a,data:"",config:{toolbar:{items:["heading","|","bold","italic","link","bulletedList","numberedList","|","blockQuote","insertTable","mediaEmbed","undo","redo"]},placeholder:"\ub0b4\uc6a9\uc744 \uc785\ub825\ud574\uc8fc\uc138\uc694"},onReady:function(e){w(e)},onChange:function(e,t){var c=t.getData();g(c),console.log(c)}})}),Object(b.jsxs)("div",{className:"divWriteButton",style:{textAlign:"right",display:"inline-block"},children:[Object(b.jsx)(o.a,{className:"buttonWrite",variant:"outline-success",size:"sm",style:{margin:"10px"},type:"submit",children:"\uc791\uc131\ud558\uae30"}),Object(b.jsx)(l.b,{to:"/ta_front/debrecruit.html",children:Object(b.jsx)(o.a,{className:"buttonBack",variant:"outline-success",size:"sm",style:{margin:"10px"},children:"\ub3cc\uc544\uac00\uae30"})})]})]})})]})}var C=c(169);function T(){var e=Object(s.useState)({}),t=Object(d.a)(e,2),c=(t[0],t[1]),a=Object(s.useState)(""),n=Object(d.a)(a,2),i=n[0],j=n[1],u=Object(s.useState)(""),h=Object(d.a)(u,2),O=h[0],x=h[1],m=Object(s.useState)("\ub0b4\uc6a9"),g=Object(d.a)(m,2),p=g[0],v=g[1],S=Object(s.useState)(""),N=Object(d.a)(S,2),w=N[0],D=N[1],_=Object(s.useState)(""),k=Object(d.a)(_,2),T=k[0],z=k[1],A=Object(s.useState)(""),E=Object(d.a)(A,2),B=E[0],P=E[1],W=Object(s.useState)(""),F=Object(d.a)(W,2),H=F[0],I=F[1],L=Object(s.useState)(""),R=Object(d.a)(L,2),G=R[0],M=R[1],V=Object(r.g)().no,J="http://localhost:9999/ta_back/debrecruit/".concat(V);Object(s.useEffect)((function(){fetch(J,{method:"GET",headers:{"Content-Type":"application/json"}}).then((function(e){return e.json()})).then((function(e){console.log("\ubdf0--\x3e",e),M(e.debate.debate.debate_writer.member_thumb),z(e.debate.debate.debate_startDate),I(e.debate.debate.debate_writer.member_nickName),P(e.debate.debate.debate_time),D(e.debate.debate.debate_date),j(e.debate.detail[0].discuss),x(e.debate.detail[1].discuss),v(e.debate.debate.debate_content)}))}),[J]);return Object(b.jsx)(b.Fragment,{children:Object(b.jsxs)("div",{className:"writeView",style:{marginTop:"50px"},children:[Object(b.jsxs)("div",{className:"divDiscuss",style:{width:"100%",display:"inline",textAlign:"center"},children:[Object(b.jsxs)("div",{className:"writeInfo",style:{display:"inline-block",width:"25%",textAlign:"left",fontWeight:"800"},children:[Object(b.jsxs)("label",{children:["\uc791\uc131\uc790: ",H]}),Object(b.jsx)(C.a,{src:G,style:{height:"50px",marginLeft:"20px"},alt:"\uc378\ub124\uc77c",roundedCircle:!0}),Object(b.jsxs)("label",{children:["\uc791\uc131\uc2dc\uac04 : ",w]})]}),Object(b.jsxs)("label",{className:"labelDiscuss",style:{width:"20%"},children:["\uc8fc\uc7a5 1 ",Object(b.jsx)("br",{}),Object(b.jsx)("input",{className:"inputDiscuss1",name:"discuss1",value:i,style:{textAlign:"center",width:"100%"},readOnly:!0})]}),Object(b.jsxs)("label",{className:"vs",style:{textAlign:"center",width:"5%"},children:[" ","VS"," "]}),Object(b.jsxs)("label",{className:"labelDiscuss",style:{width:"20%"},children:["\uc8fc\uc7a5 2 ",Object(b.jsx)("br",{}),Object(b.jsx)("input",{className:"inputDiscuss2",name:"discuss2",value:O,style:{textAlign:"center",width:"100%"},readOnly:!0})]}),Object(b.jsxs)("div",{className:"debInfo",style:{display:"inline-block",width:"25%",margin:"0px 10px"},children:[Object(b.jsxs)("label",{style:{marginBottom:"10px",fontWeight:"600"},children:["\ud1a0\ub860\uc77c\uc790 :",Object(b.jsx)("input",{className:"debDate",value:T,style:{textAlign:"center",marginLeft:"10px"},readOnly:!0})]}),Object(b.jsxs)("label",{style:{marginBottom:"10px",fontWeight:"600"},children:["\ud1a0\ub860\uc2dc\uac04 :",Object(b.jsx)("input",{className:"debTime",value:B,style:{textAlign:"center",marginLeft:"10px"},readOnly:!0})]})]})]}),Object(b.jsx)("div",{className:"divEditor",style:{overflow:"auto"},children:Object(b.jsx)(f.CKEditor,{editor:y.a,data:p,config:{toolbar:[]},onReady:function(e){e.isReadOnly=!0,c(e)}})}),Object(b.jsxs)("div",{className:"divWriteButton",style:{textAlign:"right"},children:[Object(b.jsx)(o.a,{className:"buttonWrite",variant:"outline-success",size:"sm",style:{margin:"10px"},type:"submit",children:"\uc218\uc815\ud558\uae30"}),Object(b.jsx)(l.b,{to:"/ta_front/debrecruit.html",children:Object(b.jsx)(o.a,{className:"buttonBack",variant:"outline-success",size:"sm",style:{margin:"10px"},children:"\ub3cc\uc544\uac00\uae30"})})]})]})})}var z=c(170);var A=function(){return Object(b.jsx)(l.a,{children:Object(b.jsx)("div",{className:"App",children:Object(b.jsxs)(z.a,{children:[Object(b.jsx)(j,{}),Object(b.jsxs)(r.c,{children:[Object(b.jsx)(r.a,{path:"/ta_front/debrecruit.html",children:Object(b.jsx)(p,{})}),Object(b.jsx)(r.a,{path:"/ta_front/debrecruit/write",children:Object(b.jsx)(k,{})}),Object(b.jsx)(r.a,{path:"/ta_front/debrecruit/:no",children:Object(b.jsx)(T,{})})]})]})})})};c(156);i.a.render(Object(b.jsx)(a.a.StrictMode,{children:Object(b.jsx)(A,{})}),document.getElementById("root"))},88:function(e,t,c){},89:function(e,t,c){},91:function(e,t,c){}},[[158,1,2]]]);
//# sourceMappingURL=main.f755e680.chunk.js.map