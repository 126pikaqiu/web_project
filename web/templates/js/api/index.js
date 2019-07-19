var api = {
    'logout':'users/logout',
    'login': 'users/login',
    'add_collection':'collections/add',
    'delete_collection':'collections/delete',
    'search':'search',
};

function axios(option){
    var dtd = $.Deferred();
    $.ajax({
        ...option,
        success: function (msg) {
            console.log(msg);
            dtd.resolve(msg);
        },
        error: function () {
            dtd.reject();
        }
    });
    return dtd.promise()
}
