var user = getUserInfo();
var vue = new Vue({
    el: "#app",
    data: {
        strategy: {},
        page: {},
        vo: {},
        content: {}
    },
    methods: {
        strategyThumbup: function () {
            ajaxPost("/strategies/strategyThumbup", {sid: vue.strategy.id}, function (data) {
                if (data.data) {
                    popup("顶成功啦");
                } else {
                    popup("今天你已经定过了");
                }
                vue.queryStatisVo(vue.strategy.id);
            })
        },
        favor: function () {
            ajaxPost("/strategies/favor", {sid: vue.strategy.id}, function (data) {
                if (data.data) {
                    popup("收藏成功");
                } else {
                    popup("已取消收藏");
                }


                vue.queryStatisVo(vue.strategy.id);
                if (user) {
                    vue.queryUserFavor(vue.strategy.id, user.id);
                }

            })
        },
        contentFocus: function () {
            $("#content").focus();
        },
        commentThumb: function (commentId) {
            var page = $("#pagination").find("a.active").html() || 1;
            ajaxPost("/strategies/commentThumb", {cid: commentId, sid: getParams().id}, function (data) {
                vue.commentPage(page, getParams().id);
            })
        },
        mouseover: function (even) {
            $(even.currentTarget).find(".rep-del").css("display", "block");
        },
        mouseout: function (even) {
            $(even.currentTarget).find(".rep-del").css("display", "none");
        },
        commentPage: function (page, strategyId) {//分页
            strategyId = strategyId || vue.strategy.id;
            ajaxGet("/strategies/comments", {currentPage: page, strategyId: strategyId}, function (data) {
                vue.page = data.data;
                buildPage(vue.page.number + 1, vue.page.totalPages, vue.commentPage);
            })
        },
        commentAdd: function () { //添加评论
            var param = {}
            param.strategyId = vue.strategy.id;
            param.strategyTitle = vue.strategy.title;

            var content = $("#content").val();
            if (!content) {
                popup("评论内容必填");
                return;
            }
            param.content = content;
            $("#content").val('');

            ajaxPost("/strategies/commentAdd", param, function (data) {
                vue.queryStatisVo(param.strategyId);
                vue.commentPage(1, param.strategyId);
            })
        },
        queryStatisVo: function (sid) {
            //统计数据
            ajaxGet("/strategies/statisVo", {sid: sid}, function (data) {
                vue.vo = data.data;
            })


        },
        queryUserFavor: function (sid, userId) {
            ajaxGet("/users/strategies/favor", {sid: vue.strategy.id, userId: userId}, function (data) {
                console.log(data.data);
                if (data.data) {
                    $(".btn-collect i").addClass('on-i02');
                } else {
                    $(".btn-collect i").removeClass('on-i02');
                }
            })
        },
        thumbEcho: function (thumbuplist) {
            if (user) {
                var id = parseInt(user.id)
                return $.inArray(id, thumbuplist) != -1;
            }
        }
    },
    filters: {
        dateFormat: function (date) {
            return dateFormat(date, "YYYY-MM-DD HH:mm:ss")
        }
    },
    mounted: function () {
        var param = getParams();
        var _this = this;


        //查明细
        ajaxGet("/strategies/detail", {id: param.id}, function (data) {
            _this.strategy = data.data;
            _this.content = data.data.content;

            //用户收藏攻略id集合
            if (user) {
                console.log(user);
                _this.queryUserFavor(param.id, user.id);
            }
            //统计数据
            _this.queryStatisVo(param.id);

        })

        //攻略评论分页
        this.commentPage(1, param.id);



    }
});

