var vue = new Vue({
    el: "#app",
    data: {
        spots: []
    },
    methods: {},
    mounted: function () {

        var param = getParams();
        var _this = this;

        //景点列表
        ajaxGet("/spot/list", {}, function (data) {
            _this.spots = data.data;
        })
    }
});

