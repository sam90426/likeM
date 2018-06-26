mini.parse();
var form = new mini.Form("form1");

var id = GetQueryString("id");
//加载
if (id) {
    ShowCommonloading();
    $.ajax({
        url: "/info/suggesteditp",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var o = mini.decode(text);
            form.setData(o);
            mini.get("isRead").disable(false);
            form.setChanged(false);
            CloseCommonloading();
        },
        error: function () {
            CloseCommonloading();
        }
    });
}

function onCancel(e) {
    top["SuggestList"].gridReload();
    CloseDialog();
}
