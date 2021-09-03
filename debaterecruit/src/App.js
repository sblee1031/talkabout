import "./App.css";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Header from "./component/Header";
import DebateList from "./component/DebateList";
import DebWrite from "./component/DebWrite";
import DebateView from "./component/DebateView";
import { Container } from "react-bootstrap";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Container>
          <Header />
          <Switch>
            <Route path="/ta_front/debrecruit.html">
              <DebateList />
            </Route>
            <Route path="/ta_front/debrecruit/write">
              <DebWrite />
            </Route>
            <Route
              path="/ta_front/debrecruit/modify"
              component={DebWrite}
            ></Route>
            <Route path="/ta_front/debrecruit/:no">
              <DebateView />
            </Route>
          </Switch>
        </Container>
      </div>
    </BrowserRouter>
  );
}

export default App;
