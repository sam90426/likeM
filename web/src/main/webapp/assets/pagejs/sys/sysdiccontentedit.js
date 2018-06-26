mini.parse();
var Path = GetQueryString("path");
var form = new mini.Form("form1");
if(Path){
    mini.get("dicPath").setValue(Path);
}
//保存
function SaveData() {
    var o = form.getData();
    form.validate();
    if (form.isValid() == false) return;
    var json = mini.encode(o);
    ShowCommonloading();
    $.ajax({
        url: "/sys/sysdiccontenteditsave",
        type: "post",
        data: {
            data: json
        },
        success: function (data) {
            var json = eval("(" + data + ")");
            if(json.code==1) {
                CloseCommonloading();
                CloseDialog();
                top["SysDicContentList"].gridReload();
            }else
            {
                ShowCommonTip("系统提示", json.message, "error", function () { CloseDialog(); });
            }
        },
        error: function () {
            CloseCommonloading();
        }
    });
}
var id = GetQueryString("id");
//加载
if (id) {
    ShowCommonloading();
    $.ajax({
        url: "/sys/sysdiccontenteditp",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var o = mini.decode(text);
            form.setData(o);

            form.setChanged(false);
            CloseCommonloading();
        },
        error: function () {
            CloseCommonloading();
        }
    });
}
function onOk(e) {
    SaveData();
}
function onCancel(e) {
    CloseDialog();
}
