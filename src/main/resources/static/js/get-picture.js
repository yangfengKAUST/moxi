

$(function () {
    $('#picSearch').click(function () {

        var d1 = document.getElementById('my-div-id')
        var picPath = "upload";

        $.ajax({
            type: "post",
            contentType: "application/json;charset=utf-8",
            url: "/pic/pictureCheck",
            data: {params : picPath},
            success: function (msg) {
                var pichtml = "";
                if (!msg.length) {
                    alert("还没有考生照片上传，无法查看")
                    return;
                }else {
                    for (var i = 0; i < msg.length; i++ ) {
                        var src = msg[i];
                        var last = src.split('/')
                        var name = last[last.length - 1]
                        var idGroup = name.split('.')
                        var idName = idGroup[0]

                        //todo add a function to trans id into full words, just in case X

                        var html1 = '<img onload="AutoResizeImage(350,520,this)" ' +
                            'src="'+src+'"><a href="javascript:void(0);" onclick="onModified('+idName+')">图片不合格？</a><br>';
                        pichtml += html1;
                    }
                }
                showPicDetail(pichtml, d1);
            },
            error: function (e) {
                console.log(e)
                alert("获取图片list失败")
            }
        });
    });
});

function AutoResizeImage(maxWidth, maxHeight, objImg) {
    var img = new Image();
    img.src = objImg.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth == 0 && maxHeight == 0) {
        Ratio = 1;
    } else if (maxWidth == 0) { //
        if (hRatio < 1)
            Ratio = hRatio;
    } else if (maxHeight == 0) {
        if (wRatio < 1)
            Ratio = wRatio;
    } else if (wRatio < 1 || hRatio < 1) {
        Ratio = (wRatio <= hRatio ? wRatio : hRatio);
    }
    if (Ratio < 1) {
        w = w * Ratio;
        h = h * Ratio;
    }
    objImg.height = h;
    objImg.width = w;
}

function showPicDetail(pictureHtml, d1) {
    var d1 = document.getElementById('my-div-id');
    d1.insertAdjacentHTML('beforeend', pictureHtml);
}


function onModified(src){
    // console.log(src)
    // alert('我要修改用户名称为' + src + '的一行');
    // /**
    //  其它代码
    //  **/
    $.ajax({
        type: "post",
        contentType: "application/json;charset=utf-8",
        url: "/pic/pictureCheckProcess",
        data: "{\"result\":\"" + src
        + "\"}",
        success: function (msg) {

        },
        error: function (e) {
            console.log(e)
            alert("获取图片list失败")
        }
    });
}

