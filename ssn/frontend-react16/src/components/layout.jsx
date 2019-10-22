import React from "react";
import { connect } from "react-redux";
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { LOGGED_USER } from "../globals";

class Layout extends React.Component {

    render() {
        return (
            <React.Fragment>
                <AppBar position="fixed">
                    <Toolbar>
                        <Typography variant="h5" style={{ flex:1 }}>
                            Simple Social Network
                        </Typography>
                        <Typography variant="subtitle1">
                            { LOGGED_USER }
                        </Typography>
                    </Toolbar>
                </AppBar>
            </React.Fragment>
        );
    }
}

function mapStateToProps(state) {
    return {
        loggedUser: state.loggedUser,
        currentUser: state.currentUser,
        title: state.title,
		friends: state.friends
    };
}

let mapDispatchToProps = {};

export default connect(mapStateToProps,mapDispatchToProps)(Layout);
