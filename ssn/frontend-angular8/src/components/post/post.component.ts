import { Component, OnInit, Input } from "@angular/core";
import { Post, Comment } from "../../data-types";
import { CommentsService } from "../../services/comments.service";
import { PostsService } from "../../services/posts.service";
import { LOGGED_USER } from "../../globals";
import { formatTimestamp } from "../../utils/data-formatter";
import { message } from "../../messages";


@Component({
	selector: "post",
	templateUrl: "./post.component.html",
	styles: []
})
export class PostComponent implements OnInit {
	@Input() post: Post;
	editMode: boolean = false;
	editable: boolean = false;

	postMessage: string = message("post");
	placeholderMessage: string = message("writeNewPost");

	timestamp: string;

	constructor(private commentsService: CommentsService, private postsService: PostsService) {}

	ngOnInit() {
		this.editable = this.post.user == LOGGED_USER;
		this.timestamp = formatTimestamp(this.post.timestamp);
	}

	saveComment = (content: string) => {
		var comment = new Comment();
		comment.user = LOGGED_USER
		comment.postId = this.post.id;
		comment.content = content;
		this.commentsService.addComment(comment);
	}

	saveEditedPost = (content: string) => {
		this.post.content = content;
		this.postsService.updatePost(this.post);
		this.exitEditMode();
	}

	activateEditMode = () => {
		this.editMode = true;
	}

	exitEditMode = () => {
		this.editMode = false;
	}

	reloadComments = () => {
		this.commentsService.getComments(this.post.id);
	}


}
