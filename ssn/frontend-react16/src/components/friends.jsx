import React from "react";
import { connect } from "react-redux";
import Card from "@material-ui/core/Card";
import CardHeader from '@material-ui/core/CardHeader';
import List from "@material-ui/core/List";
import Divider from "@material-ui/core/Divider";
import ListItem from "@material-ui/core/ListItem";
import { getPosts, setCurrentUser } from "../redux/actions";
import { Redirect } from "react-router-dom";
import { ListItemText, CardContent } from "@material-ui/core";
import blue from '@material-ui/core/colors/blue'
import { message } from "../messages";

class Friends extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			redirect: false
		};
	}

	loadPosts = friend => {
		this.props.setCurrentUser(friend);
		this.props.getPosts(friend);
		this.setState({ redirect: true });
	}

	render() {
		if (this.state.redirect) {
			this.setState({ redirect: false });
			return <Redirect to={"/dashboard" } />;
		}
		
		let friendsListElement;
		if (this.props.friends) {
			friendsListElement = this.props.friends.map(friend => (
				<ListItem
					key={friend}
					button
					onClick={() => this.loadPosts(friend)}
				>
					<ListItemText primary={friend} />
				</ListItem>
			));
		}

		return (
			<Card style={{ backgroundColor: blue[800] }}>
				<CardHeader title={message("friends")} style={{ color: "white"}} />
				<Divider/>
				<CardContent style={{ color: "yellow" }}>
					<List component="nav">
						{friendsListElement}
					</List>
				</CardContent>
			</Card>
		);
	}
}

function mapStateToProps(state) {
	return {
		friends: state.friends
	};
}

let mapDispatchToProps = { getPosts, setCurrentUser };

export default connect(mapStateToProps,mapDispatchToProps)(Friends);
