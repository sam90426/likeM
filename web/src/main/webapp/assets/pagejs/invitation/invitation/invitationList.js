mini.parse();
var grid = mini.get("datagrid1");
grid.load({
    withdrawalsState: mini.get("withdrawalsState").getValue()
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
        withdrawalsState: mini.get("withdrawalsState").getValue(),
        userName: mini.get("userName").getValue()
    });
}

function resetsearch() {
    mini.get("withdrawalsState").setValue();
    mini.get("userName").setValue();
    grid.load();
}

function onStateRender(e) {
    if (e.value == 0) {
        return '<span style="color: #3c86c5">未申请</span>';
    } else if (e.value == 1) {
        return '<span style="color: #FFB800;">提现中</span>';
    } else if (e.value == 2) {
        return '<span style="color: green;">提现成功</span>';
    } else if (e.value == 3) {
        return '<span style="color: red">提现失败</span>';
    } else {
        return "";
    }
}

// function look() {
//     var row = grid.getSelected();
//     if (row) {
//         ShowCommonDialog("/info/suggestedit?id=" + row.id, "反馈 - 查看", 820, 300);
//     } else {
//         ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
//         });
//     }
// }

// function remove() {
//     var rows = grid.getSelecteds();
//     if (rows.length > 0) {
//         ShowCommonConfirm("系统提示", "确定删除选中记录？", "question", function () {
//             var ids = [];
//             for (var i = 0, l = rows.length; i < l; i++) {
//                 var r = rows[i];
//                 ids.push(r.id);
//             }
//             var id = ids.join(',');
//             ShowCommonloading()
//             $.ajax({
//                 url: "/info/suggestdel",
//                 dataType: "json",
//                 type: "post",
//                 data: {
//                     id: id
//                 },
//                 success: function (data) {
//                     if (data.code == 1) {
//                         CloseCommonloading();
//                         grid.reload();
//                     }
//                     else
//                         ShowCommonTip("系统提示", json.message, "info", function () {
//                             ShowCommonloading();
//                         });
//                 },
//                 error: function () {
//                     CloseCommonloading()
//                 }
//             });
//         }, function () {
//         });
//     } else {
//         ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
//         });
//     }
// }

top["SuggestList"] = window;

function gridReload() {
    grid.reload();
}