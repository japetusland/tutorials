import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UsersService } from "../../services/users.service";
import { PostsService } from "../../services/posts.service";
import { message } from "../../messages";

@Component({
	selector: "friends",
	templateUrl: "./friends.component.html",
	styles: []
})
export class FriendsComponent implements OnInit {
	@Input() friends: string[];

	friendsMessage: string = message("friends");

	constructor(private router: Router, private usersService:UsersService, private postService:PostsService) {}

	ngOnInit() {
		this.usersService.getFriends().subscribe(friends => this.friends = friends);
	}

	loadPosts = (friend:string) => {
		this.usersService.setCurrentUser(friend);
		this.postService.getPosts(friend);
		this.router.navigateByUrl("/dashboard");
	}
}
