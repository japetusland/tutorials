import Axios from "axios";
import { CONTEXT_ROOT } from "../globals";

function addComment(comment) {
	return Axios.post(CONTEXT_ROOT + "/api/comments/", comment, {
		params: { lang: navigator.language }
	})
		.then(res => res.data)
		.catch(error => {
			console.log(error);
			return false;
		});
}

function getCommentsByPost(postId) {
	return Axios.get(CONTEXT_ROOT + "/api/comments/" + postId + "/", {
		params: { lang: navigator.language }
	})
		.then(res => res.data)
		.catch(error => {
			console.log(error);
			return false;
		});
}

export { addComment, getCommentsByPost };
