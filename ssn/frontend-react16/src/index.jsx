import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import store from "./redux/store";
import CssBaseline from "@material-ui/core/CssBaseline";
import UserDashboard from "./components/user-dashboard.jsx";
import Dashboard from "./components/dashboard.jsx";
import { CONTEXT_ROOT } from "./globals";

ReactDOM.render(
	<Provider store={store}>
		<BrowserRouter basename={CONTEXT_ROOT + "/react"}>
				<CssBaseline />
				<Switch>
					<Route exact path="/dashboard" component={Dashboard}/>
					<Route path="/" component={UserDashboard} />
				</Switch>
		</BrowserRouter>
	</Provider>,
	document.getElementById("root")
);
