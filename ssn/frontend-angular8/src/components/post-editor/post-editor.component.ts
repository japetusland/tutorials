import { Component, Input, OnInit } from "@angular/core";
import { message } from 'src/messages';

@Component({
	selector: "post-editor",
	templateUrl: "./post-editor.component.html",
	styles: []
})
export class PostEditorComponent implements OnInit {
	@Input() saveContent: Function;
	@Input() placeholder: string;
	@Input() content: string;
	@Input() title: string;
	buttonLabel:string;

	ngOnInit() {
		this.buttonLabel = this.content ? message("save") : message("add") + " " + this.title;
	}

	save() {
		this.saveContent(this.content);
		this.content = "";
	}
}
