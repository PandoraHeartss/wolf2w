var vue = new Vue({
    el: "#app",
    data: {
        toasts: [],  //吐司
        dest: {},  //目的地
        catalogs: [], //概况中攻略分类
        strategies: [],  //点击量前3
        page: {}  //游记分页
    },
    methods: {
        commPage: function (page) {
            var param = getParams();
            var p = $("#travelForm").serialize() + "&destId=" + param.id + "&currentPage=" + page;
            //游记分页
            ajaxGet("/travels/query?" + p, {}, function (data) {
                vue.page = data.data;
                buildPage(vue.page.number, vue.page.totalPages, vue.doPage)
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
        var param = getParams();
        var _this = this;

        //查吐司
        ajaxGet("/destinations/toasts", {destId: param.id}, function (data) {
            // _this.toasts = data.data;
            var list = data.data;  //中国  广东  广州
            _this.dest = list.pop();  //数组弹出最后一个
            _this.toasts = list;
        })

        //查概况: List<StrategyCatalog> list
        ajaxGet("/destinations/catalogs", {destId: param.id}, function (data) {
            _this.catalogs = data.data;
        })


        //点击量前上攻略
        ajaxGet("/destinations/strategies/viewnumTop3", {destId: param.id}, function (data) {

            _this.strategies = data.data;
        })


        //游记查询
        ajaxGet("/travels/query", {destId: param.id}, function (data) {
            _this.page = data.data;
            buildPage(vue.page.current, vue.page.pages, vue.doPage);
        })

    }
});

