$(function () {
    $('.tn-dropdown').on('mouseenter', function () {
        $(this).addClass('dropdown-open');
    }).on('mouseleave', function () {
        $(this).removeClass('dropdown-open');
    });
});