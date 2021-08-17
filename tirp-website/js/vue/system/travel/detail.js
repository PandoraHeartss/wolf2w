//(扮酷小蚂)  ---><img....>
//将 (中文)   格式数据替换成标签图片:
function emoji(str) {
    //表情图片资源, 从马蜂窝扣出来的, 可以百度:
    //1:HttpURLConnection 使用  jdk自带的 代码中如何发起http请求
    //   RestTemplate  项目发短信用到
    //2:HttpClient  第三方http请求发送的框架
    //RestTemplate(client)
    //3:webmagic  专门用于爬虫框架

    //str=现金付款山东省看得(大笑小蜂)见风科技适(大笑小蜂)得府君书(得意小蜂)的开发决胜巅峰
    //匹配中文
    var reg = /\([\u4e00-\u9fa5A-Za-z]*\)/g;

    var matchArr = str.match(reg);  //[(大笑小蜂), 大笑小蜂),(得意小蜂)]


    if (!matchArr) {
        return str;
    }
    for (var i = 0; i < matchArr.length; i++) {
        str = str.replace(matchArr[i], '<img src="' + EMOJI_MAP[matchArr[i]] + '"style="width: width:28px;"/>')
    }
    return str;
}

var vue = new Vue({
    el: "#app",
    data: {
        content: {}, //内容
        detail: {
            coverUrl: "",
            author: {},
            dest: {}
        },
        dest: {},
        toasts: [],
        strategies: [],
        comments: [],
        travels: []
    },
    methods: {
        emotionClick: function (e) {
            e.stopPropagation();
            $('#_js_replyExpression').show();
        },
        emojiitemClick: function (e) {
            $('._j_replyarea').val($('._j_replyarea').val() + '(' + $(e.currentTarget).attr('title') + ')')
        },
        faceTabClick: function (e) {
            e.stopPropagation();
            $('._j_face_tab').removeClass('cur');
            $(e.currentTarget).addClass('cur');
            $('.art_newface').addClass('hide');
            $('.art_newface').eq($(e.currentTarget).index()).removeClass('hide');
        },
        newtabNext: function (e) {
            e.stopPropagation();
            if (!$(e.currentTarget).hasClass('disabled')) {
                $(e.currentTarget).addClass('disabled')
                $('.newtab-prev').removeClass('disabled')
                $('._j_switch_container').css('left', '-324px');
            }
        },
        newtabPrev: function (e) {
            e.stopPropagation();
            if (!$(e.currentTarget).hasClass('disabled')) {
                $(e.currentTarget).addClass('disabled')
                $('.newtab-next').removeClass('disabled')
                $('._j_switch_container').css('left', '0');

            }
        },
        toComment: function (nickname, refId) {
            $("#commentTpye").val(1);
            $("#refCommentId").val(refId);


            $("#commentContent").focus();
            $("#commentContent").attr("placeholder", "回复：" + nickname);

        },
        commentAdd: function (e) {

            var content = $("#commentContent").val();
            if (!content) {
                popup("评论不能为空");
                return;
            }
            var param = {};
            param.travelId = vue.detail.id;
            param.travelTitle = vue.detail.title;
            param.content = emoji(content);

            param.type = $("#commentTpye").val();

            if (param.type == 1) {
                param["refComment.id"] = $("#refCommentId").val();
            } else {
                param["refComment.id"] = "";
            }

            $("#commentTpye").val(0);
            $("#refCommentId").val("");

            ajaxPost("/travels/commentAdd", param, function (data) {
                $("#commentContent").val("");
                $("#commentContent").attr("placeholder", "");

                vue.queryComments(param.travelId);
            })
        },
        queryComments: function (travelId) {
            //游记评论不分页
            ajaxGet("/travels/comments", {travelId: travelId}, function (data) {
                vue.comments = data.data;
            })

        }
    },
    filters: {
        dateFormat: function (date) {
            return dateFormat(date, "YYYY-MM-DD")
        }
    },
    mounted: function () {
        var param = getParams();
        var _this = this;

        //吐司
        ajaxGet("/destinations/toasts", {destId: param.destId}, function (data) {
            vue.dest = data.data.pop();
            vue.toasts = data.data
        })

        //点击量前3的攻略文章
        ajaxGet("/destinations/strategies/viewnumTop3", {destId: param.destId}, function (data) {
            vue.strategies = data.data;
        })


        //游记点击量前3
        ajaxGet("/destinations/travels/viewnumTop3", {destId: param.destId}, function (data) {
            _this.travels = data.data;
        })


        //游记
        ajaxGet("/travels/detail", {id: param.id}, function (data) {
            _this.detail = data.data;
            _this.content = data.data.content

            console.log(_this.detail);

        })


        //游记评论不分页
        this.queryComments(param.id);


        /*
         //游记
         ajaxGet("/travels/detail",{id:param.id}, function (data) {
             vue.detail = data.data;
             vue.content = data.data.content
         })

         //点击量前3的游记文章
         ajaxGet("/destinations/travels/viewnumTop3",{destId:param.destId}, function (data) {
             vue.travels = data.data;
         })



 */

    }
});

