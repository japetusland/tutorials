import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { BehaviorSubject, Observable} from "rxjs";
import { LOGGED_USER } from "../globals";
import { getPageData } from "../globals";
import { CONTEXT_ROOT } from "../globals";


let httpOptions = {
	headers: new HttpHeaders({ "Content-Type": "application/json" }),
	params: new HttpParams().set("lang", navigator.language)
};

@Injectable({
	providedIn: "root"
})
export class UsersService {

	private currentUserSubject: BehaviorSubject<string> = new BehaviorSubject(LOGGED_USER);
	private currentUserData:Observable<string> = this.currentUserSubject.asObservable();

	private friendsSubject:BehaviorSubject<string[]> = new BehaviorSubject(getPageData("data-friends",[]));
	private friendsData:Observable<string[]> = this.friendsSubject.asObservable();

	constructor(private httpClient: HttpClient) {}

	public setCurrentUser = (user:string) => {
		this.currentUserSubject.next(user);
	}

	public currentUser = () => {
		return this.currentUserData;
	}

	public getFriends = () => {
		this.httpClient.get<string[]>(CONTEXT_ROOT + "/api/friends", httpOptions)
			.subscribe(
				friends => this.friendsSubject.next(friends), 
				error => console.log(error)
			);
		return this.friendsData;
	}

	public friends = () => {
		return this.friendsData;
	}

}
