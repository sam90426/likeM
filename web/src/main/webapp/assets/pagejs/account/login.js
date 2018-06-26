$(function () {
    $('#login').validator({
        timely: false,
        showOk: false,
        fields: {
            'UserName': 'required;',
            'PassWord': 'required;'
        },
        invalid: function (form, errors) {
            $('#login .n-valid').parent().css('border-color', '#bbb');
            $('#login .n-invalid').parent().css('border-color', '#c33');
        }
    });
});

//登录事件
function FormSubmit() {
    if ($("#UserName").val() == '') {
        return;
    }

    if ($("#PassWord").val() == '') {
        return;
    }

    ShowCommonloading();
    $.ajax({
        url: "/account/loginon",
        dataType: "json",
        type: "post",
        data: {
            username: $("#UserName").val(),
            password: $("#PassWord").val()
        },
        success: function (resp, opts) {
            if (resp.code!=1) {
                CloseCommonloading();
                ShowCommonAlert("系统提示", resp.message, "error", function () { });
            } else {
                window.location = resp.message; //跳转到首页
            }
        },
        failure: function (resp, opts) {
            CloseCommonloading();
            ShowCommonAlert("系统提示", resp.responseText, "error", function () { });
        }
    });
}

$(document).ready(function () {
    //登录事件
    $("#btnSubmit").click(function () {
        FormSubmit();
    });

    //回车事件
    function keydown(e) {
        var currKey = 0, e = e || event;
        if (e.keyCode == 13)
            $("#btnSubmit").click();
    }
    document.onkeydown = keydown;
});