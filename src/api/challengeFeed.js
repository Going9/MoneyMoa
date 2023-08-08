import { apiInstance } from './index'
import { useCookies } from 'vue3-cookies'

const api = apiInstance()

// 토큰 받아오기
const { cookies } = useCookies()
const token = cookies.get('accessToken')

// 피드 전체 목록 조회 API
async function fetchAllFeedList() {
  try {
    const headers = {
      Authorization: `Bearer ${token}`
    }
    const res = await api.get(`/feed/all`, { headers })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 피드 상세 조회 API
async function fetchFeedDetail(feedId) {
  try {
    const headers = {
      Authorization: `Bearer ${token}`
    }
    const res = await api.get(`/feed/${feedId}`, { headers })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 피드 좋아요 API
async function addFeedLike(feedId) {
  try {
    const headers = {
      Authorization: `Bearer ${token}`
    }
    const res = await api.put(`/feed/like`, { headers })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 피드 검색 API
async function searchFeed(searchWord) {
  try {
    const res = await api.get(`/feed/search`, {
      usertoken: accessToken,
      searchWord: searchWord
    })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 피드 작성 API
async function postFeed() {
  try {
    const headers = {
      Authorization: `Bearer ${token}`
    }
    const res = await api.post(`/feed/create`, { headers })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 피드 수정 API
async function updateFeed() {
  try {
    const res = await api.put(`/feed/modifyboard`, {
      usertoken: accessToken
    })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 피드 삭제 API
async function deleteFeed() {
  try {
    const res = await api.delete(`/feed/deleteboard`, {
      usertoken: accessToken
    })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 댓글 작성 API
async function postComment() {
  try {
    const res = await api.get(`/feed/list`, {
      usertoken: accessToken
    })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 댓글 삭제 API
async function deleteComment() {
  try {
    const res = await api.get(`/feed/list`, {
      usertoken: accessToken
    })
    return res
  } catch (err) {
    console.log(err)
  }
}

// 댓글 수정 API
async function updateComment() {
  try {
    const res = await api.get(`/feed/list`, {
      usertoken: accessToken
    })
    return res
  } catch (err) {
    console.log(err)
  }
}

export default {
  fetchAllFeedList,
  fetchFeedDetail,
  addFeedLike,
  searchFeed,
  postFeed,
  updateFeed,
  deleteFeed,
  postComment,
  deleteComment,
  updateComment
}
