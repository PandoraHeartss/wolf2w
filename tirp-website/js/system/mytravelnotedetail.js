$(function () {
    var left = 0;
    $('.prev_btn').click(function () {
        if (left == 0) return;
        left += 240;
        $('.mdd_info').animate({
            left: left + 'px'
        })
    });
    $('.next').click(function () {
        var len = $('.mdd_info').find('a').length;
        if ((-len + 1) * 240 == left) return;
        left -= 240;
        $('.mdd_info').animate({
            left: left + 'px'
        })
    });
});