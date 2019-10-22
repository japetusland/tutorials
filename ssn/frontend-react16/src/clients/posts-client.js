import Axios from "axios";
import { CONTEXT_ROOT } from "../globals";

function addPost(post) {
	return Axios.post(CONTEXT_ROOT + "/api/posts/", post, {
		params: { lang: navigator.language }
	})
		.then(res => res.data)
		.catch(error => {
			console.log(error);
			return false;
		});
}

function updatePost(post) {
	return Axios.put(CONTEXT_ROOT + "/api/posts/", post, {
		params: { lang: navigator.language }
	})
		.then(res => res.data)
		.catch(error => {
			console.log(error);
			return false;
		});
}

function getPosts(user) {
	return Axios.get(CONTEXT_ROOT + "/api/posts/" + user + "/", {
		params: { lang: navigator.language }
	})
		.then(res => res.data)
		.catch(error => {
			console.log(error);
			return false;
		});
}

export { addPost, updatePost, getPosts };
