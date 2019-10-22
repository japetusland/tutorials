import Axios from "axios";
import { CONTEXT_ROOT } from "../globals";

function getFriends() {
	return Axios.get(CONTEXT_ROOT + "/api/friends", {
		params: { lang: navigator.language }
	})
		.then(res => res.data)
		.catch(error => {
			console.log(error);
			return false;
		});
}

export { getFriends };
