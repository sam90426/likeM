/**
 *  扩展 placeholder 属性
 *  author：xuejiang
 *  date:2014/10/23
 **/

;(function(window, $, undefined) {
    $.placeholder = {
        className: 'forPlaceholder',
        // 检测浏览器是否支持 placeholder 属性
        _support: (function() {
            return 'placeholder' in document.createElement('input');
        })(),
        init: function() {
            // 如果浏览器不支持 placeholder
            if (!this._support) {
                // 暂时只对 input 进行处理
                this.create($('input'));
                this.create($('textarea.placeholder'));
            }
        },
        create: function($inputs) {
            var $input;
            for (var i = 0, len = $inputs.length; i < len; i++) {
                $input = $inputs.eq(i);
                if (!$.placeholder._support && $input.attr('placeholder') !== undefined) {
                    $.placeholder._setValue($input);

                    $input.focus(function(){
                        var $this = $(this);
                        if ($this.val() == $this.attr('placeholder')){
                            $this.val('');
                            $input.removeClass($.placeholder.className);
                        }
                    });
                    $input.blur(function(){
                        var $this = $(this);
                        if ($this.val() == '') {
                            $.placeholder._setValue($this);
                        }
                    });
                }
            }
        },
        _setValue: function($input) {
            $input.val($input.attr('placeholder'));
            $input.addClass(this.className);
        }
    };
})(window, jQuery);