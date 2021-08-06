var vue = new Vue({
    el: "#app",
    data: {
        page: {},
        day: getIndexTime(),
        banners: [],
        page: {},
        stBanner: {}
    },
    methods: {
        searchClick: function () {
            var type = $(".tab-selected").data("index");
            var keyword = $("#keyword").val();
            if (!keyword) {
                popup("请先输入搜索关键字");
                return;
            }
            searchByType(type, keyword);
        },
        orderBy: function (type) {
            $("#orderType").val(type);
            this.doPage(1);
        },
        changActive: function (index, event) {
            $(".show_nav").removeClass('active');
            $(".show_image").css("display", '');
            $(event.currentTarget).closest('li').addClass('active');
            $(".show_image[data-id=" + index + "]").css("display", 'list-item');
        },
        doPage: function (page) {
            var p = $("#searchForm").serialize() + "&currentPage=" + page;
            //游记分页
            ajaxGet("/travels/query?" + p, {}, function (data) {
                vue.page = data.data;
                buildPage(vue.page.current, vue.page.pages, vue.doPage)
            })
        }

    },
    filters: {
        subStr: function (str) {
            if (str.length > 100) {
                return str.substring(0, 100) + "...";
            } else {
                return str;
            }
        }
    },
    mounted: function () {

        ajaxGet("/banners/travel", {}, function (data) {
            vue.banners = data.data;  //游记banner，前面5个
        })

        ajaxGet("/banners/strategy", {}, function (data) {
            vue.stBanner = data.data[0];  //攻略
        })

        //游记分页
        this.doPage(1);


    }
});

