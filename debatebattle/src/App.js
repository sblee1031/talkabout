import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter, Route } from "react-router-dom";
import Battle from "./component/battle";
import "./component/css/bootstrap.css";
import { Container } from "react-bootstrap";

function App() {
  return (
    <div className="App">
      <Container>
        <BrowserRouter>
          <Route path="/">
            <Battle />
          </Route>
        </BrowserRouter>
      </Container>
    </div>
  );
}

export default App;
