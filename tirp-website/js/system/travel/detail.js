$(function () {
    var strategyTimer = null;
    var strategyIdx = 0;
    var travelTimer = null;
    var travelIdx = 0;

    // 自动播放相关攻略图片
    function autoPlayStrategy() {
        strategyTimer = setInterval(function () {
            strategyIdx++;
            strategyIdx = strategyIdx > 2 ? 0 : strategyIdx;
            playSlide(strategyIdx, $('#_j_gl_slide_container'));
        }, 5000);
    }

    // 自动播放相关游记图片
    function autoPlayTravel() {
        travelTimer = setInterval(function () {
            travelIdx++;
            travelIdx = travelIdx > 2 ? 0 : travelIdx;
            playSlide(travelIdx, $('#_j_rec_slide_container'));
        }, 5000);
    }

    // 公用切换图片方法
    function playSlide(idx, $dom) {
        $dom.find('.gs_nav li').removeClass('on');
        $dom.find('.gs_nav li').eq(idx).addClass('on')
        $dom.find('.gs_content').animate({
            'left': -idx * 240 + 'px'
        });
    }

    // 点击切换图片
    $('#_j_rec_slide_container .gs_nav li').on('click', function (e) {
        // e.stopPropagation();
        clearInterval(travelTimer);
        travelIdx = $(this).attr('data-id');
        travelIdx = travelIdx > 2 ? 0 : travelIdx;
        playSlide(travelIdx, $('#_j_rec_slide_container'));
        autoPlayTravel();
    })
    // 点击切换图片
    $('#_j_gl_slide_container .gs_nav li').on('click', function (e) {
        // e.stopPropagation();
        clearInterval(strategyTimer);
        strategyIdx = $(this).attr('data-id');
        strategyIdx = strategyIdx > 2 ? 0 : strategyIdx;
        playSlide(strategyIdx, $('#_j_gl_slide_container'));
        autoPlayStrategy();
    })

    autoPlayTravel();
    autoPlayStrategy();


    $('body').on('click', function () {
        $('#_js_replyExpression').hide();
    });
    $('.newtab-prev').click(function (e) {
        e.stopPropagation();
        if (!$(this).hasClass('disabled')) {
            $(this).addClass('disabled')
            $('.newtab-next').removeClass('disabled')
            $('._j_switch_container').css('left', '0');

        }
    })

    $('.newtab-next').click(function (e) {
        e.stopPropagation();
        if (!$(this).hasClass('disabled')) {
            $(this).addClass('disabled')
            $('.newtab-prev').removeClass('disabled')
            $('._j_switch_container').css('left', '-324px');
        }
    })

    $('._j_face_tab').click(function (e) {
        e.stopPropagation();
        $('._j_face_tab').removeClass('cur');
        $(this).addClass('cur');
        $('.art_newface').addClass('hide');
        $('.art_newface').eq($(this).index()).removeClass('hide');
    });

    $('#_j_replyfacetrigger').click(function (e) {
        e.stopPropagation();
        $('#_js_replyExpression').show();
    });


});