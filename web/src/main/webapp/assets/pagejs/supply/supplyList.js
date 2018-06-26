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
    var supplyType = mini.get("supplyType").getValue();
    var supplyName = mini.get("supplyName").getValue();
    grid.load({
        supplyType: supplyType,
        supplyName: supplyName
    });
}

function onSupplyTypeRender(e) {
    if (e.value == 1)
        return "极速贷";
    else if (e.value == 2)
        return "工薪贷";
    else if (e.value == 3)
        return "白领贷";
    else
        return "";
}

function onLinkUrlRender(e) {
    if(e.value)
        return "<a href='"+e.value+"' target='_blank'>"+e.value+"</a>";
    else
        return "";
}

function onRateUnitRender(e) {
    if (e.value == 1)
        return "日";
    else if (e.value == 2)
        return "月";
    else if (e.value == 3)
        return "年";
    else
        return "";
}

function onStateRender(e) {
    if (e.value == 1)
        return "<span style=\" color:Green;\">正常</span>";
    else if (e.value == 2)
        return "<span style=\" color:Red;\">停用</span>";
    else if (e.value == 3)
        return "资讯";
    else if (e.value == 4)
        return "商品";
    else
        return "";
}

function onRecommend(e) {
    if (e.value == 1)
        return "<span style=\" color:Green;\">是</span>";
    else
        return "否";
}

function edit() {
    var row = grid.getSelected();
    if (row) {
        ShowCommonDialog("/supply/supplyEdit?id=" + row.id, "供应商 - 编辑", 820, 650);
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

function add() {
    ShowCommonDialog("/supply/supplyEdit", "供应商 - 新增", 820, 650);
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
                url: "/supply/supplyDel",
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

top["SupplyList"] = window;

function gridReload() {
    grid.reload();
}