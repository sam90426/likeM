/**
 *  轻量级弹窗，暂时替代dialog功能。
 *  author：xuejiang
 *  date:2014/10/23
 **/
;(function(window, $, undefined) {
	$.fn.leanPopup = function(options) {
		var defaults = {
			type: 'pop', // 'dialog'，'pop'；dialog 会附带 title
			title: '', // 标题内容
			top: 150, // 调整距离顶部的距离
			left: 0, // 调整弹出层和左边之间的距离，正数变小，负数变大
			overlay: 0.2 // mark 的透明度
		};
		var $mark = $('<div id="lean-mark"></div>');
		$('body').append($mark);
		options = $.extend(defaults, options);

		return this.each(function(){
			var o = options, 
				$btnModel = $(this),
				$model = $($btnModel.attr('href')),
				_width = $model.outerWidth(),
				$close = $model.find('.close-btn').eq(0);
			// 添加关闭按钮
			$model.append($close);
			// title
			if(typeof o.type === 'string' && o.type == 'dialog' && $model.find('.tt').length == 0){
				$model.prepend('<div class="tt"></div>');
			}
			// 给弹出层添加默认定位位置
			$model.css({
				"display": "none",
				"position": "fixed",
				"opacity": 0,
				"z-index": 1100,
				"left": 50 + "%",
				"margin-left": -(_width / 2) + o.left + "px",
				"top": o.top + "px"
			});

			// 点击弹出层以外的区域关闭弹出层
			$mark.click(function() {
				closePopup($model);
			});
			// 点击 close 按钮关闭弹出层
			$close.click(function() {
				closePopup($model);
			});
			// 绑定弹出层触发按钮
			$btnModel.click(function(e){
				$model.find('.tt').html(o.title || $btnModel.data('title'));
				$mark.show().fadeTo(200, o.overlay);
				$model.show().fadeTo(200, 1);

				e.preventDefault();
				return false;
			});
		});
	};

	function closePopup($model) {
		$('#lean-mark').css({
			'display': 'none',
			'opacity': 0
		});
		$model.css({
			'display': 'none',
			'opacity': 0
		});
	}
})(window, jQuery);