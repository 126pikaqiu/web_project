$(document).ready(function () {
    initResource();
    bindEvents();
});
let blankInfo = ["用户名","邮箱","密码","确认密码","验证码"];
function bindEvents() {
    $("#register-btn").click(function () {
        if (checkData()) {
            let username = $("#inputUsername").val();
            let pwd = $("#inputPassword").val();
            let email = $("#inputEmail").val();
            let data = {'username': username, 'pwd': pwd,'email':email};
            $.when(register(data))
                .done(function () {
                    showMessage("注册成功，即将跳转。",1);
                    setTimeout(function () {
                        window.location.href = goBack($(".register-body").attr("id"));
                    },500);
                }).fail(function () {
                showMessage("注册失败，用户已存在或出现网络故障。",2);
            })
        }
    });
}
function sendCaptcha() {
    let codeChars = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    let code = '';
    for (let i = 0; i < 6; i++)
    {
        //获取随机验证码下标
        let charNum = Math.floor(Math.random() * 62);
        //组合成指定字符验证码
        code += codeChars[charNum];
    }
    localStorage.setItem("captcha",code);
    showMessage("验证码发送成功，验证码为" + code,1,10000)
}
function checkData() {
    let legal = checkUsername()&&checkEmail()&&checkPwd()&&checkPwdAgain()&&checkCaptcha();
    setTimeout(function () {
        $(".info").css('visibility','hidden');
    },1500);
    return legal;
}

function checkUsername() {
    let name = $("#inputUsername").val();
    $("#name_info").css('visibility','visible');
    if(name == '') {
        $("#name_info").html(blankInfo[0] + '不能为空');
        return false;
    } else if(name.length < 4 || name.length > 15) {
        $("#name_info").html('用户名不能长于15位或短于4位');
        return false;
    }
    $("#name_info").css('visibility','hidden');
    return true;
}
function checkEmail() {
    let email = $("#inputEmail").val();
    let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
    $("#email_info").css('visibility','visible');
    if(email == '') {
        $("#email_info").html(blankInfo[1] + '不能为空');
        return false;
    } else if(!reg.test(email)) {
        $("#email_info").html('邮箱地址格式错误');
        return false;
    }
    $("#email_info").css('visibility','hidden');
    return true;
}
function checkPwd() {
    let pwd = $("#inputPassword").val();
    $("#pwd_info").css('visibility','visible');
    if(pwd == '') {
        $("#pwd_info").html(blankInfo[2] + '不能为空');
        return false;
    } else if(pwd.length < 6 || pwd.length > 10) {
        $("#pwd_info").html('密码不能长于10位或短于6位');
        return false;
    } else if(!validatePwd(pwd)) {
        $("#pwd_info").html('密码必须包含大小写字母和数字');
        return false;
    }
    $("#pwd_info").css('visibility','hidden');
    return true;
}
function checkPwdAgain() {
    let pwd = $("#inputPasswordAgain").val();
    $("#pwd_again_info").css('visibility','visible');
    if(pwd == '') {
        $("#pwd_again_info").html(blankInfo[3] + '不能为空');
        return false;
    } else if(pwd !== $("#inputPassword").val()) {
        $("#pwd_again_info").html('两次密码输入不一致');
        return false;
    }
    $("#pwd_again_info").css('visibility','hidden');
    return true;
}
function checkCaptcha() {
    let captcha = $("#inputCaptcha").val();
    $("#captcha_info").css('visibility','visible');
    if(captcha == '') {
        $("#captcha_info").html(blankInfo[4] + '不能为空');
        return false;
    } else if(captcha !== localStorage.getItem("captcha")) {
        $("#captcha_info").html('验证码输入错误');
        return false;
    }
    $("#captcha_info").css('visibility','hidden');
    return true;
}
function validatePwd(pwd) {
    let Letter = false;
    let number = false;
    let letter = false;
    for(let s = 0; s < pwd.length && !(letter&&Letter&&number); s++) {
        if(pwd[s] >= '0' && pwd[s] <= '9') {
            number = true;
        } else if(pwd[s] >= 'a' && pwd[s] <= 'z') {
            letter = true;
        } else if(pwd[s] >= 'A' && pwd[s] <= 'Z') {
            Letter = true;
        }
    }
    return letter&&Letter&&number;
}


function initResource() {

}