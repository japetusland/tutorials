import { Component, OnInit } from "@angular/core";
import { UsersService } from "../../services/users.service";
import { PostsService } from "../../services/posts.service";
import { Router } from "@angular/router";
import { Post } from "../../data-types";
import { LOGGED_USER } from 'src/globals';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { message } from "../../messages";

@Component({
	selector: "dashboard",
	templateUrl: "./dashboard.component.html",
	styles: []
})
export class DashboardComponent implements OnInit {
	posts: Post[];
	user: string;
	contentWidth: string = "100%";
	friendsWidth: string = "100%";
	
	myDashboardMessage: string = message("myDashboard");

	constructor(private breakpointObserver: BreakpointObserver, private router: Router, private usersService:UsersService, private postService: PostsService) {}

	ngOnInit() {
		this.usersService.currentUser().subscribe(user => {
			this.user = user;
			this.postService.getPosts(user).subscribe(posts => this.posts = posts);
		});
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

	navigateHome = () => {
		this.usersService.setCurrentUser(LOGGED_USER);
		this.postService.getPosts(LOGGED_USER);
		this.router.navigateByUrl("");
	}
}
