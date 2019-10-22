import { Component } from "@angular/core";
import { LOGGED_USER } from "../../globals";

@Component({
	selector: 'layout',
	templateUrl: './layout.component.html'
})
export class LayoutComponent {
	
	loggedUser:string = LOGGED_USER;
	
}
