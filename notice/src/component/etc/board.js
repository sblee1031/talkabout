import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Table from '@material-ui/core/Table';
import TableHead from '@material-ui/core/TableHead';
import TableBody from '@material-ui/core/TableBody';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableFooter from '@material-ui/core/TableFooter';
import TablePagination from '@material-ui/core/TablePagination';
import { withStyles } from '@material-ui/core/styles';
import InputBase from '@material-ui/core/InputBase';
import SearchIcon from '@material-ui/icons/Search';
import Toolbar from '@material-ui/core/Toolbar';
import Media from 'react-media';
import myFireBase from '../../config/MyFireBase'
// Board total data
var totalBoardData = {};
// Storage and Database from firebase
const storageRef = (new myFireBase).storageRef;
const databaseRef = (new myFireBase).databaseRef;
// Style
const styles = theme => ({
  boardTopBackground: {
    zIndex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    width: '100%',
    height: '12rem',
    position: 'absolute'
  },
  boardTop: {
    height: '12em',
    width: '100%',
    position: 'relative',
  },
  boardTopText: {
    zIndex: 2,
    position: 'absolute',
    bottom: '3rem',
    left: '3rem',
    fontSize: "2rem",
    fontWeight: 'bold',
    color: 'white',
  },
  boardTopImg: {
    height: '12em',
    width: '100%',
    objectFit: 'cover',
    position: 'relative'
  },
  tableBody: {
    fontFamily: 'Noto Sans KR',
    fontSize: '1rem',
    color: '#494949',
    height: '1rem',
    textAlign: 'center',
    letterSpacing: 0
  },
  tableHeader: {
    fontFamily: 'Noto Sans KR',
    fontSize: '1rem',
    fontWeight: 'bold',
    color: '#494949',
    textAlign: 'center',
    height: '1rem',
    letterSpacing: 0
  },
  search: {
    fontFamily: 'Noto Sans KR',
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    borderWidth: '1px',
    borderColor: 'red'
  },
  searchIcon: {
    width: theme.spacing(7),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 7),
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: 120,
      '&:focus': {
        width: 200,
      },
    },
  }
})
class Board extends Component {
  // Init
  constructor(props) {
    super(props);
    this.state = {
      imgSrcBoardTop:'',
      boardData: {},
      page: 0,
      rowsPerPage: 10,
      emptyRows: 0,
      searchKeyword: '',
    }
  }
  componentDidMount() {
    this.getBoardData();
    this.getImage();
  }
  // Get Image
  getImage () {
    storageRef.child('boardtop.jpg').getDownloadURL().then((url) => {
      this.setState({imgSrcBoardTop:url});
    })
  }
  // Get Data
  getBoardData() {
    databaseRef.child('board/').once('value').then(data=>{
      totalBoardData = data.val();
      this.setState({ boardData: totalBoardData.slice(this.state.page * this.state.rowsPerPage, this.state.page * this.state.rowsPerPage + this.state.rowsPerPage)});
    })
  }
  // Change searchKeyword with value
  handleChangeValue = (e) => {
    this.setState({ searchKeyword: e.target.value });
    let slicedData = totalBoardData.slice(this.state.page * this.state.rowsPerPage, this.state.page * this.state.rowsPerPage + this.state.rowsPerPage);
    let filteredData = slicedData.filter((b) => {
      return b.title.indexOf(e.target.value) > -1;
    });
    this.setState({ boardData: filteredData });
    this.setState({ emptyRows: this.state.rowsPerPage - filteredData.length });
  }
  // Change page with new page and refresh searchKeyword
  handleChangePage = (event, newPage) => {
    this.setState({ page: newPage });
    this.setState({ searchKeyword: '' });
    let slicedData  = totalBoardData.slice(newPage* this.state.rowsPerPage, newPage * this.state.rowsPerPage + this.state.rowsPerPage);
    this.setState({ boardData: slicedData})
    this.setState({ emptyRows: this.state.rowsPerPage - slicedData.length });
  }
  // Change rowPerPage with value and refresh page and searchKeyword
  handleChangeRowsPerPage = (event) => {
    this.setState({ page: 0 });
    this.setState({ rowsPerPage: parseInt(event.target.value, 10) });
    this.setState({ searchKeyword: '' });
    let slicedData = totalBoardData.slice(0, parseInt(event.target.value, 10));
    this.setState({ boardData: slicedData});
    this.setState({ emptyRows: parseInt(event.target.value, 10) - slicedData.length });
  }
  // Increse Hit
  increaseHit= (id,hit) => {
    databaseRef.child('board/'+(id-1)).update({
      hit: hit+1
    })
  }
  render() {
    // Set classes
    const { classes } = this.props;
    // Return
      <div>
        <div className={classes.boardTop}>
          <div className={classes.boardTopBackground}></div>
          <img className={classes.boardTopImg} src={this.state.imgSrcBoardTop} />
          <div className={classes.boardTopText}>
            Notices of Sallab
          </div>
        </div>
        <div>
          {/* Search tool bar */}
          <Toolbar>
            <div style={{ 'width': '100%' }} />
            <div className={classes.search}>
              <div className={classes.searchIcon}><SearchIcon /></div>
              <InputBase placeholder="Search" classes={{ root: classes.inputRoot, input: classes.inputInput, }} inputProps={{ 'aria-label': 'search' }}
                name="searchKeyword" value={this.state.searchKeyword} onChange={this.handleChangeValue} style={{ "fontFamily": "Noto Sans KR" }} />
            </div>
          </Toolbar>
          <Media query="(max-width: 960px)">
            {matches =>
              matches ? (
                // When width is less then 960px 
                <Table className={classes.table}>
                  <TableHead>
                    <TableRow>
                      <TableCell className={classes.tableHeader} style={{ width: "5%" }}>#</TableCell>
                      <TableCell className={classes.tableHeader} style={{ width: "70%" }}>Title</TableCell>
                      <TableCell className={classes.tableHeader} style={{ width: "25%" }}>Created by</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {Object.keys(this.state.boardData).map(idx => {
                      const b = this.state.boardData[idx];
                      return (<TableRow key={b.id} component={Link} to={{
                        pathname: "/main/board/detail",
                        state: {
                          id: b.id,
                          title: b.title,
                          content: b.content,
                          createdby: b.createdby,
                          createdDate: b.createdDate,
                          hit: b.hit,
                        }
                      }} onClick={this.increaseHit.bind(this,b.id,b.hit)}>
                        <TableCell className={classes.tableBody} >{b.id}</TableCell>
                        <TableCell className={classes.tableBody} >{b.title}</TableCell>
                        <TableCell className={classes.tableBody} >{b.createdby}</TableCell>
                      </TableRow>)
                    })}
                    {this.state.emptyRows > 0 && (
                      <TableRow style={{ height: 46.34 * this.state.emptyRows }}>
                        <TableCell colSpan={3} />
                      </TableRow>
                    )}
                  </TableBody>
                </Table>
              ) :
                (
                  // When width is bigger then 960px 
                  <Table className={classes.table}>
                    <TableHead>
                      <TableRow>
                        <TableCell className={classes.tableHeader} style={{ width: "5%" }}>#</TableCell>
                        <TableCell className={classes.tableHeader} style={{ width: "70%" }}>Title</TableCell>
                        <TableCell className={classes.tableHeader} style={{ width: "10%" }}>Created by</TableCell>
                        <TableCell className={classes.tableHeader} style={{ width: "10%" }}>Created Date</TableCell>
                        <TableCell className={classes.tableHeader} style={{ width: "5%" }}>Hit</TableCell>
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {
                        Object.keys((this.state.boardData)).map(idx => {
                        const b = this.state.boardData[idx];
                        return (<TableRow key={b.id} component={Link} to={{
                          pathname: "/main/board/detail",
                          state: {
                            id: b.id,
                            title: b.title,
                            content: b.content,
                            createdby: b.createdby,
                            createdDate: b.createdDate,
                            hit: b.hit,
                          }
                        }} onClick={this.increaseHit.bind(this,b.id,b.hit)}>
                          <TableCell className={classes.tableBody} >{b.id}</TableCell>
                          <TableCell className={classes.tableBody} >{b.title}</TableCell>
                          <TableCell className={classes.tableBody} >{b.createdby}</TableCell>
                          <TableCell className={classes.tableBody} >{b.createdDate}</TableCell>
                          <TableCell className={classes.tableBody} >{b.hit}</TableCell>
                        </TableRow>)
                      })}
                      {this.state.emptyRows > 0 && (
                        <TableRow style={{ height: 50.91 * this.state.emptyRows }}>
                          <TableCell colSpan={5} />
                        </TableRow>
                      )}
                    </TableBody>
                  </Table>
                )
            }
          </Media>
          {/* Pagination */}
          <TableFooter>
            <TableRow >
              <TablePagination
                rowsPerPageOptions={[10, 25]}
                colSpan={5}
                count={totalBoardData.length}
                rowsPerPage={this.state.rowsPerPage}
                page={this.state.page}
                SelectProps={{
                  inputProps: { 'aria-label': 'rows per page' },
                  native: true,
                }}
                onChangePage={this.handleChangePage}
                onChangeRowsPerPage={this.handleChangeRowsPerPage}
              />
            </TableRow>
          </TableFooter>
        </div>
      </div>
    );
  }
}

export default withStyles(styles)(Board);