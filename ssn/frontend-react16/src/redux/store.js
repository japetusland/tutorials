import { createStore, applyMiddleware } from "redux";
import createSagaMiddleware from "redux-saga";
import rootReducer from "./reducers";
import rootSaga from "../sagas/root-saga";
import { getPageData, LOGGED_USER } from "../globals";

let initialiseSagaMiddleware = createSagaMiddleware();

let state = {
	currentUser: LOGGED_USER,
	friends: getPageData("data-friends",[]),
	posts: getPageData("data-posts",[])
};

let store = createStore(
	rootReducer,
	state,
	applyMiddleware(initialiseSagaMiddleware)
);

initialiseSagaMiddleware.run(rootSaga);

export default store;
