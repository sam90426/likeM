mini.parse();
var form = new mini.Form("form1");
function SaveData() {
    var o = form.getData();
    form.validate();
    if (form.isValid() == false) return;

    if (!o.content) {
        ShowCommonTip("系统提示", "内容不能为空", "info", function () { });
        return false;
    } else if (o.content.length > 72) {
        ShowCommonTip("系统提示", "内容太长", "info", function () { });
        return false;
    }
    var json = mini.encode(o);
    ShowCommonloading();
    $.ajax({
        url: "/info/getpush",
        dataType:"json",
        type: "post",
        data: {
            content: mini.get("content").getValue(),
            pushObj: mini.get("pushObj").getValue(),
            pushObjPar: mini.get("pushObjPar").getValue(),
            pushObjParValue: mini.get("pushObjParValue").getValue(),
            addValue1: mini.get("addValue1").getValue()
        },
        success: function (data) {

            if (data.code==1) {
                ShowCommonTip("系统提示", data.message, "info", function () {});
            }
            else {
                ShowCommonTip("系统提示", data.message, "error", function () { });
            }
            CloseCommonloading();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            ShowCommonTip("系统提示", jqXHR.responseText, "error", function () { CloseCommonloading(); });
        }
    });
}

$("#pushObjParValue").hide();
function onPushObjParChange(e) {
    var parm = $("#pushObjParValue");
    $("#getAgent").hide();
    if (e.value == "1") {
        parm.hide();
    } else {
        parm.show();
    }
}
function onOk() {
    SaveData();
}
function onCancel(e) {
    CloseDialog();
}