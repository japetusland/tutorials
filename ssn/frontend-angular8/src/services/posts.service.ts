import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { BehaviorSubject, Observable} from "rxjs";
import { clone } from "../globals";
import { Post, Comment } from "../data-types";
import { getPageData } from "../globals";
import { CONTEXT_ROOT } from "../globals";


let httpOptions = {
	headers: new HttpHeaders({ "Content-Type": "application/json" }),
	params: new HttpParams().set("lang", navigator.language)
};

@Injectable({
	providedIn: "root"
})
export class PostsService {

	private postsSubject:BehaviorSubject<Post[]> = new BehaviorSubject(getPageData("data-posts",[]));
	private postsData:Observable<Post[]> = this.postsSubject.asObservable();

	constructor(private httpClient: HttpClient) {}

	public getPosts = (user: string) => {
		this.httpClient.get<Post[]>(CONTEXT_ROOT + "/api/posts/" + user + "/", httpOptions)
			.subscribe(
				posts => this.postsSubject.next(posts), 
				error => console.log(error)
			);
		return this.postsData;
	}

	public addPost = (post: Post) => {
		this.httpClient.post<boolean>(CONTEXT_ROOT + "/api/posts/", post, httpOptions)
			.subscribe(
				() => this.getPosts(post.user), 
				error => console.log(error)
			);
	}

	public updatePost = (post: Post) => {
		this.httpClient.put<boolean>(CONTEXT_ROOT + "/api/posts/", post, httpOptions)
			.subscribe(
				() => this.getPosts(post.user), 
				error => console.log(error)
			);
	}

	public posts = () => {
		return this.postsData;
	}

	public setComments = (postId: number, comments: Comment[]) => {
		let posts = clone(this.postsSubject.value);
		let post: Post = posts.find(x=>x.id == postId);
		if (post) {
			post.comments = comments;
			this.postsSubject.next(posts);
		}
	}

}
