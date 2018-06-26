$(function(){
    $("#common-body").css("height",document.body.scrollHeight-158+"px");
});
//左侧导航行为
$("ul[attr='nav']").find(".item-name").click(function (event) {
    var $this = $(this), $parent = $this.parent();
    if ($parent.hasClass('active')) {
        //缩小
        $parent.removeClass('active'); //移除识别
        $this.find('b').removeClass('bottom').addClass('right');
        $this.siblings('ul').slideUp(400);
    } else {
        //展开
        $parent.addClass('active'); //添加识别
        $this.siblings('ul').slideDown(400);
        $this.find('b').removeClass('right').addClass('bottom');
    }
});
//左菜单选中样式控制
function onLeftMenuStyle(obj) {
    $("a[attr='LeftMenu']").each(function () {
        $(this).removeClass('active');
    });
    $("a[name='aLeftMenu" + obj + "']").addClass('active');
}