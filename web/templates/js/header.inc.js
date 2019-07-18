function Logout() {
    $.when(logout()).done(function () {
        history.go(0);
    })
}
