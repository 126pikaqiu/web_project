function logout() {
    return axios({
        url: api.logout,
        type: 'post',
    })
}

function login(data) {
    return axios({
        url: api.login,
        type: 'post',
        data: data
    })
}

function register(data) {
    return axios({
        url: api.register,
        type: 'post',
        data: data
    })
}
function getUserInfor() {
    return axios({
        url: api.user_info,
        type: 'get'
    })
}
function updateUserInfor(data) {
    return axios({
        url: api.user_info,
        type: 'post',
        data:data
    })
}
function getAllFriends() {
    return axios({
        url: api.all_friends,
        type: 'get',
        data:data
    })
}
function getAllFriendsRequest() {
    return axios({
        url: api.all_friends_request,
        type: 'get',
        data:data
    })
}
function getAllFriendsReceive() {
    return axios({
        url: api.all_friends_receive,
        type: 'get',
        data:data
    })
}