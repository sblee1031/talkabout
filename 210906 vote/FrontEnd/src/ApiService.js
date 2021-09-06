import axios from 'axios';

const AUDIENCE_URL = "http://localhost:9999/ta_back/debbattle/audience";
const DEBATE_URL = "http://localhost:9999/ta_back/debbattle/debate";
const DETAIL_URL = "http://localhost:9999/ta_back/debbattle/debatedetail";

class ApiService {

    /*
        Audience
    */

    // http://localhost:9999/ta_back/debbattle/audience
    fetchAudiences() {
        return axios.get(AUDIENCE_URL, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/audience/one
    // params : "audi_no"
    fetchAudOneByPK(params) {
        return axios.get(AUDIENCE_URL + '/one', params, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/audience/two?deb_no=숫자&mem_no=숫자
    // params : "deb_no", "mem_no"
    fetchAudOneByTwo(params) {
        return axios.get(AUDIENCE_URL + `/two?deb_no=${params.deb_no}&mem_no=${params.mem_no}`, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/audience/vote?deb_no=숫자
    // params : "deb_no"
    fetchVoteCount(params) {
        return axios.get(AUDIENCE_URL + `/vote?deb_no=${params.deb_no}`, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/audience
    // params : "deb_no", "mem_no"
    addVote(params){
        return axios.post(AUDIENCE_URL, params, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/audience/관중번호
    // params : "audi_no", "vote_no"
    editVote(params){
        return axios.put(AUDIENCE_URL + '/' + params.audi_no, params, { withCredentials: true })
    }

    /*
        Debate
    */

    // http://localhost:9999/ta_back/debbattle/debate
    fetchDebates() {
        return axios.get(DEBATE_URL, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/debate/토론번호
    fetchDebOne(deb_no){
        return axios.get(DEBATE_URL + '/' + deb_no, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/debate/토론번호
    // params : "deb_no", "word", "setdata"
    editDebate(params){
        return axios.put(DEBATE_URL + '/' + params.deb_no, params);
    }

    /*
        DebateDetail
    */

    // http://localhost:9999/ta_back/debbattle/debatedetail/토론번호
    fetchDiscussors(deb_no) {
        return axios.get(DETAIL_URL + '/' + deb_no, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/debatedetail/one
    // params : "deb_no", "discussor"
    fetchDetailOne(params) {
        return axios.get(DETAIL_URL + '/one', params, { withCredentials: true });
    }

    // http://localhost:9999/ta_back/debbattle/debatedetail/상세번호
    // params : "detail_no", "word", "evi_no", "setdata"
    editDetail(params) {
        return axios.put(DETAIL_URL + '/' + params.detail_no, params, { withCredentials: true });
    }
}

export default new ApiService();