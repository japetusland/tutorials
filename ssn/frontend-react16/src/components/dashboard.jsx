import React from "react";
import { connect } from "react-redux";
import Container from "@material-ui/core/Container";
import Grid from "@material-ui/core/Grid";
import Paper from "@material-ui/core/Paper";
import Toolbar from "@material-ui/core/Toolbar";
import grey from '@material-ui/core/colors/grey';
import Typography from '@material-ui/core/Typography';
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";
import Layout from './layout.jsx';
import Friends from './friends.jsx';
import PostList from "./post-list.jsx";
import { message } from "../messages";

class Dashboard extends React.Component {

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
										<Toolbar>
											<Typography variant="h4" style={{ flex:1 }}>
												{this.props.currentUser}
											</Typography>
											<Button variant="contained" color="primary" component={Link} to="/">
												{message("myDashboard")}
											</Button>
										</Toolbar>
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
		posts: state.posts,
		currentUser: state.currentUser
	};
}

export default connect(mapStateToProps)(Dashboard);
