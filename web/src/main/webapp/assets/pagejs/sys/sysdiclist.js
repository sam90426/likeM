mini.parse();
var tree = mini.get("tree1");
tree.on("nodeselect", function (e) {
    if (e.node.dicPath) {
        var url = "/sys/sysdiccontentlist?path=" + e.node.dicPath;
        $("#mainForm").attr("src", url);
    }
});

function search() {
    tree.load({key: mini.getbyName("key").getValue()});
}

function add() {
    ShowCommonDialog("/sys/sysdicedit", "广告位字典信息 - 新增", 470, 300);
}

function edit() {
    var row = tree.getSelected();
    if (row) {
        if (row.id > 0)
            ShowCommonDialog("/sys/sysdicedit?id=" + row.id, "广告位字典信息 - 编辑", 470, 300);
        else
            ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
            });
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

//删除
function remove() {
    var row = tree.getSelected();
    if (row.id > 0) {
        ShowCommonConfirm("系统提示", "确定删除选中记录？", "question", function () {
            var id = row.id;
            ShowCommonloading()
            $.ajax({
                url: "/sys/sysdicdel",
                dataType: "json",
                type: "post",
                data: {
                    id: id
                },
                success: function (data) {
                    if (data.code == 1) {
                        CloseCommonloading();
                        tree.reload();
                    }
                    else
                        ShowCommonTip("系统提示", resp.message, "error", function () {
                            ShowCommonloading();
                        });
                },
                error: function () {
                    CloseCommonloading()
                }
            });
        }, function () {
        });
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

function onKeyEnter(e) {
    search();
}

top["SysDicList"] = window;

function gridReload() {
    tree.reload();
}