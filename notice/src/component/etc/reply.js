import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
const styles = () => ({
    root: {
      backgroundColor: "#eeeeee",
      height: '6em',
      marginTop: '1rem',
      borderBottom: '0.05rem dotted #cccccc',
      width: '100%',
    },
    name: {
      height: '2rem',
      float: 'left'
    },
    date: {
      height: '2rem',
      float: 'right'
    },
    content: {
      clear: 'both'
    }
})
class Reply extends Component {
    render() {
        // Set classes
        const { classes } = this.props;
        // Return
        return (
            <div className={classes.root}>
              <div>
                <div className={classes.name}>
                  <div className="lunchBox-textField-black-content-small" style={{"height":"100%"}}>
                    {this.props.name}
                  </div>
                </div>
                <div className={classes.date}>
                  <div className="lunchBox-textField-black-content-small" style={{"height":"100%"}}>
                    {this.props.date}
                  </div>
                </div>
              </div>
              <div className="lunchBox-textField-black-content-small" style={{"height":"100%"}}>
                <div className={classes.content}>
                  {this.props.content}
                </div>
              </div>
            </div>
        );
    };
}
export default withStyles(styles)(Reply);
