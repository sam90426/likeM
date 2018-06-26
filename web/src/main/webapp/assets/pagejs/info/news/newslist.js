mini.parse();
var grid = mini.get("datagrid1");
grid.load({
    Type: mini.get("Type").getValue(),
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
    var Type = mini.get("Type").getValue();
    var State=mini.get("State").getValue();
    grid.load({
        Type: Type,
        State:State
    });
}

function onType(e) {
    if (e.value == 1)
        return "热点";
    else if (e.value == 2)
        return "理财";
    else if (e.value == 3)
        return "贷款";
    else
        return "";
}

function onStateRender(e) {
    if (e.value == 1)
        return "<span style=\" color:Green;\">正常</span>";
    else if (e.value == 2)
        return "<span style=\" color:Red;\">停用</span>";
    else
        return "";
}

function edit() {
    var row = grid.getSelected();
    if (row) {
        ShowCommonDialog("/info/newsedit?id=" + row.id, "资讯 - 编辑", 820, 650);
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

function add() {
    ShowCommonDialog("/info/newsedit", "资讯 - 新增", 820, 650);
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
                url: "/info/newsdel",
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

top["NewsList"] = window;

function gridReload() {
    grid.reload();
}