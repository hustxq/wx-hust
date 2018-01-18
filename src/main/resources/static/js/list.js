/**
 * Created by sse on 2017/9/22.
 */
function refreshList(l) {
    $('#list').empty();
    for (var i = 0; i < l.length; i++) {
        var tmp = l[i];
        var mb = item(tmp);
        $('#list').append(mb);
    }
}
function item(tmp) {
    var res = '<a href="javascript:showNote(' + tmp.id + ');" class="weui-media-box weui-media-box_appmsg">'
    + '<div class="weui-media-box__hd">'
    + '<img class="weui-media-box__thumb" src="/img/avatar3.jpg" alt=""></div>'
    + '<div class="weui-media-box__bd"><h4 class="weui-media-box__title">'
    + tmp.pos
    + '<span class="weui-media-box__title-after">1</span></h4>'
    + '<p class="weui-media-box__desc">'
    + tmp.name
    + '</p></div></a>';
    /*return '<a class="weui-cell weui-cell_access" href="javascript:showNote(' + tmp.id + ');"><div class="weui-cell__bd"><p>'
        + tmp.name
        + '</p></div><div class="weui-cell__ft">' + tmp.pos + '</div></a>';*/
    return res;
}
function showNote(id) {
    $.ajax({
        url: "/employ-info/" + id,
        success: function (res) {
            $.alert(res.note);
        }
    })
}
$(function () {
    var datetime;
    // var list = [];
    var content = document.querySelector("#tab1");
    var status = 0;
    var sx;
    var ex;
    var page = 1;
    var total = 2;
    $.ajax({
        url: '/feedlist',
        success: function (res) {
            // console.log(res);
            list = [];
            total = res.total;
            res = res.res;
            for (var i = 0; i < res.length; i++) {
                var tmp = res[i];
                if (i == 0) {
                    datetime = res[i].time;
                }
                list.push(tmp);
            }
            $('#page').html(page);
            $('#total').html(total);
            refreshList(list);
        },
        fail: function (error) {
            // console.log(error);
            $.alert("数据加载失败，请重试");
        }
    });

    content.addEventListener("touchstart", function (e) {
        e.preventDefault();
        // content.style.background = "#0F0";
        var touch = e.touches[0];
        sx = touch.pageX;
        // console.log(1,sx)
    })
    content.addEventListener("touchmove", function (e) {
        // stopBubble(e)
        status = 1;
        // content.style.background = "#0F0";
        // console.log(1.5)
    })
    content.addEventListener("touchend", function (e) {
        e.preventDefault();
        // content.style.background = "#0F0";
        var touch = e.changedTouches[0];
        ex = touch.pageX;
        // console.log(2,ex,ex - sx)
        if (ex < sx) {
            // console.log('left')
            if (page < total) {
                page++;
                $.ajax({
                    data: {
                        start: page - 1
                    },
                    url: '/feedlist',
                    success: function (res) {
                        // console.log(res);
                        $('#page').html(page)
                        list = [];
                        total = res.total;
                        res = res.res;
                        for (var i = 0; i < res.length; i++) {
                            var tmp = res[i];
                            list.push(tmp);
                        }
                        refreshList(list);
                    },
                    fail: function (error) {
                        // console.log(error);
                        $.alert("刷新失败，请重试");
                    }
                });
            }
        }
        if (ex > sx) {
            // console.log('right')
            if (page > 1) {
                page--;
                $.ajax({
                    data: {
                        start: page - 1
                    },
                    url: '/feedlist',
                    success: function (res) {
                        // console.log(res);
                        $('#page').html(page);
                        list = [];
                        total = res.total;
                        res = res.res;
                        for (var i = 0; i < res.length; i++) {
                            var tmp = res[i];
                            list.push(tmp);
                        }
                        refreshList(list);
                    },
                    fail: function (error) {
                        // console.log(error);
                        $.alert("刷新失败，请重试");
                    }
                });
            }
        }
    });

    content.addEventListener("click", function (e) {
        /*var tag = e.target.tagName;
         console.log("tag",tag)
         if (tag=='pull-to-refresh'){
         e.preventDefault();
         }*/
        // console.log("status",status)
        if (status == 1) {
            status = 0;
            e.preventDefault();
        }
        // content.style.background = "#00F";
        // console.log(3)
    });
    //单击body,弹出'单击了document'
    /*$(document).click(function (e) {
     alert('单击了document');
     });*/
    /*function stopBubble(e) {
     //如果提供了事件对象，则这是一个非IE浏览器
     if (e && e.stopPropagation) {
     //因此它支持W3C的stopPropagation()方法
     e.stopPropagation();
     } else {
     //否则，我们需要使用IE的方式来取消事件冒泡
     window.event.cancelBubble = true;
     }
     }*/

    $('#tab1').pullToRefresh().on('pull-to-refresh', function (e) {
        // console.log(e)
        // stopBubble(e);
        $.ajax({
            url: '/feedlist',
            success: function (res) {
                // console.log(res);
                page = 1;
                $('#page').html(page);
                list = [];
                total = res.total;
                $('#total').html(total);
                res = res.res;
                for (var i = 0; i < res.length; i++) {
                    var tmp = res[i];
                    list.push(tmp);
                }
                refreshList(list);
                $('#tab1').pullToRefreshDone();
            },
            fail: function (error) {
                // console.log(error);
                $.alert("刷新失败，请重试");
            }
        });
        /*$.ajax({
         url: '/fresh-employ',
         data: {
         time: datetime
         },
         success: function (res) {
         // console.log(res);
         for (var i = res.length - 1; i >= 0; i--) {
         if (i == 0) {
         datetime = res[i].time;
         }
         list.unshift(res[i]);
         }
         refreshList(list);
         $('#tab1').pullToRefreshDone();
         },
         fail: function (error) {
         $.alert(error);
         $('#tab1').pullToRefreshDone();
         }
         })*/
    })
});
