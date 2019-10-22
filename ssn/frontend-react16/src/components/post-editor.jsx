import React from "react";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Box from "@material-ui/core/Box";
import grey from '@material-ui/core/colors/grey';
import { message } from "../messages";

class PostEditor extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			content: props.content ? props.content : "",
			buttonLabel: ( props.content ? message("save") : message("add") ) + " " + this.props.title
		};
	}

	handleContentChange = event => {
		this.setState({ content: event.target.value });
	};

	save = () => {
		this.props.save(this.state.content);
		this.setState({ content: "" });
	};

	render() {
		return (
			<Box style={{ backgroundColor: grey[100], padding:10 }}>
				<TextField
					name="content"
					fullWidth
					value={this.state.content}
					onChange={this.handleContentChange}
					label={this.props.placeholder}
					multiline="true"
					rows="1"
					rowsMax="8"
				/>
				<Button variant="contained" color="primary" onClick={this.save} style={{ margin:10 }}>
					{this.state.buttonLabel}
				</Button>
			</Box>
		);
	}
}

export default PostEditor;
