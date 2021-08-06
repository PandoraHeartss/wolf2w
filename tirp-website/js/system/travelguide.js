$(function () {
    $('.drop').on('mouseenter', function () {
        $(this).addClass('open');
    }).on('mouseleave', function () {
        $(this).removeClass('open');
    });

    $('.trigger').click(function () {
        var prev = $(this).prev();
        if (prev.hasClass('extend')) {
            $(this).text('更多');
            prev.removeClass('extend');
        } else {
            $(this).text('收起');
            prev.addClass('extend');
        }
    });
});