mini.parse();
var form = new mini.Form("form1");
//保存
function SaveData() {
    var o = form.getData();
    form.validate();
    if (form.isValid() == false) return;
    o.dicPath = mini.getbyName("dicPath").getValue();
    var json = mini.encode(o);
    ShowCommonloading();
    $.ajax({
        url: "/sys/sysdiceditsave",
        dataType: "json",
        type: "post",
        data: {
            data: json
        },
        success: function (data) {
            if(data.code=1){
            CloseCommonloading();
            top["SysDicList"].gridReload();
            CloseDialog();}
            else{
                ShowCommonTip("系统提示", json.message, "info", function () {});
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
        url: "/sys/sysdiceditp",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var o = mini.decode(text);
            form.setData(o);

            if (o.dicPath) {
                var data=o.dicPath.split("|");
                var _path = data[0].substring(0, o.dicPath.length - 3);
                mini.getbyName("dicPath").setValue(_path);
                if(data.length==2)
                    mini.getbyName("dicPath").setText(data[1]);
            }
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
function closeseldic(e) {
    var obj = e.sender;
    obj.setText("");
    obj.setValue("");
}