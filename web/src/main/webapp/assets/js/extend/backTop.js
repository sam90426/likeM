/* Build by husz@5173.com 
 Date:2014-03-13 17:55:03 
 Version:1.00 */
 $(function(){$("body").append('<a class="back-top" href="javascript:void(0)"></a>');var a=$(".back-top");$(window).scroll(function(){$(window).scrollTop()>100?a.fadeIn():a.fadeOut()});$(window).scroll();a.click(function(){$("body,html").animate({scrollTop:0},1E3)})});$(function(){var a=$(".ser-handle"),b=$(".fix-right-contact");$(".s-close").click(function(){b.hide();a.show()});a.click(function(){b.show();a.hide()})});$(function(){var a=$(".fix-right-contact"),b=$(".ser-handle");$(window).scroll(function(){$(window).scrollTop()>200?a.addClass("scroll"):a.removeClass("scroll");$(window).scrollTop()>200?b.addClass("scroll"):b.removeClass("scroll")})});$(function(){var a=$(".index-wrap .fix-right-contact"),b=$(".index-wrap .ser-handle");$(window).scroll(function(){$(window).scrollTop()>500?a.addClass("scroll"):a.removeClass("scroll");$(window).scrollTop()>630?b.addClass("scroll"):b.removeClass("scroll")})});$(function(){var a=$(".chat-we"),b=$(".weChat");a=a.find(".patent-hd");a.mouseover(function(){b.show()});a.mouseout(function(){b.hide()})});$(function(){var a=$(".div-side-contactv,.pr-we,.bt-pr"),b=$(".weChat");a.each(function(c){$(this).mouseover(function(){$(b[c]).show()});$(this).mouseout(function(){b.hide()})})});$(function(){var a=$(".on-chat-box"),b=a.find(".alt");a.each(function(c){$(this).mouseover(function(){$(b[c]).show()});$(this).mouseout(function(){b.hide()})})});$(function(){var a=$(".div-feedback"),b=$(".feedback-box");a.mouseover(function(){$(this).addClass("feed-hover");b.show()});a.mouseout(function(){$(this).removeClass("feed-hover");b.hide()})});
