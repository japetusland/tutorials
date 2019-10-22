import { Component, Input } from "@angular/core";
import { Comment } from "../../data-types";
import { message } from "../../messages";

@Component({
	selector: "comment-list",
	templateUrl: "./comment-list.component.html",
	styles: []
})
export class CommentListComponent {
	@Input() comments: Comment[];
	@Input() save: Function;

	placeholderMessage: string = message("writeNewComment");
	commentMessage: string = message("comment");
}
