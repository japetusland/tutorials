import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { PostsService } from "./posts.service";
import { Comment } from "../data-types";
import { CONTEXT_ROOT } from "../globals";

let httpOptions = {
	headers: new HttpHeaders({ "Content-Type": "application/json" }),
	params: new HttpParams().set("lang", navigator.language)
};

@Injectable({
	providedIn: "root"
})
export class CommentsService {

	constructor(private httpClient: HttpClient, private postService: PostsService) {}

	public addComment = (comment: Comment) => {
		this.httpClient.post<boolean>(CONTEXT_ROOT + "/api/comments/", comment, httpOptions)
			.subscribe(
				() => this.getComments(comment.postId), 
				error => console.log(error)
			);
	}

	public getComments = (postId: number) => {
		this.httpClient.get<Comment[]>(CONTEXT_ROOT + "/api/comments/" + postId + "/", httpOptions)
			.subscribe(
				comments=> this.postService.setComments(postId, comments),
				error => console.log(error)
			);
	}

}
