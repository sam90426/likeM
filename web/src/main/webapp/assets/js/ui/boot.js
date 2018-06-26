__CreateJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path;
}

var bootPATH = __CreateJSPath("boot.js");

//debugger
mini_debugger = true;

//miniui
document.write('<script src="' + bootPATH + 'jquery.min.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'miniui/miniui.js" type="text/javascript" ></sc' + 'ript>');
document.write('<link href="' + bootPATH + 'miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'miniui/themes/icons.css" rel="stylesheet" type="text/css" />');


//mode
var mode = getCookie("miniuiMode");
if (mode) {
    document.write('<link href="' + bootPATH + 'miniui/themes/default/' + mode + '-mode.css" rel="stylesheet" type="text/css" />');
}


//skin
var skin = getCookie("miniuiSkin");
if (skin) {
    document.write('<link href="' + bootPATH + 'miniui/themes/' + skin + '/skin.css" rel="stylesheet" type="text/css" />');
}



////////////////////////////////////////////////////////////////////////////////////////
function getCookie(sName) {
    var aCookie = document.cookie.split("; ");
    var lastMatch = null;
    for (var i = 0; i < aCookie.length; i++) {
        var aCrumb = aCookie[i].split("=");
        if (sName == aCrumb[0]) {
            lastMatch = aCrumb;
        }
    }
    if (lastMatch) {
        var v = lastMatch[1];
        if (v === undefined) return v;
        return unescape(v);
    }
    return null;
}

////////////////////////////////////////////自定义////////////////////////////////////////////////////
/**
 加载
 **/
function ShowCommonloading() {
    mini.mask({
        el: document.body,
        cls: 'mini-mask-loading',
        html: '加载中...'
    });
}
/**
 关闭加载
 **/
function CloseCommonloading() {
    mini.unmask(document.body);
}

/**
 模式窗口
 url：弹出页面地址
 title：标题
 width：宽度
 height：高度
 onload：加载时执行
 ondestroy：关闭时执行
 **/
function ShowCommonDialog(url, title, width, height, onload, ondestroy) {
    mini.open({
        url: url,
        title: title, width: width, height: height,
        onload: onload,
        ondestroy: ondestroy
    });
}

/**
 关闭窗口
 action：返回值
 **/
function CloseDialog(action) {
    if (window.CloseOwnerWindow)
        return window.CloseOwnerWindow(action);
    else
        window.close();
}

/**
 普通对话框
 title：标题
 message：提示内容
 icon：显示图标 info=提示  warning=警告  question=问号  error=错误  download=下载
 toDoSomeThing：点击确定执行
 **/
function ShowCommonAlert(title, message, icon, toDoSomeThing) {
    if (icon == "info")
        icon = "mini-messagebox-info";
    else if (icon == "warning")
        icon = "mini-messagebox-warning";
    else if (icon == "question")
        icon = "mini-messagebox-question";
    else if (icon == "error")
        icon = "mini-messagebox-error";
    else if (icon == "download")
        icon = "download";
    else
        icon = "mini-messagebox-info"; //默认提示

    mini.showMessageBox({
        showHeader: true,
        width: 250,
        title: title,
        buttons: ["ok"],
        message: message,
        iconCls: icon,
        callback: function (action) {
            if (action == "ok") {
                toDoSomeThing();
            }
        }
    });
}

//默认2秒关闭
var CommonTipTimes = 2;
//5秒关闭
var CommonAlertTimes = 5;

/**
 倒计时对话框
 title：标题
 message：提示内容
 icon：显示图标 info=提示  warning=警告  question=问号  error=错误  download=下载
 toDoSomeThing：点击确定执行
 times：默认2秒自动关闭
 **/
function ShowCommonTip(title, message, icon, toDoSomeThing, times) {
    if (icon == "info")
        icon = "mini-messagebox-info";
    else if (icon == "warning")
        icon = "mini-messagebox-warning";
    else if (icon == "question")
        icon = "mini-messagebox-question";
    else if (icon == "error")
        icon = "mini-messagebox-error";
    else if (icon == "download")
        icon = "download";
    else
        icon = "mini-messagebox-info"; //默认提示

    var _ok = 0; //用来判断是否点击确定按钮

    var messageid = mini.showMessageBox({
        showHeader: true,
        width: 250,
        title: title,
        buttons: ["ok"],
        message: message,
        iconCls: icon,
        callback: function (action) {
            if (action == "ok") {
                _ok = 1;
                toDoSomeThing();
            }
        }
    });

    //不传入时间，则默认2秒关闭
    if (times == null) {
        times = CommonTipTimes;
    }

    //定时关闭
    setTimeout(function () {
        mini.hideMessageBox(messageid);
        if (_ok == 0)
            toDoSomeThing();
    }, times * 1000);
}

/**
 确定/取消对话框
 title：标题
 message：提示内容
 icon：显示图标 info=提示  warning=警告  question=问号  error=错误  download=下载
 toDoSomeThing：点击确定执行
 toCancelThing：点击取消执行
 **/
function ShowCommonConfirm(title, message, icon, toDoSomeThing, toCancelThing) {
    if (icon == "info")
        icon = "mini-messagebox-info";
    else if (icon == "warning")
        icon = "mini-messagebox-warning";
    else if (icon == "question")
        icon = "mini-messagebox-question";
    else if (icon == "error")
        icon = "mini-messagebox-error";
    else if (icon == "download")
        icon = "download";
    else
        icon = "mini-messagebox-info"; //默认提示

    mini.showMessageBox({
        showHeader: true,
        width: 250,
        title: title,
        buttons: ["ok", "cancel"],
        message: message,
        iconCls: icon,
        callback: function (action) {
            if (action == "ok") {
                toDoSomeThing();
            } else {
                toCancelThing();
            }
        }
    });
}

var WinAlerts = window.alert;
window.alert = function (e) {
    if (e != null && e.indexOf("试用到期") > -1) {
        //和谐了
    } else {
        WinAlerts(e);
    }
};