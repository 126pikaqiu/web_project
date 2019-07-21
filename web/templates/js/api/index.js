var api = {
    'logout':'users/logout',
    'login': 'users/login',
    'add_collection':'collections/add',
    'delete_collection':'collections/delete',
    'register': 'users/register',
    'user_info': 'users/info',
    'all_friends': 'friends/all',
    'all_friends_request': 'friends/all',
    'all_friends_receive': 'friends/all'
};

function axios(option){
    var dtd = $.Deferred();
    $.ajax({
        ...option,
        success: function (res) {
            dtd.resolve(res);
        },
        error: function (error) {
            dtd.reject(error);
        }
    });
    return dtd.promise()
}
