import './App.css';
import { BrowserRouter, Switch, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import DebateBattleList from './component/DebateBattleList';
import DebateBattleDetail from './component/DebateBattleDetail';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Container>
          <Switch>
            <Route path="/ta_front/debbattle.html">
              <DebateBattleList />
            </Route>
            <Route path="/ta_front/debbattle/:no">
              <DebateBattleDetail />
            </Route>
          </Switch>
        </Container>
      </div>
    </BrowserRouter>
  );
}

export default App;
