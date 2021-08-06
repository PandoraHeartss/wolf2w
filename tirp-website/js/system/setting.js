$(function () {
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

    $('.aside a').click(function () {
        $('.aside a').removeClass('on');
        $(this).addClass('on');
        $('.content').addClass('hide');
        $('.content').eq($(this).index()).removeClass('hide')
    });
    $('#_j_upload').click(function () {
        $('#fileupload').click();
    });
    // 图片上传
    $('#fileupload').fileupload({
        dataType: 'json',
        url: '/mine/uploadImg', // 服务器路径
        autoUpload: true,
        add: function (e, data) {
            // 调用此方法上传
            data.submit();
        },
        done: function (e, data) {
            console.log(data);
            $("#imageBtn").attr("src", "/" + data.result.data);
        }
    });

    $('#set-pw-btn').click(function () {
        if ($(this).next().hasClass('hide')) {
            $(this).next().removeClass('hide')
        } else {
            $(this).next().addClass('hide')
        }
    });

    $('.modifyMobile').click(function () {
        $('.content').addClass('hide');
        $('.content').eq(6).removeClass('hide');
        $('.mobile-v2').hide();
        $('.mobile-v1').show();
    });

    $('#id_verify').click(function () {
        $('.mobile-v2').show();
        $('.mobile-v1').hide();
        $('.navtag a').removeClass('on');
        $('.navtag a').eq(1).addClass('on')
    });

    $('#vertifyMail').click(function () {
        $('.content').addClass('hide');
        $('.content').eq(7).removeClass('hide');
    })
});