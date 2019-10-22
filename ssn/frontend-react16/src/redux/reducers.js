import * as actions from "./actions";
import { clone } from '../globals';

function rootReducer(state, action) {
	switch (action.type) {

		case actions.SET_FRIENDS:
			return { ...state, friends: action.friends };
			
		case actions.SET_POSTS:
			return { ...state, posts: action.posts };

		case actions.SET_COMMENTS:
			let newState = clone(state);
			let post = newState.posts.find(x => x.id == action.params.postId);
			post.comments = action.params.comments;
			return newState;

		case actions.SET_CURRENT_USER:
			return { ...state, currentUser: action.user };
			
		default:
			return state;
	}
}

export default rootReducer;
