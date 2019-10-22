import React from "react";
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import { formatTimestamp } from "../utils/data-formatter";

class Comment extends React.Component {
	render() {
		return (
			<Typography variant="body2">
				<Box style={{ fontStyle: "italic", marginLeft:10 }}>
					{this.props.comment.user}, {formatTimestamp(this.props.comment.timestamp)}
				</Box>
				<Box style={{ fontWeight:"bold", marginLeft:10, whiteSpace: "pre-wrap" }}>
					{this.props.comment.content}
				</Box>
			</Typography>
		);
	}
}

export default Comment;
