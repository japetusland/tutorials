import React from "react";
import Grid from "@material-ui/core/Grid";
import Comment from "./comment.jsx";
import PostEditor from "./post-editor.jsx";
import grey from '@material-ui/core/colors/grey';
import { message } from "../messages";

class CommentList extends React.Component {
	render() {
		return (
			<Grid container spacing="2" style={{ backgroundColor: grey[200] }}>
				{this.props.comments.map(comment => 
					<Grid item xs="12">
						<Comment comment={comment} />
					</Grid>
				)}
				<Grid item xs="12">
					<PostEditor title={message("comment")}
										save={this.props.save}
										placeholder={message("writeNewComment")}
									/>
				</Grid>
			</Grid>
		);
	}
}

export default CommentList;
