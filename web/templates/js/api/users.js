function axios(option) {
    var dtd = $.Deferred();
    $.ajax({
        ...option,
        success: function () {
            dtd.resolve();
        },
        error: function () {
            dtd.reject();
        }
    });
    return dtd
}

function logout(current_url) {
    return axios({
        url: api.logout,
        type: 'post',
        data: {'current_url': current_url}
    })
}

function login(data) {
    return axios({
        url: api.login,
        type: 'post',
        data: data
    })
}