mini.parse();
var Path = GetQueryString("path");
var form = new mini.Form("form1");
if (Path) {
    mini.get("dicPath").setValue(Path);
}

var editor;
KindEditor.ready(function (K) {
    editor = K.create('textarea[name="content"]', {
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

//保存
function SaveData() {
    var o = form.getData();
    form.validate();
    if (form.isValid() == false) return;
    o.content = editor.html();
    var json = mini.encode(o);
    ShowCommonloading();
    $.ajax({
        url: "/info/helpdiccontenteditsave",
        dataType: "json",
        type: "post",
        data: {
            data: json
        },
        success: function (data) {
            if (data.code == 1) {
                CloseCommonloading();
                CloseDialog();
                top["HelpDicContentList"].gridReload();
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
        url: "/info/helpdiccontenteditp",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var o = mini.decode(text);
            form.setData(o);
            if (o.content)
                editor.html(o.content);
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
