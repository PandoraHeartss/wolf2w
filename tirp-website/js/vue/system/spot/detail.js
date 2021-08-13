var vue = new Vue({
    el: "#app",
    data: {
        spot: [],
        content: []
    },
    methods: {},
    mounted: function () {
        var param = getParams();
        var _this = this;

        //景点列表
        ajaxGet("/spot/detail", {id: param.id}, function (data) {
            _this.spot = data.data;
            _this.content = data.data.content;
            console.log(content);
        })

    }
});

