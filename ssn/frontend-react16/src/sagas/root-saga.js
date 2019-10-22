import { all } from "redux-saga/effects";
import { watcherApiGetFriends } from "./users-saga";
import { watcherAddComments, watcherGetComments } from "./comments-saga";
import { watcherAddPost, watcherUpdatePost,watcherGetPosts} from "./posts-saga";

export default function* rootSaga() {
	yield all([
		watcherAddComments(),
		watcherGetComments(),
		watcherApiGetFriends(),
		watcherAddPost(),
		watcherUpdatePost(),
		watcherGetPosts()
	]);
}
