$(function () {
    var playTimer = null;
    var playIdx = 0;

    function autoPlay() {
        playTimer = setInterval(function () {
            playIdx++;
            play();
        }, 5000);
    }

    function play() {
        $('.show-image li').hide();
        $('.show-nav li').removeClass('active');
        playIdx = playIdx > 4 ? 0 : playIdx;
        $('.show-image li').eq(playIdx).fadeIn();
        $('.show-nav li').eq(playIdx).addClass('active');
    }

    autoPlay();

    $('.show-nav li').on('click', function () {
        playIdx = +$(this).attr('data-id');
        clearInterval(playTimer);
        play();
        autoPlay();
    });

    $('.searchtab li').click(function () {
        var idx = +$(this).attr('data-index');
        $('.searchtab li').removeClass('tab-selected');
        $(this).addClass('tab-selected');
    });


    $('.tn-nav li').click(function () {
        $('.tn-nav li').removeClass('active');
        $(this).addClass('active');
    });

    $('body').on('click', function () {
        $('.tn-dropdown-pop').hide();
    })

    $('.tn-menu').click(function (e) {
        $('.tn-dropdown-pop').show();
        e.stopPropagation();
    })

    $('._j_close').click(function () {
        $('.tn-dropdown-pop').hide();
    });

    $('.tn-dropdown-pop').click(function (e) {
        e.stopPropagation();
    });


})