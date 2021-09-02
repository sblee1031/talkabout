import React from "react";
import _ from "lodash";
import { Link } from "react-router-dom";

const Pagination = (props) => {
  //const pageCount = 5; //한페이지 표시될 개수
  const { itemsCount, pageSize, currentPage, onPageChange } = props; // 각각 아이템(영화목록) 개수, 한 페이지에 보여줄 아이템(영화목록) 개수
  const totalPage = Math.ceil(itemsCount / pageSize); // 몇 페이지가 필요한지 계산 토탈페이지수
  const pageGroup = Math.ceil(currentPage / pageSize); //한 페이지에 보여줄 페이지 그룹수
  if (totalPage === 1) return null; // 1페이지 뿐이라면 페이지 수를 보여주지 않음

  var last = pageGroup * pageSize; // 화면에 보여질 마지막 페이지 번호
  if (last > totalPage) last = totalPage;
  var first = last - (pageSize - 1); // 화면에 보여질 첫번째 페이지 번호
  if (first < 1) first = 1;
  var next = last + 1;
  var prev = first - 1;
  // console.log("last", last);
  // console.log("next", next);
  // console.log("prev", prev);
  // console.log("totalPage", totalPage);
  // console.log("pageGroup", pageGroup);
  // console.log("pageGroup", pageGroup);
  const pages = _.range(first, last + 1); // 마지막 페이지에 보여줄 컨텐츠를 위해 +1, https://lodash.com/docs/#range 참고

  return (
    <nav style={{ display: "flex", justifyContent: "center" }}>
      {" "}
      {/* VSCode 입력: nav>ul.pagination>li.page-item>a.page-link */}
      <ul className="pagination">
        <li className="prev">
          <Link className="page-link" onClick={() => onPageChange(prev)}>
            &laquo;
          </Link>
        </li>
        {pages.map((page) => (
          <li
            key={page}
            className={page === currentPage ? "page-item active" : "page-item"} // Bootstrap을 이용하여 현재 페이지를 시각적으로 표시
            style={{ cursor: "pointer" }}
          >
            <Link className="page-link" onClick={() => onPageChange(page)}>
              {page}
            </Link>{" "}
            {/* 페이지 번호 클릭 이벤트 처리기 지정 */}
          </li>
        ))}
        <li className="next">
          <Link className="page-link" onClick={() => onPageChange(next)}>
            &raquo;
          </Link>
        </li>
      </ul>
    </nav>
  );
};

export default Pagination;
