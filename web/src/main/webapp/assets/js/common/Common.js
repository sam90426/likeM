String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2); //window.location.host=www.baidu.com
}
//获取URL当中的参数
function GetQueryString(sProp) {
    var re = new RegExp("[&,?]" + sProp + "=([^\\&]*)", "i");
    var a = re.exec(document.location.search);
    if (a == null)
        return "";
    return a[1];
}

//过滤特殊（卐）字符
function FilterStr(str) {
    //过滤卐字符
    var newStr1 = "";
    var strAry1 = str.split("卐");
    for (var i = 0; i < strAry1.length; i++) {
        newStr1 += strAry1[i];
    }

    //过滤▓字符
    var newStr2 = "";
    var strAry2 = newStr1.split("▓");
    for (var i = 0; i < strAry2.length; i++) {
        newStr2 += strAry2[i];
    }

    //过滤Ж字符
    var newStr3 = "";
    var strAry3 = newStr2.split("Ж");
    for (var i = 0; i < strAry3.length; i++) {
        newStr3 += strAry3[i];
    }

    return newStr3;
}

//日期格式转换 datetime=日期 formatStr=时间格式，支持yyyy-MM-dd、yyyy-MM-dd HH:mm、yyyy-MM-dd HH:mm:ss格式 addDay=日期基础上相加
function DateToStr(datetime, formatStr, addDay) {
    if (addDay) {
        datetime.setDate(datetime.getDate() + addDay);
    }

    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1; //js从0开始取 
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minutes = datetime.getMinutes();
    var second = datetime.getSeconds();

    if (month < 10) {
        month = "0" + month;
    }
    if (date < 10) {
        date = "0" + date;
    }
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (second < 10) {
        second = "0" + second;
    }

    //转换成小写
    formatStr = formatStr.toLowerCase();

    var time = year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + second; //2009-06-12 17:18:05
    if (formatStr == "yyyy-mm-dd")
        time = year + "-" + month + "-" + date; //2009-06-12
    else if (formatStr == "yyyy-mm-dd hh:mm")
        time = year + "-" + month + "-" + date + " " + hour + ":" + minutes; //2009-06-12 17:18
    else if (formatStr == "yyyy-mm-dd hh:mm:ss")
        time = year + "-" + month + "-" + date + " " + hour + ":" + minutes + ":" + second; //2009-06-12 17:18:05
    return time;
}

/* 权限控制 禁用按钮 */
$(document).ready(function () {
    var menuId = GetQueryString("menuId");
    var _url = window.location.href;
    if (menuId) {
        $.ajax({
            url: _url,
            type: "post",
            data: {
                __menuId: menuId,
                __isSubmit: 1
            },
            success: function (text) {
                var json = eval("(" + text + ")");
                if (json.success == true) {
                    $("a[class^='mini-button']").hide(); //禁用所有mini按钮
                    $("a[class^='mini-button'][title!='']").show(); //禁用所有mini按钮
                    var per = eval("(" + json.message + ")");
                    for (var i = 0; i < per.length; i++) {
                        for (var ii = 0; ii < per[i].Functions.length; ii++) {
                            $("a[class$='" + per[i].Functions[ii].ControlFunction + "']").show(); //显示按钮
                        }
                    }
                }
            }
        });
    }
});

var expore = function (opt) {
    var self = this;
    this.option = {
        action: "",    /*请求地址*/
        params: {},    /*请求参数*/
        page: 0,      /*当前页 0：全部页*/
        rows: 0,       /*每页多少条*/
        titles: []     /*抬头*/
    }
    this.option = $.extend(true, {}, self.option, opt);
    this.download = function () {
        var downloadHelper = $('<iframe style="display:none;" id="downloadHelper"></iframe>').appendTo('body')[0];
        var doc = downloadHelper.contentWindow.document;
        if (doc) {
            doc.open();
            doc.write('')
            doc.writeln("<html><body><form id='downloadForm' name='downloadForm' method='post' action='" + self.option.action + "'>");
            doc.writeln("<input type='hidden' name='pageIndex' value='" + self.option.page + "'>");
            doc.writeln("<input type='hidden' name='pageSize' value='" + self.option.rows + "'>");
            doc.writeln("<input type='hidden' name='titles' value='" + JSON.stringify(self.option.titles) + "'>");
            for (var key in self.option.params)
                doc.writeln("<input type='hidden' name='" + key + "' value='" + self.option.params[key] + "'>");
            doc.writeln('<\/form><\/body><\/html>');
            doc.close();
            var form = doc.forms[0];
            if (form) form.submit();
        }
    }
    return self;
}