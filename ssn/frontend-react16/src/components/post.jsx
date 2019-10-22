import React from "react";
import { connect } from "react-redux";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import EditIcon from "@material-ui/icons/Edit";
import CancelIcon from "@material-ui/icons/Cancel";
import { addComment, updatePost } from "../redux/actions";
import { Comment } from "../data-type";
import { formatTimestamp } from "../utils/data-formatter";
import PostEditor from "./post-editor.jsx";
import CommentList from "./comment-list.jsx";
import { clone, LOGGED_USER } from '../globals';
import { message } from "../messages";


class Post extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			editMode: false
		};
	}

	saveComment = content => {
		var comment = new Comment();
		comment.user = LOGGED_USER;
		comment.postId = this.props.post.id;
		comment.content = content;
		this.props.addComment(comment);
	};

	savePost = content => {
		var post = clone(this.props.post);
		post.content = content;
		this.props.updatePost(post);
		this.exitEditMode();
	};

	activateEditMode = () => {
		this.setState({	editMode: true });
	};

	exitEditMode = () => {
		this.setState({	editMode: false });
	};

	render() {
		let editIcon = null;
		let content = null;
		if (this.props.post.user == LOGGED_USER ) {
			if (this.state.editMode) {
				editIcon = <CancelIcon onClick={this.exitEditMode} />;
				content = <PostEditor title={message("post")} save={this.savePost} 
								content={this.props.post.content} 
								placeholder={message("writeNewPost")}/>;
			} else {
				editIcon = <EditIcon onClick={this.activateEditMode} />;
				content = this.props.post.content;
			}
		} else {
			content = this.props.post.content;
		}
		
		return (
			<Box style={{ backgroundColor: "white", border:"1px solid black"}}>
				<Grid container spacing="2">
					<Grid item xs="12">
						<Toolbar>
							<Typography variant="subtitle2" style={{ flex:1 }}>
								{this.props.post.user}
							</Typography>
							<Typography variant="subtitle2">
								{formatTimestamp(this.props.post.timestamp)}
							</Typography>
							<Box style={{ marginLeft:10 }}>
								{ editIcon }
							</Box>
						</Toolbar>
						<Typography style={{padding:10, whiteSpace: "pre-wrap"}}>
							{content}
						</Typography>
					</Grid>
					<Grid item xs="12" style={{ padding:20, paddingLeft:100 }}>
						<CommentList comments={this.props.post.comments} save={this.saveComment}/>
					</Grid>
				</Grid>
			</Box>
		);
	}
}

let mapDispatchToProps = { addComment, updatePost };

export default connect(null,mapDispatchToProps)(Post);
