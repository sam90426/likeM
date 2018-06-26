mini.parse();
var Path = GetQueryString("path");
var form = new mini.Form("form1");
if (Path) {
    mini.get("dicPath").setValue(Path);
}

//保存
function SaveData() {
    var o = form.getData();
    form.validate();
    if (form.isValid() == false) return;
    var json = mini.encode(o);
    ShowCommonloading();
    $.ajax({
        url: "/info/ggdiccontenteditsave",
        dataType: "json",
        type: "post",
        data: {
            data: json
        },
        success: function (data) {
            if (data.code == 1) {
                CloseCommonloading();
                CloseDialog();
                top["GGDicContentList"].gridReload();
            } else {
                ShowCommonTip("系统提示", json.message, "error", function () {
                    CloseDialog();
                });
            }
        },
        error: function () {
            CloseCommonloading();
        }
    });
}

var id = GetQueryString("id");
//加载
if (id) {
    ShowCommonloading();
    $.ajax({
        url: "/info/ggdiccontenteditp",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var o = mini.decode(text);
            form.setData(o);

            //图片显示
            var _picurl = o.picUrl;
            if (_picurl) {
                $("#imgPicUrl").attr("src", _picurl);
                mini.getbyName("picUrl").setValue(_picurl);
            }
            form.setChanged(false);
            CloseCommonloading();
        },
        error: function () {
            CloseCommonloading();
        }
    });
}

function onOk(e) {
    SaveData();
}

function onCancel(e) {
    CloseDialog();
}

/*
//上传文件//dir=上传目录//files=文件json格式
function FileManage() {
    var _files = mini.get("picUrl").getValue();
    var picstr = "";
    if (_files != "") {
        var newfiles = _files.split(',');
        for (var i = 0; i < newfiles.length; i++) {
            picstr += ",{fileName:'" + newfiles[i] + "',fileSize:'0',fileUrl:'" + newfiles[i] + "'}"
        }
        picstr = picstr.substring(1);
    }
    ShowCommonDialog('/common/uploadpage?dir=/Info/GGDic&files=' + escape(picstr), '文件管理器', 810, 500, function () { }, function () {
        //获取上传的文件
        var _files = top["FileControls"].getFiles();
        $("#divFiles").html("");
        var picurl = "";
        if (_files != "") {
            var filesList = eval("[" + _files + "]");

            $.each(filesList, function (index, obj) {
                if (obj.fileUrl) {
                    picurl += "," + obj.fileUrl;
                }
                $("#divFiles").append('<div style=" margin-top:5px;display:inline-block"><a target="_blank" href="' + obj.fileUrl + '"><img target="_blank" style="height:80px;" src="' + obj.fileUrl + '"/></a>&nbsp;<br><a href="javascript:DelPic(\'' + index + '\')">删除</a></div>');
            });
        }
        mini.get("picUrl").setValue(picurl == "" ? "" : picurl.substring(1));
        $("#imgPicUrl").attr("src", picurl == "" ? "" : picurl.substring(1));
    });
}*/

setTimeout(function () {
    $("#btnSingleUpFile").uploadify({
        //开启调试
        'debug': false,
        //是否自动上传
        'auto': true,
        //浏览按钮的背景图片路径
        //'buttonImage': '1.jpg',
        'buttonClass': "mini-button",
        'buttonText': '图片上传',
        //flash
        'swf': "/assets/js/uploadify/uploadify.swf",
        //文件选择后的容器ID
        'queueID': 'uploadfileQueue',
        //上传处理程序
        'uploader': '/common/imgupload',
        "method": "post",
        'width': '95',
        'height': '22',
        'multi': false,
        'fileTypeDesc': '支持的格式：',
        'fileTypeExts': '*.jpg;*.jpeg;*.png;*.bmp',
        'fileSizeLimit': '100KB',
        'removeTimeout': 1,
        //附带值
        'formData': {
            'saveDir': "/Info/GGDic",
            'maxSize': "100",
            'isCompression': "0",
            'maxWidth': "0",
            'maxHeight': "0",
            'isMinPic': "0",
            'minWidth': "0",
            'minHeight': "0",
            'method': 'post'
        },
        //不执行默认的onSelect事件
        'overrideEvents': ['onDialogClose'],
        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
        'fileObjName': 'uploadFile',
        //选择上传文件后调用
        'onSelect': function (file) {
        },
        //返回一个错误，选择文件的时候触发
        'onSelectError': function (file, errorCode, errorMsg) {
            switch (errorCode) {
                case -100:
                    alert("上传的文件数量已经超出系统限制的1个文件！");
                    break;
                case -110:
                    alert("文件 [" + file.name + "] 大小超出系统限制的100KB大小！");
                    break;
                case -120:
                    alert("文件 [" + file.name + "] 大小异常！");
                    break;
                case -130:
                    alert("文件 [" + file.name + "] 类型不正确！");
                    break;
            }
        },
        //检测FLASH失败调用
        'onFallback': function () {
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        //上传到服务器，服务器返回相应信息到data里
        'onUploadSuccess': function (file, data, response) {
            var obj = eval('(' + data + ')');
            if (obj.code == "0") {
                ShowCommonTip("提示", obj.message, "error", function () {
                });
            } else {
                $("#imgPicUrl").attr("src", obj.data); //图片显示
                mini.get("picUrl").setValue(obj.data); //隐藏域赋值
            }
        }
    })
}, 10);

