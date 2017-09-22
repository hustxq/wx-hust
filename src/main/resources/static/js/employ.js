/**
 * Created by sse on 2017/9/22.
 */
//        进入时定位到name可输入
$('#name').focus();
$('#submit_btn').on('click', function () {
    var name = $.trim($('#name').val());
    var pos = $('#position').val();
    var note = $('#note').val();
    if (name.length == 0) {
        $.alert("不能为空哟");
    } else if (pos.length == 0) {
        $.alert("不能为空哟");
    } else if (note.length == 0) {
        $.alert("不能为空哟");
    } else {
        $.confirm("", "确认发布", function () {
//                    确认
            data = {
                name: name,
                pos: pos,
                note: note
            };
            // console.log(data);
            $.ajax({
                url: '/employee',
                data: data,
                success: function (res) {
                    // console.log(res, res.result);
                    $('#name').val("");
                    $('#position').val("");
                    $('#note').val("");
                    $.toast(res.result);
                }
            })
        }, function () {
//                    取消
        })

    }
});
function myTrim(s) {
    return s.replace(/^\s+|\s+$/gm, '');
}