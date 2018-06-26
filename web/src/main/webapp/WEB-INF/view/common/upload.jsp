<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/21
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/assets/js/ui/boot.js" type="text/javascript"></script>
    <script src="/assets/js/common/Common.js" type="text/javascript"></script>
    <script src="/assets/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
    <link href="/assets/js/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
    <script language="javascript">
        //上传目录
        var saveDir = GetQueryString("dir");
        //URL传过来的文件json
        var _files = unescape(GetQueryString("files"));
        var type = GetQueryString("type");
        $(document).ready(function () {
            setTimeout(function () {
                $("#file_upload").uploadify({
                    //开启调试
                    'debug': false,
                    //是否自动上传
                    'auto': false,
                    //浏览按钮的背景图片路径
                    //'buttonImage': '1.jpg',
                    'buttonText': '选择文件',
                    //flash
                    'swf': "/assets/js/uploadify/uploadify.swf",
                    //文件选择后的容器ID
                    'queueID': 'uploadfileQueue',
                    //上传处理程序
                    'uploader': '/common/upload',
                    "method": "post",
                    'width': '100',
                    'height': '35',
                    'multi': true,
                    'fileTypeDesc': '支持的格式：',
                    'fileTypeExts': '*.jpg;*.jpeg;*.png;*.bmp',
                    'fileSizeLimit': '100KB',
                    'removeTimeout': 1,
                    //附带值
                    'formData': {
                        'saveDir': saveDir,
                        'maxSize': "100",
                        'isCompression': "0",
                        'maxWidth': "0",
                        'maxHeight': "0",
                        'isMinPic': "0",
                        'minWidth': "0",
                        'minHeight': "0",
                        'type': type,
                        'method':'post'
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
                    'onUploadSuccess': function ( file, data, response) {
                        var obj = eval('(' + data + ')');
                        if (obj.code == "0") {
                            ShowCommonTip("提示", obj.message, "error", function () { });
                        } else {
                            AddFile(obj);
                        }
                    }
                })
            }, 10);
            LoadFiles();
        });

        function DoUpload() {
            $('#file_upload').uploadify('upload', '*');
        }

        function CloseLoad() {
            $('#file_upload').uploadify('cancel', '*');
        }

        //上传文件
        function AddFile(obj) {
            $("#tbFileList").append("<tr><td>" + obj.data.fileName + "</td><td style=\"width:75px\">" + obj.data.fileSize + "M</td><td style=\"width:75px\"><img title='删除' src='/JS/UI/themes/icons/remove.gif' onclick=\"DeleteFile('" + obj.data.fileName + "','" + obj.data.fileSize + "','" + obj.data.fileUrl + "',this);\" style='cursor:pointer;'></td></tr>");
            var hdFileValue = _files;
            var setValue = "{fileName:'" + obj.data.fileName + "',fileSize:'" + obj.data.fileSize + "',fileUrl:'" + obj.data.fileUrl + "'}";
            if (hdFileValue != "") {
                setValue = hdFileValue + "," + setValue;
            }
            _files = setValue;
        }

        //删除文件
        function DeleteFile(fileName, fileSize, url, tbObj) {

            $.ajax({
                url: "/common/filedel",
                type: "post",
                data: {
                    path: url
                },
                success: function (text) {

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    ShowCommonTip("系统提示", jqXHR.responseText, "info", function () { });
                    //CloseDialog();
                }
            });
            $(tbObj).parent().parent().remove();
            var hdFileValue = _files;
            var removeValue = "{fileName:'" + fileName + "',fileSize:'" + fileSize + "',fileUrl:'" + url + "'}";
            hdFileValue = ("," + hdFileValue).replaceAll("," + removeValue, "");
            if (hdFileValue != "" && hdFileValue.substring(0, 1) == ",") {
                hdFileValue = hdFileValue.substring(1);
            }
            _files = hdFileValue;
        }

        //加载，绑定文件
        function LoadFiles() {
            var filesList = eval("[" + _files + "]");
            $.each(filesList, function (index, obj) {
                $("#tbFileList").append("<tr><td>" + obj.fileName + "</td><td style=\"width:75px\">" + obj.fileSize + "M</td><td style=\"width:75px\"><img title='删除' src='/JS/UI/themes/icons/remove.gif' onclick=\"DeleteFile('" + obj.fileName + "','" + obj.fileSize + "','" + obj.fileUrl + "',this);\" style='cursor:pointer;'></td></tr>");
            });
        }

        //返回上传的文件
        top["FileControls"] = window;
        function getFiles() {
            return _files;
        }
    </script>
    <style type="text/css">
        .table
        {
            width: 390px;
            border: 1px solid #B1CDE3;
            padding: 0;
            border-collapse: collapse;
        }
        .table-1 tr
        {
            border-bottom: 1px solid #B1CDE3;
            height: 30px;
        }
        .table td
        {
            border: 0px;
            background: #fff;
            font-size: 12px;
            padding: 3px 3px 3px 8px;
            color: #4f6b72;
            text-align: center;
        }
    </style>
</head>
<body>
<div style="display: inline-block; width: 800px;">
    <div style="width: 390px; float: left; display: inline;">
        <table class="table">
            <tr>
                <td style="height: 250px">
                    <div id="uploadfileQueue" style="padding: 3px;">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="file_upload">
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <a class="mini-button " iconcls="icon-save" onclick="DoUpload()" tooltip="全部上传">全部上传</a>&nbsp;
                    &nbsp; &nbsp; &nbsp; <a class="mini-button " iconcls="icon-remove" onclick="CloseLoad()"
                                            tooltip="全部取消">全部取消</a>
                </td>
            </tr>
        </table>
    </div>
    <div style="width: 390px; float: left; display: inline;">
        <table style="border-top: 1px solid #B1CDE3; border-right: 1px solid #B1CDE3; border-bottom: 1px solid #B1CDE3;
                width: 390px; background-color: #dedede; padding-left: 10px;">
            <tr>
                <td style="height: 20px; line-height: 20px;">
                    文件名称
                </td>
                <td style="width: 75px">
                    文件大小
                </td>
                <td style="width: 75px">
                    操作
                </td>
            </tr>
        </table>
        <div style="height: 100%; border-bottom: 1px solid #B1CDE3; border-right: 1px solid #B1CDE3;
                border-left: 1px solid #B1CDE3;">
            <table id="tbFileList" class="table-1" style="border-top: 0px; width: 389px; padding-left: 10px;">
            </table>
        </div>
    </div>
</div>
<a style="margin-left: 340px; margin-top: 10px;" class="mini-button" iconcls="icon-cancel"
   onclick="CloseDialog();" tooltip="关闭页面">关闭页面</a>
</body>
</html>
