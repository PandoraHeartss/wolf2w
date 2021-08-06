var user = getUserInfo();

if (!user) {
    window.confirm("没有登录, 不允许进来");
}

var vue = new Vue({
    el: "#app",
    data: {
        owner: {},
        visitors: [
            {id: "5f1c1a1e43190000f60034c2", nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: "5f1c1a1e43190000f60034c3", nickname: "qsz", headImgUrl: "/images/test/personal/qsz.jpg"},
            {id: "5f1c1a1e43190000f60034c4", nickname: "zhy", headImgUrl: "/images/test/personal/zhy.jpg"},
            {id: 4, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: 5, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: 5, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: 5, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: 5, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: 6, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: 7, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"},
            {id: 8, nickname: "lyf", headImgUrl: "/images/test/personal/lyf.jpg"}
        ],
        totalView: 200,
        todayView: 20
    },
    methods: {},
    filters: {},
    mounted: function () {
        var param = getParams();

        ajaxGet("/users/get", {id: param.ownerId}, function (data) {
            vue.owner = data.data;
        })
    }
});

