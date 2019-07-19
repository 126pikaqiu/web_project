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
                    showMessage("注册成功，即将跳转。",1);
                    console.log($(".login-body").attr("id"));
                    setTimeout(function () {
                        window.location.href = $(".login-body").attr("id");
                    },500);
                }).fail(function () {
                showMessage("注册失败，用户已存在或出现网络故障。",2);
            })
        }
    });
}
function checkData() {
    let legal = true;
    let name = $("#inputUsername").val();
    let pwd = $("#inputPassword").val();
    if(name == '') {
        $("#name_info").css('visibility','visible');
        legal = false;
    }
    if(pwd == '') {
        $("#pwd_info").css('visibility','visible');
        legal = false;
    }
    setTimeout(function () {
        $(".info").css('visibility','hidden');
    },1500)
    return legal;
}


function initResource() {

}