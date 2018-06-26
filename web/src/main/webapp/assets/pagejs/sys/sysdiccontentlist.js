var path = GetQueryString("path");
mini.parse();
var grid = mini.get("datagrid1");
grid.load({ path: path });

//固定列表高度
$(function () {
    if ($("#datagrid1")) {
        var h = window.innerHeight-48;
        $("#datagrid1").css("height", h + "px");
    }
});
//添加
function add() {
    ShowCommonDialog("/sys/sysdiccontentedit?path=" + path, "广告位 - 新增", 680, 520);
}
//编辑
function edit() {
    var row = grid.getSelected();
    if (row) {
        ShowCommonDialog("/sys/sysdiccontentedit?id=" + row.id, "广告位 - 编辑", 680, 520);
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () { });
    }
}
//删除
function remove() {
    var rows = grid.getSelecteds();
    if (rows.length > 0) {
        ShowCommonConfirm("系统提示", "确定删除选中记录？", "question", function () {
            var ids = [];
            for (var i = 0, l = rows.length; i < l; i++) {
                var r = rows[i];
                ids.push(r.id);
            }
            var id = ids.join(',');
            ShowCommonloading()
            $.ajax({
                url: "/sys/sysdiccontentdel",
                dataType: "json",
                type: "post",
                data: {
                    id: id
                },
                success: function (data) {
                    if (data.code==1) {
                        CloseCommonloading();
                        grid.reload();
                    }
                    else
                        ShowCommonTip("系统提示", json.message, "info", function () { ShowCommonloading(); });
                },
                error: function () {
                    CloseCommonloading()
                }
            });
        }, function () { });
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () { });
    }
}
top["SysDicContentList"] = window;
function gridReload() {
    grid.reload();
}