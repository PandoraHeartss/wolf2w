$(function () {
    $('.mfw-header').on('mouseenter', function () {
        $(this).removeClass('header-place-default');
    }).on('mouseleave', function () {
        $(this).addClass('header-place-default');
    });

    $('.search-input').bind('input propertychange', function () {
        var val = $(this).val();
        // debugger;
        if (val) {
            $('.search-suggest-panel').show();
        } else {
            $('.search-suggest-panel').hide();
        }
    });
    $('.search-suggest-panel').on('mouseenter', function () {

    }).on('mouseleave', function () {
        var self = this;
        setTimeout(function () {
            $(self).hide();
        }, 1500);
    });


    $('.pagelet-block .r-navbar a').on('hover', function () {
        var idx = $(this).index();
        idx = idx == 0 ? 0 : idx / 2;
        $('.pagelet-block .r-navbar a').removeClass('on');
        $(this).addClass('on');
        $('.pagelet-block .tiles').addClass('hide');
        $('.pagelet-block .tiles').eq(idx).removeClass('hide');
    });

    $('.pagelet-block1 .r-navbar a').on('hover', function () {
        var idx = $(this).index();
        idx = idx == 0 ? 0 : idx / 2;
        $('.pagelet-block1 .r-navbar a').removeClass('on');
        $(this).addClass('on');
        $('.pagelet-block1 .tiles').addClass('hide');
        $('.pagelet-block1 .tiles').eq(idx).removeClass('hide');
    });
    $('.suggest-list li').click(function () {
        window.location.href = "./index.ftl"
    })
});