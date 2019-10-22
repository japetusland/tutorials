import React from "react";
import { connect } from "react-redux";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import Paper from "@material-ui/core/Paper";
import grey from '@material-ui/core/colors/grey';
import PostList from "./post-list.jsx";
import PostEditor from "./post-editor.jsx";
import { Post } from "../data-type";
import { addPost, getPosts } from "../redux/actions";
import Layout from './layout.jsx';
import Friends from './friends.jsx';
import { LOGGED_USER } from '../globals';
import { message } from "../messages";

class UserDashboard extends React.Component {

	componentDidMount() {
		if (this.props.currentUser != LOGGED_USER)
			this.props.getPosts(LOGGED_USER);
	}

	savePost = content => {
		var post = new Post();
		post.user = LOGGED_USER;
		post.content = content;
		this.props.addPost(post);
	};

	render() {
		return (
			<React.Fragment>
				<Layout/>
				<Container fixed style={{ marginTop: 100, paddingLeft: 10 }}>
					<Grid container spacing={2}>
						<Grid item xs="12" lg="9">
							<Grid container spacing={2}>
								<Grid item xs="12">
									<Paper style={{ backgroundColor: grey[100], padding:10 }}>
										<PostEditor title={message("post")}
											save={this.savePost}
											placeholder={message("writeNewPost")}
										/>
									</Paper>
								</Grid>
								<Grid item xs="12">
									<PostList />
								</Grid>
							</Grid>
						</Grid>
						<Grid item xs="12" lg="3">
							<Friends/>
						</Grid>
					</Grid>
				</Container>
			</React.Fragment>
		);
	}

}

function mapStateToProps(state) {
	return {
		currentUser: state.currentUser
	};
}

let mapDispatchToProps = { addPost, getPosts };

export default connect(mapStateToProps,mapDispatchToProps)(UserDashboard);
