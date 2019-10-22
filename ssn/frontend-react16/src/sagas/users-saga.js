import { takeEvery, call, put } from "redux-saga/effects";
import * as actions from "../redux/actions";
import * as usersClient from "../clients/users-client";

function* watcherApiGetFriends() {
	yield takeEvery(actions.GET_FRIENDS, workerGetFriends);
}

function* workerGetFriends() {
	try {
		let friends = yield call(usersClient.getFriends);
		if (friends)
			yield put(actions.setFriends(friends));
	} catch (exception) {
		console.log(exception);
	}
}

export { watcherApiGetFriends };
