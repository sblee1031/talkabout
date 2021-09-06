import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import NoticeListComponent from "./component/notice/NoticeListComponent";

import DetailNoticeComponent from "./component/notice/DetailNoticeComponent";
import { Container } from "react-bootstrap";

class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <Container>
            <Switch>
              <Route
                exact
                path="/ta_front/notice.html"
                component={NoticeListComponent}
              />
              <Route
                exact
                path="/ta_front/notice/"
                component={DetailNoticeComponent}
              />
              <Route
                exact
                path="/ta_front/notice/:no"
                component={DetailNoticeComponent}
              />

              {/* <Route path="/ta_front/notice.html">
                <NoticeListComponent />
              </Route>
              <Route path="/ta_front/notice/:no">
                <DetailNoticeComponent />
              </Route>
              <Route path="/ta_front/notice/write">
                <AddNoticeComponent />
              </Route>
              <Route path="/ta_front/notice/modify">
                <EditNoticeComponent />
              </Route> */}
              {/* <Route exact path="/" component={NoticeListComponent} />
              <Route exact path="/notices/" component={DetailNoticeComponent} />
              <Route exact path="/notices/:no" component={DetailNoticeComponent} />
              <Route exact path="/add-notice" component={AddNoticeComponent} />
              <Route exact path="/edit-notice" component={EditNoticeComponent} /> */}
            </Switch>
          </Container>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
