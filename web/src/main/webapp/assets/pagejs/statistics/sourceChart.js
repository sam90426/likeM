mini.parse();//解析html元素

var startTime = mini.get("startTime");//开始时间
var endTime = mini.get("endTime");//结束时间
var myChart; //图表

//固定列表高度
function setHeight() {
    var $main = $("#main");
    if ($main) {
        var h = document.body.scrollHeight * 0.55;
        $main.css("height", h + "px");
        $main.css("min-height", "420px");
    }
}

//查询数据
function search() {
    var startTimeStr = startTime.getFormValue();
    var endTimeStr = endTime.getFormValue();
    if (moment(endTimeStr).isBefore(startTimeStr)) {
        ShowCommonTip("警告", "结束时间不能早于开始时间", "warning", function () {
        });
    } else {
        $.ajax({
            url: "/statistics/sourceChartData",
            dataType: "json",
            type: "post",
            data: {
                startTime: startTimeStr,
                endTime: endTimeStr
            },
            success: function (data) {
                myChart.source(data);
                myChart.scale('time', {
                    type: 'timeCat' // 声明 type 字段为分类类型
                });
                myChart.intervalStack().position('time*count').color('sourceName');
                myChart.render();
            },
            error: function () {
                ShowCommonTip("错误", "系统错误", "error", function () {
                });
            }
        });
    }
}

//初始化
function init() {
    setHeight();
    startTime.setValue(new Date());
    endTime.setValue(new Date());
    myChart = new G2.Chart({
        container: 'main', // 指定图表容器 ID
        forceFit: true // 指定图表宽度
    });
    search();
}

init();