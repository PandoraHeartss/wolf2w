/*Vue.filter('dateFormat', function (value) {
    return dateFormat(date, "YYYY-MM-DD HH:mm:ss")
})*/

//延长登录时间
var token = Cookies.get("token");
var user = Cookies.get("user");
if (token && user) {
    Cookies.set('token', token, {expires: 1 / 48, path: '/'});
    Cookies.set('user', user, {expires: 1 / 48, path: '/'});
}

//api ip与端口
var domainUrl = "http://localhost:8080";

//异步请求
function ajaxRequest(url, type, param, success, fail) {

    //通过js操作将加密之后的签名手动添加到参数中去

    //{a:1, b:2,c:3, d:4}
    //param.sign = getSignString(param);  //使用逻辑处理

    //{a:1, b:2,c:3, d:4, sign:xxx}

    var token = getToken();
    $.ajax({
        type: type,

        url: domainUrl + url,
        data: param,
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader("token", token);
        },
        success: function (data) {
            if (!data) {
                popup("请求异常");
            } else {
                if (data.code == 200) {
                    if (success) {
                        success(data);
                    }
                } else if (data.code == 401) {
                    //未登录
                    Cookies.remove("token");
                    Cookies.remove("user");
                    $(".login-out").css("display", "")
                    $(".login_info").css("display", "none")
                    popup("请先登录");

                } else {
                    if (fail) {
                        fail(data);
                    } else {
                        popup(data.msg);
                    }
                }
            }
        },
        error: function () {
            popup("网络不通，请联系管理员~");
        }

    })
}

//异步请求 get方式
function ajaxGet(url, params, success, fail) {
    ajaxRequest(url, "GET", params, success, fail);
}

//异步请求post方式
function ajaxPost(url, params, success, fail) {
    ajaxRequest(url, "POST", params, success, fail);
}

//获取url上的请求参数
function getParams() {
    //获取问号及问号后面的内容
    var url = window.location.search;
    var params = new Object();
    if (url.indexOf("?") != -1) {
        //截取问号后面的内容,再使用&分割多个属性
        var arr = url.substr(1).split("&");
        for (var i = 0; i < arr.length; i++) {
            //使用=分割为keyvalue
            var keyValue = arr[i].split("=");
            params[keyValue[0]] = keyValue[1];
        }
    }
    return params;
}

//获取用户信息
function getUserInfo() {
    var userString = Cookies.get('user');
    console.log(userString);
    if (userString) {
        var user = JSON.parse(userString);

        for (p in user) {
            if (typeof (p) == 'string') {
                user[p] = decodeURIComponent(user[p]);
            }
        }
        return user;
    }
    return null;
}

//获取token
function getToken() {

    return Cookies.get('token');
}


//弹出，3秒消失
function popup(msg) {
    $('body').append('<div id="over_container"><div id="over_message">' + msg + '</div></div>')
    setTimeout(function () {
        $('#over_container').remove();
    }, 3000)
}

//格式转换
function dateFormat(date, pattern) {
    if (!pattern) {
        pattern = "YYYY-MM-DD"
    }
    return moment(date).format(pattern)
}

//分页方法
function buildPage(current, totalPages, doPage) {
    $("#pagination").html('');
    $("#pagination").jqPaginator({
        totalPages: totalPages || 1,
        visiblePages: 5,
        currentPage: current,
        prev: '<a class="prev" href="javascript:void(0);">上一页<\/a>',
        next: '<a class="next" href="javascript:void(0);">下一页<\/a>',
        page: '<a href="javascript:void(0);">{{page}}<\/a>',
        last: '<a class="last" href="javascript:void(0);" >尾页<\/a>',
        onPageChange: function (page, type) {
            if (type == 'change') {
                if (doPage) {
                    doPage(page);
                }
            }
        }
    })
}

$(function () {
    $('._j_close').click(function () {
        $('#_j_layer_0').hide();
    })

    /*$('.collect_icon').click(function () {
      if ($(this).hasClass('on-i02')) {
        $('#_j_layer_0').show();
        $('.collect_icon').removeClass('on-i02')
      } else {
        $(this).addClass('on-i02');
      }
    });*/
    $(window).scroll(function () {
        if ($(this).scrollTop() > 280) {
            $('.toolbar-item-top').show();
        } else {
            $('.toolbar-item-top').hide();
        }
        ;
    });
    $('.toolbar-item-top').click(function () {
        $('html, body').animate({
            scrollTop: 0
        }, 250);
        return false;
    });
});

function getIndexTime() {
    var yy = new Date().getFullYear();
    var mm = new Date().getMonth() + 1;
    var dd = new Date().getDate();
    var my = '';
    if (mm == 1) {
        my = 'Jan';
    } else if (mm == 2) {
        my = 'Feb';
    } else if (mm == 3) {
        my = 'Mar';
    } else if (mm == 4) {
        my = 'Apr';
    } else if (mm == 5) {
        my = 'May';
    } else if (mm == 6) {
        my = 'Jun';
    } else if (mm == 7) {
        my = 'Jul';
    } else if (mm == 8) {
        my = 'Aug';
    } else if (mm == 9) {
        my = 'Sept';
    } else if (mm == 10) {
        my = 'Oct';
    } else if (mm == 11) {
        my = 'Nov';
    } else if (mm == 12) {
        my = 'Dec';
    }

    return '<span class="day">' + dd + '</span>/' + my + '.' + yy;
}


//搜索相关
function searchByType(type, keyword) {
    if (!keyword) {
        popup("请先输入搜索关键字");
        return;
    }
    var html = '';
    if (type == 0) {
        html = 'searchDest.html';
    } else if (type == 1) {
        html = 'searchStrategy.html';
    } else if (type == 2) {
        html = 'searchTravel.html';
    } else if (type == 3) {
        html = 'searchUser.html';
    } else {
        html = 'searchAll.html';
    }
    window.location.href = "/views/search/" + html + "?type=" + type + "&keyword=" + keyword;
}


function getSignString(param) {
    var sdic = Object.keys(param).sort();
    var signStr = "";
    for (var i in sdic) {
        if (i == 0) {
            signStr += sdic[i] + "=" + param[sdic[i]];
        } else {

            signStr += "&" + sdic[i] + "=" + param[sdic[i]];
        }
    }
    return hex_md5(signStr).toUpperCase();
}






















