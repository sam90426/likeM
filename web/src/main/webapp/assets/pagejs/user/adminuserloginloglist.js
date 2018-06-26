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
        return "<span style=\" color:Green;\">登录成功</span>";
    else if (e.value == 2)
        return "<span style=\" color:Red;\">密码错误</span>";
    else if (e.value == 3)
        return "<span style=\" color:Red;\">账号不存在</span>";
    else
        return "";
}