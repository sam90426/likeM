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
    var data={};
    if(searchtype=="1"){
        data.name=key;
    }else{
        data.code=key;
    }

    grid.load(data);
}

function onState(e) {
    if (e.value == 10)
        return "<span style=\" color:Green;\">正常</span>";
    else if (e.value == 20)
        return "<span style=\" color:Red;\">停用</span>";
    else
        return "";
}

function edit() {
    var row = grid.getSelected();
    if (row) {
        ShowCommonDialog("/channel/channelEdit?id=" + row.id, "渠道商 - 编辑", 820, 300);
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

function add() {
    ShowCommonDialog("/channel/channelEdit", "渠道商 - 新增", 820, 300);
}

//停用
function lock() {
    islock(20);
}

//启用
function unlock() {
    islock(10);
}

function islock(userlock) {
    var message = '';
    var url="/channel/updateStateByIds";
    switch (userlock) {
        case 30://删除
            url="/channel/deleteByIds";
            message = "确定删除选中渠道商？";
            break;
        case 20:
            message = "确定停用选中渠道商？";
            break;
        case 10:
            message = "确定启用选中渠道商？";
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
                url: url,
                type: "post",
                dataType: "json",
                data: {
                    ids: id,
                    state: userlock //10-启用，20-停用
                },
                success: function (result) {
                    CloseCommonloading();
                    if (result.code==1) {
                        gridReload();
                    }else{
                        ShowCommonAlert("系统提示", result.message, "info", function () {
                            if(result.code==4){//部分删除成功需刷新
                                gridReload();
                            }
                        });
                    }
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

top["channelList"] = window;

function gridReload() {
    grid.reload();
}
function onSearchType() {
    var searchId = mini.getbyName("SearchType").getValue();
    if (searchId == "1") {
        mini.getbyName("key").set({ emptyText: "请输入渠道商名称" });
    } else if (searchId == "2") {
        mini.getbyName("key").set({ emptyText: "请输入渠道商代码" });
    }
}