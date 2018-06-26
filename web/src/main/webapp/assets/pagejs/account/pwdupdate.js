mini.parse();

//保存事件
function onOk() {
    var form = new mini.Form("#form1");
    form.validate(); //验证
    if (form.isValid() == false) return;
    var pwd = mini.getbyName("OldPwd").getValue();
    var pwd1 = mini.getbyName("NewPwd").getValue();
    var pwd2 = mini.getbyName("ConfirmPwd").getValue();
    if (pwd1 != pwd2) {
        mini.alert("确认密码不一致");
        return false;
    } else if (pwd == pwd1) {
        mini.alert("原密码与新密码一致");
        return false;
    }

    //验证通过，获取数据
    var o = form.getData();
    //把数据转换成json
    var json = mini.encode(o); //获取表单多个控件的数据后，序列化成JSON

    ShowCommonloading();
    $.ajax({
        url: "/account/savepwd",
        dataType: "json",
        type: "post",
        data: {
            OldPwd: mini.getbyName("OldPwd").getValue(),
            NewPwd: mini.getbyName("NewPwd").getValue()
        },
        success: function (data) {
            if (data.code == 1) {
                CloseCommonloading();
                ShowCommonAlert("系统提示", data.message, "info", function () {
                    CloseDialog();
                });
            } else {
                CloseCommonloading();
                ShowCommonAlert("系统提示", data.message, "error", function () {
                });
            }
        },
        failure: function (resp, opts) {
            CloseCommonloading();
            ShowCommonAlert("系统提示", resp.responseText, "error", function () {
            });
        }
    });
}

//取消
function onCancel(e) {
    CloseDialog();
}

$(document).ready(function () {
    //回车事件
    function keydown(e) {
        var currKey = 0, e = e || event;
        if (e.keyCode == 13)
            $("#btnSubmit").click();
    }

    document.onkeydown = keydown;

    /////
});