import Vue from "vue";
import Vuex from "vuex";
import http from '@/util/http-common';
import axios from 'axios'

Vue.use(Vuex);
const SERVER_URL = process.env.VUE_APP_SERVER_URL;


export default new Vuex.Store({
  state: {
    boards: [],
    qnas: [],
    checkMsg:'',
    accessToken: null,
    userId: '',
    userName: '',
    searchCondition: {
      key: '',
      word: '',
    },
    myMember: [],
    memberList: [],
    apts : [],
    deals: [],
    selectDeal: [],
    map: null,
    mydeals: [],
    comments: [],
    heart: 0,
    insta: [],
    top:[],
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.userId;
    },
    getUserName(state) {
      return state.userName;
    }
  },
  mutations: {
    LOAD_BOARD(state, data) {
      state.boards = data;
    },
    LOAD_QNA(state, data) {
      state.qnas = data;
    },
    CHECK_ID1(state) {
      state.checkMsg = '중복된 ID';
    },
    CHECK_ID2(state) {
      state.checkMsg = '';
    },
    LOGIN(state, payload) {
      state.accessToken = payload['auth-token'];
      state.userId = payload['user-id'];
      state.userName = payload['user-name'];

      localStorage.setItem('token', state.accessToken);
      localStorage.setItem('userId', state.userId);
      localStorage.setItem('userName', state.userName);
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = '';
      state.userName = '';

      localStorage.removeItem('token');
      localStorage.removeItem('userId');
      localStorage.removeItem('userName');
    },
    SEARCH_BOARD(state, payload) {
      state.searchCondition.key = payload.key;
      state.searchCondition.word = payload.word;
    },
    SEARCH_QNA(state, payload) {
      state.searchCondition.key = payload.key;
      state.searchCondition.word = payload.word;
    },
    // 회원목록 로드
    LOAD_MEMBER_LIST(state, data) {
      state.memberList = data;
      console.log(state.memberList);
    },
    // 매물 로드
    LOAD_DEAL(state, data) {
      state.deals = data;
    },
    // 찜목록 저장
    LOAD_ZZIM(state, data) {
      state.mydeals = data;
    },
    // 댓글 불러오기
    LOAD_COMMENT(state, data) {
      state.comments = data
    },
    // 좋아요 카운트
    COUNT_HEART(state, data) {
      state.heart = data;
    },
    // 인스타
    GET_INSTA(state, insta) {
      state.insta = insta
    },
    // 인기매물
    TOP_LOAD(state, data) {
      state.top = data;
    },
  },
  actions: {
    loadBoard({ commit }, searchCondition) {
      http
        .get('/board/all', {
          params: searchCondition
        })
        .then(({ data }) => {
          commit('LOAD_BOARD', data);
          commit('SEARCH_BOARD', searchCondition)
        })
        .catch(() => {
          alert('에러 발생');
        });
    },
    loadQnA({ commit }, searchCondition) {
      http
        .get('/qna/all', {
          params: searchCondition
        })
        .then(({ data }) => {
          commit('LOAD_QNA', data);
          commit('SEARCH_BOARD', searchCondition)
        })
        .catch(() => {
          alert('에러 발생');
        });
    },

    searchBoard({ commit }, target) {
      http.get('/board/all', {
        params: {
          key: target.key,
          word: target.word,
        }
      })
        .then(({ data }) => {
          commit('LOAD_BOARD', data);
          commit('SEARCH_BOARD', target)
        })
        .catch(() => {
          alert('에러 발생');
      })
    },
    searchQna({ commit }, target) {
      http.get('/qna/all', {
        params: {
          key: target.key,
          word: target.word,
        }
      })
        .then(({ data }) => {
          commit('LOAD_QNA', data);
          commit('SEARCH_QNA', target)
        })
        .catch(() => {
          alert('에러 발생');
      })
    },
    registBoard({ commit }, board) {
      http.post('/board', board)
        .then(({ data }) => {
          commit('LOAD_BOARD', data);
        })
        .catch(() => {
          alert('에러 발생');
      })
    },
    registQna({ commit}, qna) {
      http.post('/qna', qna)
        .then(({ data }) => {
          commit('LOAD_QNA', data);
        })
        .catch(() => {
          alert('에러 발생');
      })
    },
    signUp(context, member) {
      http
        .post('/member', {
          memberid: member.memberid,
          membername: member.membername,
          memberpwd: member.memberpwd,
          memberemail: member.memberemail,
          membertell: member.membertell,
        })
        .then(() => {
          alert('회원가입을 축하드립니다 !');
        })
        .catch(() => {
          alert('에러 발생');
        });
    },
    check(context, id) {
      http
        .get(`/member/${id}`)
        .then(({ data }) => {
          if (data.memberid != null) {
            context.commit('CHECK_ID1');
          } else {
            context.commit('CHECK_ID2');
          }
        });
    },
    // 로그인
    login(context, user) {
      
      return axios
        .post(`${SERVER_URL}/member/login`, {
          memberid: user.userid,
          memberpwd: user.userpwd,
        })
        .then(({ data }) => {
          if (data["user-id"]) {
            context.commit('LOGIN', data);
            axios.defaults.headers.common['auth-token'] = `${data['auth-token']}`;
            this.dispatch('myMember', data["user-id"]);
          }
          else {
            this.state.userId = '';
            this.state.userName = '';
            this.state.accessToken = null;
            alert('로그인 실패');
          }
        });
      
    },
    // 로그아웃
    logout(context) {
      context.commit('LOGOUT');
      axios.defaults.headers.common['auth-token'] = undefined;
    },
    deleteBoard({ commit }, searchCondition) {
      http
        .delete('/board', {
          params: searchCondition
        })
        .then(({ data } ) => {
        commit('LOAD_BOARD', data);
      })
    },
    deleteQna({ commit }, searchCondition) {
      http
        .delete('/qna', {
          params: searchCondition
        })
        .then(({ data } ) => {
        commit('LOAD_QNA', data);
      })
    },
     // 회원탈퇴
     memberDelete(context) {
      let id = this.state.userId;
      http
        .delete(`/member/${id}`)
        .then((data) => {
          alert('성공적으로 탈퇴되었습니다.');
          context.commit('LOGOUT');
          console.log(data);
        })
        .catch(() => {
          alert('에러 발생');
        });
    },
    // 마이페이지 멤버 조회
    myMember(context, id) {
      http
        .get(`/member/${id}`)
        .then(({ data }) => {
          this.state.myMember = data;
        });
    },
    // 회원수정
    updateMember(context, data) {
      this.state.myMember = data;
        http
        .put('/member', data)
        .then(() => {
          this.state.userId = data.memberid;
          this.state.userName = data.membername;
          localStorage.setItem('userId', data.memberid);
          localStorage.setItem('userName', data.membername);
        })
        .catch(() => {
          alert('에러 발생');
        })
    },
    // 회원 목록 가져오기
    loadMemberList({ commit }) {
      http
        .get('/member/all')
        .then(({ data }) => {
          commit('LOAD_MEMBER_LIST', data);
        })
        .catch(() => {
          alert('에러 발생');
      })
    },
    // 회원검색 (이름으로)
    searchMember({ commit }, word) {
      http
      .get('/member/all')
        .then(({ data }) => {
          let searchList = [];
          searchList = data.filter((member) => {
            return member.membername.includes(word);
          });
        commit('LOAD_MEMBER_LIST', searchList);
      })
      .catch(() => {
        alert('에러 발생');
    })
    },

    loadDeal({ commit }, searchCondition) {
      http
        .get('/housedeal', {
          params : searchCondition
        })
        .then(({ data }) => {
          
          commit('LOAD_DEAL', data);
        })
        .catch(() => {
          alert('에러 발생');
        });
      },
      setDeal({ commit },data) {
        commit('LOAD_DEAL', data);
    },
  
  // 매물 조회
  selectDeal(state, no) {
    http
      .get(`/housedeal/select/${no}`)
      .then(({ data }) => {
        this.state.selectDeal = data;
      })
      .catch(() => {
        alert('에러 발생');
      });
    },
  
    // 매물 검색 (동으로)
    searchDeal({ commit }, searchCondition) {
      http
        .get(`/housedeal/search/${searchCondition.word}`)
        .then(({ data }) => {
          let searchList = [];
        searchList = data.filter((apt) => {
          return parseInt(apt.dealamount) < searchCondition.value*10
        });

        let resultList = [];
        resultList = searchList.filter((apt) => {
          return parseInt(apt.area) < (searchCondition.value2 * 3.3);
        });

          commit('LOAD_DEAL', resultList);
        })
        .catch(() => {
          alert('에러발생');
        })
    },
    // 찜목록
    load_zzim({ commit }) {
      let memberid = localStorage.getItem('userId');
      http
        .get(`/housedeal/zzim/${memberid}`)
        .then(({ data }) => {
          console.dir(data);
          commit('LOAD_ZZIM', data);
        })
        .catch(() => {
          alert('에러발생');
      })
    },
    // 찜하기
    zzim(state, apt) {
      let userid = localStorage.getItem('userId');
      http
        .post('/housedeal/zzim', {
            memberid: userid,
            no: apt.no,
        })
        .then(({data}) => {
          state.commit('LOAD_ZZIM', data);
          this.state.heart = this.state.heart + 1;
          
        })
        .catch(() => {
          alert('에러발생');
      })
    },
    // 찜취소
    zzimNo(state, apt) {
      let userid = localStorage.getItem('userId');
      http
        .delete('/housedeal/zzim',{
          params: {
            memberid: userid,
            no: apt.no,
          }
        })
        .then(({ data }) => {
          state.commit('LOAD_ZZIM', data);
          this.state.heart = this.state.heart - 1;
        })
        .catch(() => {
          alert('에러발생');
      })
    },
    LOAD_COMMENT(state, data) {
      state.comments = data
    },
    addComment({ commit }, condition) {
      console.log(condition)
      http
        .post('/housedeal/comment', condition)
        .then(({ data }) => {
          console.log(data)
          commit('LOAD_COMMENT', data);
        })
        .catch(() => {
          alert('에러발생');
      })
    },
    // 댓글 불러오기
    loadComment({ commit }, data) {
      commit('LOAD_COMMENT', data)
    },
    // 좋아요 카운트
    countHeart({ commit }, data) {
      commit('COUNT_HEART', data);
    },
    // 인스타
    getInsta({ commit }, dong) {
      // happyhouse_hooondam - dam0kim@naver.com IGQVJWWUZA3MlFqY3loNU85cUROdTJMUk5NMUs5N0FGT2NEbG91Vm9nMWdGckpTWC0yajA0cmRieEVreGNUU0tKcDZAEUTVaWlp0dUpNWWVSekUxbXFQTWlldDI3Wi0tdmxQWGdvVk9yZAURoWjJaekdYZAgZDZD

      // happyhouse-boondam - damyoung1225@gmail.com  IGQVJVYWFZAZA1k5QTFRUmFsZAHVmS0xTekFSd0xBeWNBZA19fMmd1a1oxQzBZAalhHWTY4clBlNlozYVVqRjRwZAlBnYlFNMU9JaGZArTVI4dUt0Y1R1RXE0TGt5RVc3dTJKemNWal90S01qNGZARS2xFWVRjUAZDZD

      // happyhouse_hoondams - damyoung1225@naver.com  IGQVJYakExUlFGbXNXUkJUMWh3Rk1QcnJieWNobzVVMmhJeGlVMV9tMk9HYnNndXhYdVkweEZAxRGR0cVhvRkVqR3RYX0I3NC1RUmFJTUp1Um00X0ZAHZAjBTZAGhZAd1N6SjBDMklmOTViRUpLSWluQkQ2TQZDZD
      let token = '';
      console.log(dong)
      if (dong == '사직동') {
         token = 'IGQVJWWUZA3MlFqY3loNU85cUROdTJMUk5NMUs5N0FGT2NEbG91Vm9nMWdGckpTWC0yajA0cmRieEVreGNUU0tKcDZAEUTVaWlp0dUpNWWVSekUxbXFQTWlldDI3Wi0tdmxQWGdvVk9yZAURoWjJaekdYZAgZDZD'
      } else if (dong == '한남동') {
        token = 'IGQVJVYWFZAZA1k5QTFRUmFsZAHVmS0xTekFSd0xBeWNBZA19fMmd1a1oxQzBZAalhHWTY4clBlNlozYVVqRjRwZAlBnYlFNMU9JaGZArTVI4dUt0Y1R1RXE0TGt5RVc3dTJKemNWal90S01qNGZARS2xFWVRjUAZDZD'
      } else if (dong == '불광동') {
        token = 'IGQVJYakExUlFGbXNXUkJUMWh3Rk1QcnJieWNobzVVMmhJeGlVMV9tMk9HYnNndXhYdVkweEZAxRGR0cVhvRkVqR3RYX0I3NC1RUmFJTUp1Um00X0ZAHZAjBTZAGhZAd1N6SjBDMklmOTViRUpLSWluQkQ2TQZDZD'
      }
      axios.
      get(`https://graph.instagram.com/me/media?access_token=${token}&fields=id,caption,media_type,media_url,thumbnail_url,permalink`)
      .then(({ data }) => {
        commit('GET_INSTA', data.data)
        console.log(data)
      })
        .catch((error) => {
        console.log(error)
      })
    },
    // 회원 삭제
    memberRemove({commit}, memberid) {
      http
        .delete(`/member/${memberid}`)
        .then(({ data }) => {
          commit('LOAD_MEMBER_LIST', data);
          alert('정상적으로 삭제했습니다.');
        })
        .catch(() => {
          alert('에러 발생');
        });
    },
    // 인기순위
    top_load(state) {
      http
        .get('/housedeal/top')
        .then(({ data }) => {
          state.commit('TOP_LOAD', data);
        })
        .catch(() => {
          alert('에러 발생');
      })
    }
  },
  modules: {},
});