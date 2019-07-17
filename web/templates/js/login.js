$(document).ready(function () {
    initResource();
    bindEvents();
});

function bindEvents() {
    $("#login-btn").click(function () {
        if (checkData()) {
            var username = $("inputUsername").val();
            var pwd = $("inputPassword").val();
                $.ajax({
                url:'users/login',
                method:'post',
                data: {'username':username,'pwd':pwd},
                success: function () {
                    setTimeout(function () {
                        window.location.href = "index.jsp";
                    },1000);
                },
                error: function () {
                    showError();
                }
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