mini.parse();
var grid = mini.get("datagrid1");
grid.load();

//固定列表高度
$(function () {
    if ($("#datagrid1")) {
        var h = window.innerHeight-48;
        $("#datagrid1").css("height", h + "px");
    }
});

function search() {
    var searchtype = mini.getbyName("SearchType").getValue(); //类型
    var key = mini.get("key").getValue(); //关键词
    grid.load({
        searchtype: searchtype,
        key: key
    });
}

function onState(e) {
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
        ShowCommonDialog("/user/adminuseredit?id=" + row.id, "分销商 - 编辑", 820, 300);
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

function add() {
    ShowCommonDialog("/user/adminuseredit", "分销商 - 新增", 820, 300);
}

//停用
function lock() {
    islock(2);
}

//启用
function unlock() {
    islock(1);
}

function islock(userlock) {
    var message = '';
    switch (userlock) {
        case 2:
            message = "确定停用选中用户？";
            break;
        case 1:
            message = "确定启用选中用户？";
            break;
    }
    var rows = grid.getSelecteds();
    if (rows.length > 0) {
        ShowCommonConfirm("系统提示", message, "question", function () {
            var ids = [];
            for (var i = 0, l = rows.length; i < l; i++) {
                var r = rows[i];
                ids.push(r.id);
            }
            var id = ids.join(',');
            ShowCommonloading();
            $.ajax({
                url: "/user/upstate",
                type: "post",
                data: {
                    id: id,
                    state: userlock, //1-锁定，2-解锁
                },
                success: function (data) {
                    var json = eval("(" + data + ")")
                    if (json.code==1) {
                        CloseCommonloading();
                        gridReload();
                    }
                    else
                        ShowCommonTip("系统提示", json.message, "info", function () {
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

top["AdminUserList"] = window;

function gridReload() {
    grid.reload();
}