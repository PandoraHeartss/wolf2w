var vue = new Vue({
    el: "#app",
    data: {
        page: {},
        qo: {}
    },
    methods: {
        doPage: function (page) {
            ajaxGet("/q", {currentPage: page, type: 1, keyword: $("#_j_search_input").val()}, function (data) {
                vue.page = data.data.page;
                buildPage(vue.page.number + 1, vue.page.totalPages, vue.doPage)
            })
        },
        searchChange: function (type) {
            searchByType(type, $("#_j_search_input").val());
        }
    },
    filters: {
        dateFormat: function (date) {
            return dateFormat(date, "YYYY-MM-DD HH:mm:ss")
        }
    },
    mounted: function () {
        var params = getParams();
        ajaxGet("/q", params, function (data) {
            var map = data.data;
            vue.page = map.page;
            vue.qo = map.qo;
            $("#_j_search_input").val(vue.qo.keyword);
            $("#searchType").val(1);  //攻略
            buildPage(vue.page.number + 1, vue.page.totalPages, vue.doPage)
        })
    }
});

