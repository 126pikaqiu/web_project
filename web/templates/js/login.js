$(document).ready(function () {
    initResource();
    bindEvents();
});

function bindEvents() {
    $("#login-btn").click(function () {
        if (checkData()) {
            var username = $("#inputUsername").val();
            var pwd = $("#inputPassword").val();
            let data = {'username': username, 'pwd': pwd};
            $.when(login(data))
                .done(function () {
                    showMessage("登录成功，即将跳转。",1);
                    // setTimeout(function () {
                    //     window.location.href = "index.jsp";
                    // },1000);
                }).fail(function () {
                showMessage("登录失败，请检查用户名或密码是否正确。",2);
            })
        }
    });
}
function checkData() {
    return true;
}
function showError() {

}

function initResource() {
    
}