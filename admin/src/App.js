import "./App.css";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import AdminLogin from "./component/AdminLogin";
import AdminList from "./component/AdminList";
import { useState } from "react";

function App() {
  const [userInfo, setUserInfo] = useState();
  //console.log("userInfo", userInfo);
  return (
    <BrowserRouter>
      <div className="App">
        <div className="container">
          <Switch>
            <Route path="/ta_front/admin.html">
              <AdminLogin setUserInfo={setUserInfo} />
            </Route>
            <Route path="/ta_front/admin">
              <AdminList userInfo={userInfo} />
            </Route>
          </Switch>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
