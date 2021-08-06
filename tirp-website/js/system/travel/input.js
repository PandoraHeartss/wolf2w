var ue;
$(function () {
    // 初始化富文本编辑器
    ue = UM.getEditor('editor', {
        textarea: "content",
        imageUrl: domainUrl + "/travels/contentImage",
        imageFieldName: "upfile",
        imagePath: ''
    });
    // 初始化日历控件
    $.fn.datepicker.languages['zh-CN'] = {
        format: 'yyyy年mm月dd日',
        days: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
        daysShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
        daysMin: ['日', '一', '二', '三', '四', '五', '六'],
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        monthsShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        weekStart: 1,
        startView: 0,
        yearFirst: true,
        yearSuffix: '年'
    };
    $('[data-toggle="datepicker"]').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        autoHide: true
    });

    $('.ap-head dl').click(function () {
        $('.ap-head dl').removeClass('on');
        $(this).addClass('on');
    });


    $('#title').on('focus', function () {
        var prev = $(this).prev();
        prev.removeClass('hide');
        prev.prev().hide();
    }).on('blur', function () {
        var prev = $(this).prev();
        if ($(this).val().length > 0) {
            prev.prev().hide();
        } else {
            prev.prev().show();
        }
        prev.addClass('hide');
    });
    if ($('#title').val()) {
        $('#title').focus();
    }

    $('#title').bind('input propertychange', function () {
        var MAXLENGTH = 48;
        var val = $(this).val();
        if (MAXLENGTH - val.length >= 0) {
            $(this).prev().find('strong').html(MAXLENGTH - val.length);
        } else {
            $(this).val($(this).val().slice(0, MAXLENGTH));
        }
    });

    $('._j_personitem').click(function (e) {
        e.stopPropagation();
        $('.toggle').removeClass('newon');
        $('.toggle').next().hide();
        $(this).parents('._j_piDrop').find('._j_personinput').val($(this).find('a').text());

        $(this).parents('._j_piDrop').find('._j_personhiddeninput').val($(this).data("person"));
    });

    $(document).click(function () {
        if ($('.toggle').hasClass('newon')) {
            $('.toggle').removeClass('newon');
            $('.toggle').next().hide();
        }
    });

    $('._j_personinput').on('click', function (e) {
        e.stopPropagation();
        var parent = $(this).parents('.toggle');
        parent.addClass('newon');
        parent.next().show();
    });

    // $('.btn_submit').click(function () {
    //     var title = $('#title').val(); // 标题
    //     var date = $('._j_dateinput').val(); // 日期
    //     var person = $('._j_person').val(); // 人物
    //     var place = $('._j_place').val(); // 旅游地点
    //     var days = $('#days').val(); // 出行天数
    //     var expense = $('#expense').val(); // 人均花费
    //     var isopen = $('#isopen').prop('checked'); // 是否公开
    //     var labels = []; // 游记标签
    //     $('.pi-tagitem span').each(function (key, value) {
    //         labels.push($(this).text());
    //     })
    //     var content =  ue.getContent(); // 游记内容
    // });

    $('.pi-addinput').keypress(function (e) {
        if (e.which == 13 && $(this).val().trim()) {
            $('._j_added').append('<a class="pi-tagitem" role="button"><span>' + $(this).val().trim() + '</span><i class="rm_tag">×</i></a>');
            $(this).val('');
        }
    });

    $('.pi-addinput').blur(function () {
        $(this).val('');
    });


    $('._j_added').on('click', '.rm_tag', function () {
        $(this).parents('.pi-tagitem').remove();
    })
});