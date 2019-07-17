var api = {
    'logout':'users/logout',
    'login': 'users/login'
};

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
    return dtd.promise()
}
