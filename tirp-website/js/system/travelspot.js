$(function () {
    var guideFlag = true;
    $('.drop').on('mouseenter', function () {
        $(this).addClass('open');
    }).on('mouseleave', function () {
        $(this).removeClass('open');
    });

    $('.city-guide').on('mouseenter', function () {
        guideFlag = true;
        $(this).parent().next().removeClass('hide');
    }).on('mouseleave', function () {
        var self = this
        setTimeout(function () {
            if (guideFlag) {
                $(self).parent().next().addClass('hide');
                guideFlag = false;
            }
        }, 100);
    });

    $('.overview-drop').on('mouseenter', function () {
        guideFlag = false;
        $(this).removeClass('hide');
    }).on('mouseleave', function () {
        var self = this
        $(self).addClass('hide');
        guideFlag = true;
    });

    $('.tn-dropdown').on('mouseenter', function () {
        $(this).addClass('dropdown-open');
    }).on('mouseleave', function () {
        $(this).removeClass('dropdown-open');
    });
});