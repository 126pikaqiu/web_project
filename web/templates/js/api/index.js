var api = {
    'logout':'users/logout',
    'login': 'users/login',
    'add_collection':'collections/add',
    'delete_collection':'collections/delete',
};

function axios(option){
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
    return dtd.promise()
}
