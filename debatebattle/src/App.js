import './App.css';
import { BrowserRouter, Switch, Route } from "react-router-dom";
import { Container } from "react-bootstrap";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Container>
          <Switch>
            <Route path="/ta_front/debbattle.html">
              
            </Route>
          </Switch>
        </Container>
      </div>
    </BrowserRouter>
  );
}

export default App;
