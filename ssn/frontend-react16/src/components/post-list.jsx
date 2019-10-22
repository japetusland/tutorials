import React from "react";
import { connect } from "react-redux";
import Grid from "@material-ui/core/Grid";
import Post from "./post.jsx";

class PostList extends React.Component {
	render() {
		return (
			<Grid container spacing="2">
				{this.props.posts.map(post =>
					<Grid item xs="12">
						<Post post={post} />
					</Grid>
				)}
			</Grid>
		);
	}
}

function mapStateToProps(state) {
	return {
		posts: state.posts
	};
}

export default connect(mapStateToProps)(PostList);
