import { takeEvery, call, put } from "redux-saga/effects";
import * as actions from "../redux/actions";
import * as postsClient from "../clients/posts-client";

function* watcherAddPost() {
	yield takeEvery(actions.ADD_POST, workerAddPost);
}

function* workerAddPost(action) {
	try {
		yield call(postsClient.addPost, action.post);
		yield put(actions.getPosts(action.post.user));
	} catch (exception) {
		console.log(exception);
	}
}

function* watcherUpdatePost() {
	yield takeEvery(actions.UPDATE_POST, workerUpdatePost);
}

function* workerUpdatePost(action) {
	try {
		yield call(postsClient.updatePost, action.post);
		yield put(actions.getPosts(action.post.user));
	} catch (exception) {
		console.log(exception);
	}
}

function* watcherGetPosts() {
	yield takeEvery(actions.GET_POSTS, workerGetPosts);
}

function* workerGetPosts(action) {
	try {
		let posts = yield call(postsClient.getPosts, action.user);
		if (posts)
			yield put(actions.setPosts(posts));
	} catch (exception) {
		console.log(exception);
	}
}

export { watcherAddPost, watcherUpdatePost, watcherGetPosts };
