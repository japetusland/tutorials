import { Component, OnInit } from "@angular/core";
import { UsersService } from "../../services/users.service";
import { PostsService } from "../../services/posts.service";
import { LOGGED_USER } from "../../globals";
import { Post } from "../../data-types";
import { BreakpointObserver, Breakpoints } from "@angular/cdk/layout";
import { message } from "../../messages";

@Component({
	selector: "user-dashboard",
	templateUrl: "./user-dashboard.component.html"
})
export class UserDashboardComponent implements OnInit {

	postMessage: string = message("post");
	placeholderMessage: string = message("writeNewPost");

	posts: Post[];
	contentWidth: string = "100%";
	friendsWidth: string = "100%";

	constructor(private breakpointObserver: BreakpointObserver, private usersService: UsersService, private postsService: PostsService) { }

	ngOnInit() {
		if (this.usersService.currentUser() != LOGGED_USER)
			this.usersService.setCurrentUser(LOGGED_USER);
		this.postsService.getPosts(LOGGED_USER).subscribe(posts => this.posts = posts);
		this.breakpointObserver.observe([
			Breakpoints.XSmall,
			Breakpoints.Small,
			Breakpoints.Medium,
			Breakpoints.Large,
			Breakpoints.XLarge
		]).subscribe(result => {
			if (result.breakpoints[Breakpoints.Large] || result.breakpoints[Breakpoints.XLarge]) {
				this.contentWidth = "74%";
				this.friendsWidth = "25%";
			} else {
				this.contentWidth ="100%";
				this.friendsWidth = "100%";
			}
		});
	}

	addPost = (content: string) => {
		var post = new Post();
		post.user = LOGGED_USER;
		post.content = content;
		this.postsService.addPost(post);
	}

}
