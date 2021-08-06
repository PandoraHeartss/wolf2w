var vue = new Vue({
    el: "#app",
    data: {
        regions: [],  //热门排序的区域集合
        destListLeft: [],
        destListRight: [],
        regionId: ''
    },
    methods: {
        hotChange: function (event) {
            var _this = $(event.currentTarget);
            var rid = _this.attr("rid");
            if (!rid) {
                return;
            }
            $('.row-hot .r-navbar a').removeClass('on');
            _this.addClass('on');
            //非国内数据列表
            this.queryRegion(rid);
        },
        queryRegion: function (regionId) {
            ajaxGet("/destinations/search", {regionId: regionId}, function (data) {
                var list = data.data;
                vue.regionId = regionId;
                var destListLeft = [];
                var destListRight = [];
                //将list集合分成左右2边, 进行遍历显示
                for (var i = 0; i < list.length; i++) {
                    if (i % 2 == 0) {
                        destListLeft.push(list[i])
                    } else {
                        destListRight.push(list[i])
                    }
                }
                vue.destListLeft = destListLeft;
                vue.destListRight = destListRight;

            })
        }
    },
    //数据初始化位置
    mounted: function () {
        //热门数据
        var _this = this;
        ajaxGet("/destinations/hotRegion", {}, function (data) {
            _this.regions = data.data;
        })

        //通过区域id查询目的地
        this.queryRegion(-1);


        /* ajaxGet("/destinations/hotRegion", {}, function (data) {
             _this.regions = data.data;
         })

         //通过区域id查询目的地
         this.queryRegion(-1);*/


        //var _this  = this;
        // ajaxGet("/destinations/hotRegion", {}, function (data) {
        //_this .regions = data.data;
        //})

        //通过区域id查询目的地
        //this.queryRegion(-1);
    }
});

