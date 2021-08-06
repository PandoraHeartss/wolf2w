$(function () {
    $('.btn-toggle').click(function () {
        var parent = $(this).parents('.p-item-main');
        if (parent.next('.p-item-poi').hasClass('hide')) {
            parent.next('.p-item-poi').removeClass('hide');
            $(this).find('span').text('收起');
            $(this).find('i').attr('class', 'arr-up');

        } else {
            parent.next('.p-item-poi').addClass('hide');
            $(this).find('span').text('展开更多');
            $(this).find('i').attr('class', 'arr-down');
        }
    });

    $('.post_item').on('mouseenter', function () {
        $(this).find('.delPost').removeClass('hide');
    }).on('mouseleave', function () {
        $(this).find('.delPost').addClass('hide');
    });

    $('.s_title li').click(function () {
        $('.s_title li').removeClass('s_curr');
        $(this).addClass('s_curr')
        $('.p-country').addClass('hide');
        $('.p-country').eq($(this).index()).removeClass('hide');
    });
});