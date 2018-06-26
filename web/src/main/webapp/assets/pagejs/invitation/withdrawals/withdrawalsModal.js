mini.parse();
var form = new mini.Form("form1");

var id = GetQueryString("id");
//加载
if (id) {
    ShowCommonloading();
    $.ajax({
        url: "/withdrawals/getModalData",
        type: "post",
        data: {
            id: id
        },
        success: function (text) {
            var o = mini.decode(text);
            form.setData(o);
            if (o.state != 1) {
                mini.get("state").disable(true);
                mini.get("failReason").disable(true);
                mini.get("submitBtn").hide();
            }
            // form.setChanged(false);
            CloseCommonloading();
        },
        error: function () {
            CloseCommonloading();
        }
    });
}

function onCancel(e) {
    top["WithdrawalsList"].gridReload();
    CloseDialog();
}

function onSubmit(e) {
    var o = form.getData();
    console.log(o);
    $.ajax({
        url: "/withdrawals/updateWithdrawal",
        type: "post",
        data: {
            id: id,
            state: o.state,
            failReason: o.failReason
        },
        success: function (text) {
            top["WithdrawalsList"].gridReload();
            CloseDialog();
        },
        error: function () {
            top["WithdrawalsList"].gridReload();
            CloseDialog();
        }
    });

}