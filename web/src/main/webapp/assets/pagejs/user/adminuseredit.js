mini.parse();
var form = new mini.Form("form1");
var id = GetQueryString("id");

//两次密码验证
function onPwsValidation(e) {
    if (e.isValid) {
        if (e.value != mini.get("passWord").getValue()) {
            e.errorText = "两次密码不一致";
            e.isValid = false;
        }
    }
}
//密码验证
function onPwValidation(e) {
    if (e.isValid) {
        if (e.value.length > 16 || e.value.length < 6) {
            e.errorText = "请输入6-16位数字、字母";
            e.isValid = false;
        }
    }
}

//保存
function SaveData() {
    form.validate();
    if (form.isValid() == false)
        return;
    var o = form.getData();
    if(o.id=='')
        o.id='0';
    var json = mini.encode(o);
    ShowCommonloading();
    $.ajax({
        url: "/user/adminusereditsave",
        dataType: "json",
        type: "post",
        data: {
            data: json
        },
        success: function (data) {
            if(data.code==1) {
                CloseCommonloading();
                CloseDialog();
                top["AdminUserList"].gridReload();
            }else
            {
                ShowCommonAlert("系统提示", resp.message, "error", function () { });
            }
        },
        failure: function (resp, opts) {
            CloseCommonloading();
            ShowCommonAlert("系统提示", resp.responseText, "error", function () { });
        }
    });
}

//加载
if (id) {
    ShowCommonloading();
    $.ajax({
        url: "/user/adminusereditpl",
        dataType: "json",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var model = mini.decode(text);
            form.setData(model);
            if(model.passWord){
                mini.get("PassWord2").setValue(model.passWord);
            }
            CloseCommonloading();
        },
        error: function () {
            CloseCommonloading();
        }
    });
}
function onOk() {
    SaveData();
}