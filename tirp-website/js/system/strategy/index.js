$(function () {
    // 鼠标移入右侧推荐导航
    $('.nav-item').on('hover', function () {
        var navItemDOMs = $('.nav-item');
        for (var i = 0; i < navItemDOMs.length; i++) {
            navItemDOMs.eq(i).removeClass('hover');
        }
        $(this).addClass('hover');
    });
    // 鼠标移出右侧推荐导航
    $('.gonglve-nav').on('mouseleave', function () {
        var navItemDOMs = $('.nav-item');
        for (var i = 0; i < navItemDOMs.length; i++) {
            navItemDOMs.eq(i).removeClass('hover');
        }
    });

    $('#search_result').hide()
    // 攻略搜索没有则显示提示
    /*$('#search_result ul').hide()
    $('#search_result').addClass('no_gl').show()*/
    /*$('#search_result strong').hide()
    $('#search_result').addClass('search_resu').show()*/

    /**
     * 轮播功能
     */
    var slideIdx = 0;
    var slideTimer = null;

    function autoPlay() {
        slideTimer = setInterval(function () {
            slideIdx++;
            changePlay();
        }, 8000);
    }

    function changePlay() {
        var thumboxDOMs = $('#thumb_box li');
        for (var i = 0; i < thumboxDOMs.length; i++) {
            thumboxDOMs.eq(i).removeClass('on');
        }
        slideIdx = slideIdx < 5 ? slideIdx : 0;
        $('#thumb_box li').eq(slideIdx).addClass('on');
        $('#slide_box').animate({'left': -slideIdx * 700 + 'px'}, 600);
    }

    autoPlay()

    $('#thumb_box li').click(function () {
        clearInterval(slideTimer);
        slideIdx = +$(this).attr('data-id');
        changePlay();
        autoPlay();
    })

    // 收起
    $('.more_selector').click(function () {
        var isToggle = $(this).hasClass('sp_toggle');
        if (isToggle) {
            $(this).removeClass('sp_toggle');
            $(this).parent('span').prev().css('height', '28px');
        } else {
            $(this).addClass('sp_toggle');
            $(this).parent('span').prev().css('height', 'auto');
        }
    })


})