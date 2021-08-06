var user = getUserInfo();
var vue = new Vue({
    el: "#app",
    data: {
        page: {},
        user: {}


    },
    methods: {
        orderChange: function (orderType) {
            var el = event.currentTarget;
            $(".orderBy").closest("div").removeClass("on");
            $(el).closest("div").addClass("on");

            $("#orderType").val(orderType);

            this.commPage(1);

        },
        commPage: function (page) {
            var param = getParams();
            var p = $("#travelForm").serialize() + "&currentPage=" + page;
            //游记分页
            ajaxGet("/travels/query?" + p, {}, function (data) {
                vue.page = data.data;
                buildPage(vue.page.current, vue.page.pages, vue.doPage);
            })
        },
        doPage: function (page) {
            this.commPage(page);
        },
        conditionChange: function () {
            this.commPage(1);
        }
    },
    mounted: function () {
        //游记分页
        ajaxGet("/travels/query", {}, function (data) {
            vue.page = data.data;
            buildPage(vue.page.current, vue.page.pages, vue.doPage);
        })
    }
});

