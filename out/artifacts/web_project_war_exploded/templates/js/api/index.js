var api = {
    'logout':'/users/logout',
    'login': '/users/login',
    'add_collection':'/collections/add',
    'delete_collection':'/collections/delete',
    'register': '/users/register',
    'user_info': '/users/info',
    'add_hot': '/items/addhot',
    'search':'/search',
    'update_collection':'/collections/update'
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
