mini.parse();
var grid = mini.get("datagrid1");
grid.load();
var specificSourceData=[];

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
    var specificSource = mini.getbyName("specificSource").getValue(); //具体访问来源
    grid.load({
        searchtype: searchtype,
        key: key,
        specificSource:specificSource,
        startTime: mini.get("startTime").getFormValue(),
        endTime: mini.get("endTime").getFormValue()
    });
}
function resetsearch() {
    mini.get("SearchType").setValue("1");
    mini.get("key").setValue("");
    mini.get("startTime").setValue("");
    mini.get("endTime").setValue("");
    mini.get("specificSource").setValue("0");
    search();
}

function onSource(e) {
    if (e.value == 1)
        return "APP";
    else if (e.value == 2)
        return "H5";
    else if (e.value == 3)
        return "PC";
    else
        return "";
}
function onLoadLog(specificSource){
    specificSourceData=specificSource;
}
function onSpecificSource(e) {
    if(specificSourceData!=null&&specificSourceData.length>0){
        for(var i=0;i<specificSourceData.length;i++){
            if(specificSourceData[i].id==e.value){
                return specificSourceData[i].text;
            }
        }
    }
    return "";
}




function onDrawDate(e) {
    var date = e.date;
    var d = new Date();//当前时间
    var startDate = mini.get("startTime").getValue();
    var id = e.sender.id;
    if ((startDate == null || startDate == "") || id === "startTime") {
        if (date.getTime() > d.getTime()) {
            e.allowSelect = false;
        }
    } else {
        if (date.getTime() < startDate.getTime() || date.getTime() > d.getTime()) {
            e.allowSelect = false;
        }
    }
}

function onValueChanged(e) {
    var startTime = this.getValue();
    var endTime = mini.get("endTime").getValue();
    if (startTime > endTime && endTime != null && endTime != '') {
        mini.get("endTime").setValue(startTime);
    }
}

function userExport() {
    var searchtype = mini.getbyName("SearchType").getValue(); //类型
    var key = mini.get("key").getValue(); //关键词
    var startTime = mini.get("startTime").getFormValue();
    var endTime = mini.get("endTime").getFormValue();
    window.open("/supply/getSupplyLogExport?startTime=" + startTime + "&endTime=" + endTime + "&key=" + key + "&searchtype=" + searchtype);
}