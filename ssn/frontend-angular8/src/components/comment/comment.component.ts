import { Component, Input, OnInit } from "@angular/core";
import { Comment } from "../../data-types";
import { formatTimestamp } from "../../utils/data-formatter";

@Component({
	selector: "comment",
	templateUrl: "./comment.component.html",
	styles: []
})
export class CommentComponent implements OnInit {
	@Input() comment: Comment;

	timestamp:string;

	ngOnInit() {
		this.timestamp = formatTimestamp(this.comment.timestamp);
	}

}
