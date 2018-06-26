mini.parse();
var form = new mini.Form("form1");
var id = GetQueryString("id");

//两次密码验证
function onPhone(e) {
    if (e.isValid) {
        if (!/^1\d{10}$/.test(e.value)) {
            e.errorText = "请输入正确的手机号";
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
    var url ="/channel/add";
    if(o.id!=null&&o.id!=""&&o.id!="0"){
        url ="/channel/updateById";
    }
    ShowCommonloading();
    $.ajax({
        url: url,
        dataType: "json",
        type: "post",
        data: o,
        success: function (data) {
            CloseCommonloading();
            if(data.code==1) {
                CloseDialog();
                top["channelList"].gridReload();
            }else
            {
                ShowCommonAlert("系统提示", data.message, "error", function () { });
            }
        },
        error:function(resp){
            CloseCommonloading();
            ShowCommonAlert("系统提示","系统出错了", "error", function () { });
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
        url: "/channel/selectById",
        dataType: "json",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var result = mini.decode(text);
            if(result.code==1){
                form.setData(result.data);
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