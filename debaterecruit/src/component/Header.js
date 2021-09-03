export default function Header() {
  //const redirect = window.location.replace("/");
  return (
    <div className="header" style={{ marginTop: "10px" }}>
      <a
        style={{ textDecoration: "none", color: "black" }}
        href={"/ta_front/debrecruit.html"}
      >
        <h1>토론 모집</h1>
      </a>
    </div>
  );
}
