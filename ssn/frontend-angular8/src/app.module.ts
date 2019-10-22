import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { NgModule } from '@angular/core';

// material imports needs to be before BrowserAnimationsModule (accordingly to the documentation)
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatButtonModule } from "@angular/material/button";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatListModule } from "@angular/material/list";
import { MatInputModule } from "@angular/material/input";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";
import { MatToolbarModule } from "@angular/material/toolbar";

import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { HttpClientModule } from "@angular/common/http";

import { UsersService } from "./services/users.service";
import { PostsService } from "./services/posts.service";
import { CommentsService } from "./services/comments.service";

import { AppComponent } from "./components/app.component";
import { UserDashboardComponent } from "./components/user-dashboard/user-dashboard.component";
import { PostComponent } from "./components/post/post.component";
import { PostListComponent } from "./components/post-list/post-list.component";
import { FriendsComponent } from "./components/friends/friends.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { CommentComponent } from "./components/comment/comment.component";
import { CommentListComponent } from "./components/comment-list/comment-list.component";
import { PostEditorComponent } from "./components/post-editor/post-editor.component";
import { LayoutComponent } from './components/layout/layout.component';

@NgModule({
  declarations: [
    AppComponent,
    UserDashboardComponent,
    PostComponent,
    PostListComponent,
    FriendsComponent,
    DashboardComponent,
    CommentComponent,
    CommentListComponent,
    PostEditorComponent,
    LayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatButtonModule,
    MatListModule,
    MatInputModule,
    MatCardModule,
    MatIconModule,
    MatGridListModule,
    MatToolbarModule
  ],
  providers: [
    PostsService,
    CommentsService,
    UsersService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
