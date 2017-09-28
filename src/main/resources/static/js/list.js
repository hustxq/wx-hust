/**
 * Created by sse on 2017/9/22.
 */
function refreshList(l) {
    $('#list').empty();
    for (var i = 0; i < l.length; i++) {
        var tmp = l[i];
        var mb = '<a class="weui-cell weui-cell_access" href="javascript:showNote(' + tmp.id + ');"><div class="weui-cell__bd"><p>' + tmp.name + '</p></div><div class="weui-cell__ft">' + tmp.pos + '</div></a>';
        $('#list').append(mb);
    }
}
function showNote(id) {
    $.ajax({
        url:"/employ-info/"+id,
        success:function (res) {
            $.alert(res.note);
        }
    })
}
$(function () {
    var datetime;
    var list = [];
    $.ajax({
        url: '/feedlist',
        success: function (res) {
            // console.log(res);
            for (var i = 0; i < res.length; i++) {
                var tmp = res[i];
                if (i == 0) {
                    datetime = res[i].time;
                }
                list.push(tmp);
            }
            refreshList(list);
        },
        fail: function (error) {
            console.log(error);
        }
    });
//        var datetime =  new Date().Format("yyyy-MM-dd hh:mm:ss");
//        var time = date.g
//     FastClick.attach(document.body);
    var content = document.querySelector("#tab1");
    var status = 0;
    /*content.addEventListener("touchstart", function(e){
        e.preventDefault();
        content.style.background = "#0F0";
        console.log(1)
    })*/
    content.addEventListener("touchmove", function(e){
        // stopBubble(e)
        status = 1;
        // content.style.background = "#0F0";
        // console.log(1.5)
    })
    /*content.addEventListener("touchend", function(e){
        e.preventDefault();
        content.style.background = "#0F0";
        console.log(2)
    });*/

    content.addEventListener("click", function(e){
        /*var tag = e.target.tagName;
        console.log("tag",tag)
        if (tag=='pull-to-refresh'){
            e.preventDefault();
        }*/
        // console.log("status",status)
        if (status == 1){
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
        })
    })
});
