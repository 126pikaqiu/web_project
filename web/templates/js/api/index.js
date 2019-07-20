var api = {
    'logout':'users/logout',
    'login': 'users/login',
    'add_collection':'collections/add',
    'delete_collection':'collections/delete',
    'register': 'users/register',
    'userInfo': 'users/info'
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
