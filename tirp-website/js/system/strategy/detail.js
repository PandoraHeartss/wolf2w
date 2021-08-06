$(function () {
    $('.bs_share').on('mouseenter', function () {
        $(this).parents('.bar-sar').next().show();
    }).on('mouseleave', function () {
        $(this).parents('.bar-sar').next().hide();
    })

    $('.bs_pop').on('mouseenter', function () {
        $(this).show()
    }).on('mouseleave', function () {
        $(this).hide();
    });

})