export const GET_LOGGED_USER = "GET_LOGGED_USER";
export const GET_FRIENDS = "GET_FRIENDS";
export const SET_FRIENDS = "SET_FRIENDS";
export const ADD_POST = "ADD_POST";
export const UPDATE_POST = "UPDATE_POST";
export const GET_POSTS = "GET_POSTS";
export const SET_POSTS = "SET_POSTS";
export const ADD_COMMENT = "ADD_COMMENT";
export const GET_COMMENTS = "GET_COMMENTS";
export const SET_COMMENTS = "SET_COMMENTS";
export const SET_CURRENT_USER = "SET_CURRENT_USER";

export function getFriends() {
	return { type: GET_FRIENDS };
}

export function setFriends(friends) {
	return { type: SET_FRIENDS, friends };
}

export function addPost(post) {
	return { type: ADD_POST, post };
}

export function updatePost(post) {
	return { type: UPDATE_POST, post };
}

export function getPosts(user) {
	return { type: GET_POSTS, user };
}

export function setPosts(posts) {
	return { type: SET_POSTS, posts };
}

export function addComment(comment) {
	return { type: ADD_COMMENT, comment };
}

export function getComments(postId) {
	return { type: GET_COMMENTS, postId };
}

export function setComments(postId, comments) {
	return { type: SET_COMMENTS, params: { comments, postId } };
}

export function setCurrentUser(user) {
	return { type: SET_CURRENT_USER, user };
}
