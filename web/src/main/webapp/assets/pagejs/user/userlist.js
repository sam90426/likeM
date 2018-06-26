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
function onDrawDate(e) {
    var date = e.date;
    var d = new Date();//当前时间
    var startDate=mini.get("startTime").getValue();
    var id=e.sender.id;
    if((startDate==null||startDate=="")||id==="startTime"){
        if (date.getTime() > d.getTime()) {
            e.allowSelect = false;
        }
    }else{
        if (date.getTime() < startDate.getTime() || date.getTime() > d.getTime()) {
            e.allowSelect = false;
        }
    }
}

function onValueChanged(e){
    var startTime=this.getValue();
    var endTime=mini.get("endTime").getValue();
    if(startTime>endTime&&endTime!=null&&endTime!=''){
        mini.get("endTime").setValue(startTime);
    }
}

function userExport(){
    var searchtype = mini.getbyName("SearchType").getValue(); //类型
    var key = mini.get("key").getValue(); //关键词
    var startTime=mini.get("startTime").getFormValue();
    var endTime=mini.get("endTime").getFormValue();
    var channelId=mini.get("channelId").getFormValue();
    window.open("/user/getUserListExport?startTime=" +startTime+"&endTime="+endTime+"&key="+key+"&searchtype="+searchtype+"&channelId="+channelId);
}

function onSearchType() {
    var searchId = mini.getbyName("SearchType").getValue();
    if (searchId == "1") {
        mini.getbyName("key").set({ emptyText: "请输入登录名" });
    } else if (searchId == "2") {
        mini.getbyName("key").set({ emptyText: "请输入真实姓名" });
    }
}

function search() {
    var searchtype = mini.getbyName("SearchType").getValue(); //类型
    var key = mini.get("key").getValue(); //关键词
    var startTime=mini.get("startTime").getFormValue();
    var endTime=mini.get("endTime").getFormValue();
    var channelId=mini.get("channelId").getFormValue();
    grid.load({
        searchtype: searchtype,
        key: key,
        startTime:startTime,
        endTime:endTime,
        channelId:channelId
    });
}
document.onkeyup = function (e) {//按键信息对象以函数参数的形式传递进来了，就是那个e
    var code = e.charCode || e.keyCode;  //取出按键信息中的按键代码(大部分浏览器通过keyCode属性获取按键代码，但少部分浏览器使用的却是charCode)
    if (code == 13) {
        search();
    }
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
        ShowCommonDialog("/user/userEdit?id=" + row.id, "app用户 - 编辑", 820, 300);
    } else {
        ShowCommonTip("系统提示", "请选中一条记录", "info", function () {
        });
    }
}

function add() {
    ShowCommonDialog("/user/userEdit", "app用户 - 新增", 820, 300);
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
                url: "/user/updateUserState",
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

top["UserList"] = window;

function gridReload() {
    grid.reload();
}