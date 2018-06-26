mini.parse();
var grid = mini.get("datagrid1");
grid.load({
    State:mini.get("State").getValue()
});

//固定列表高度
$(function () {
    if ($("#datagrid1")) {
        var h = window.innerHeight-48;
        $("#datagrid1").css("height", h + "px");
    }
});

function search() {
    grid.load({
        State:mini.get("State").getValue()
    });
}

function onStateRender(e) {
    if (e.value == 1)
        return "已读";
    else if (e.value == 2)
        return "<span style=\" color:Red;\">未读</span>";
    else
        return "";
}

function look() {
    var row = grid.getSelected();
    if (row) {
        ShowCommonDialog("/info/suggestedit?id=" + row.id, "反馈 - 查看", 820, 300);
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

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
                url: "/info/suggestdel",
                dataType:"json",
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

top["SuggestList"] = window;

function gridReload() {
    grid.reload();
}