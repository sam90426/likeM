mini.parse();
var form = new mini.Form("form1");
var id = GetQueryString("id");

//保存
function SaveData() {
    form.validate();
    if (form.isValid() == false)
        return;
    var o = form.getData();
    o.raiders = editor.html();
    if(o.id=='')
        o.id='0';
    var json = mini.encode(o);
    ShowCommonloading();
    $.ajax({
        url: "/supply/supplyEditSave",
        dataType: "json",
        type: "post",
        data: {
            data: json
        },
        success: function (resp, opts) {
            CloseCommonloading();
            if(resp.code!=1){
                ShowCommonAlert("系统提示", "保存失败", "error", function () { });
            }else{
                ShowCommonAlert("系统提示", "保存成功", "info", function () {
                    CloseDialog();
                    top["SupplyList"].gridReload();
                });
            }
        },
        failure: function (resp, opts) {
            CloseCommonloading();
            ShowCommonAlert("系统提示", resp.responseText, "error", function () { });
        }
    });
}

//加载
if (id) {
    ShowCommonloading();
    $.ajax({
        url: "/supply/supplyEditLoad",
        dataType: "json",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var model = mini.decode(text);
            form.setData(model);
            if(model.logo){
                $("#imgPicUrl").attr("src",model.logo);
                mini.get("logo").setValue(model.logo);
            }
            if (model.raiders){
                editor.html(model.raiders);
            }
            mini.get("intro").setValue(model.intro);
            CloseCommonloading();
        },
        error: function () {
            CloseCommonloading();
        }
    });
}

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
            'saveDir': "/Supply",
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
                mini.get("logo").setValue(obj.data); //隐藏域赋值
            }
        }
    })
}, 10);

function onOk() {
    SaveData();
}

var editor;
KindEditor.ready(function (K) {
    editor = K.create('textarea[name="raiders"]', {
        resizeType: 1,
        allowPreviewEmoticons: false,
        allowImageUpload: true,
        allowFileManager: true,
        uploadJson: '/common/uploadImg?saveDir=/Info/HelpDic',//saveDir为图片保存的路径 例：/Info/HelpDic
        fileManagerJson: '/assets/js/KindEditor/file_manager_json.jsp',
        items: [
            'source','|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist', '|', 'emoticons', 'image', 'link']
    });
});