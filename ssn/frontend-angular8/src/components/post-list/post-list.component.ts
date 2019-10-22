import { Component, Input } from "@angular/core";
import { Post } from "../../data-types";

@Component({
	selector: "post-list",
	templateUrl: "./post-list.component.html",
	styles: []
})
export class PostListComponent {
	@Input() posts:Post[];
}
