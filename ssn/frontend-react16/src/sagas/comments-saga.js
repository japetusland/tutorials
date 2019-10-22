import { takeEvery, all, call, put } from "redux-saga/effects";
import * as actions from "../redux/actions";
import * as commentsClient from "../clients/comments-client";

function* watcherAddComments() {
	yield takeEvery(actions.ADD_COMMENT, workerAddComments);
}

function* workerAddComments(action) {
	try {
		yield call(commentsClient.addComment, action.comment);
		yield put(actions.getComments(action.comment.postId));
	} catch (exception) {
		console.log(exception);
	}
}

function* watcherGetComments() {
	yield takeEvery(actions.GET_COMMENTS, workerGetComments);
}

function* workerGetComments(action) {
	try {
		let comments = yield call(commentsClient.getCommentsByPost, action.postId);
		if (comments)
			yield put(actions.setComments(action.postId, comments));
	} catch (exception) {
		console.log(exception);
	}
}

export { watcherAddComments, watcherGetComments };
