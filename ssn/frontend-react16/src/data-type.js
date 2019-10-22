class Post {
	id = 0;
	user = "";
	content = "";
	timestamp = 0;
	comments = [];
}

class Comment {
	id = 0;
	postId = 0;
	user = "";
	content = "";
	timestamp = 0;
}

export { Post, Comment };
