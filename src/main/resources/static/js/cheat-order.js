

$(function () {
    $('#btSearch').click(function () {
        console.log("come here .. ")
        var checkDate = $('#checkDate').val();
        var orderNo = $('#orderNo').val();
        var sortFiled = $('#sortFiled').val();
        var hotelSeq = $('#hotelSeq').val();
        var tbody = window.document.getElementById("tbody-result");

        $.ajax({
            type: "post",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            url: "/user/queryCheatOrder",
            data: "{\"hotelSeq\":\"" + hotelSeq
            + "\",\"orderNo\":\"" + orderNo
            + "\",\"sortFiled\":\"" + sortFiled
            + "\",\"checkDate\":\"" + checkDate
            + "\"}",
            success: function (msg) {
                if (msg.ret) {
                    var str = "";
                    var data = msg.data;

                    console.log("come here")

                    for (i in data) {
                        str += "<tr>" +
                            "<td align='center'>" + data[i].hotelSeq + "</td>" +
                            "<td align='center'>" + data[i].hotelName + "</td>" +
                            "<td align='center'>" + data[i].orderNo + "</td>" +
                            "<td align='center'>" + data[i].userPhone + "</td>" +
                            "<td align='center'>" + data[i].createTime + "</td>" +
                            "<td align='center'>" + data[i].userId + "</td>" +
                            "<td align='center'>" + data[i].cellid + "</td>" +
                            "<td align='center'>" + data[i].gpsCity + "</td>" +
                            "<td align='center'>" + data[i].cellCity + "</td>" +
                            "<td align='center'>" + data[i].distance + "</td>" +
                            "</tr>";
                    }
                    tbody.innerHTML = str;
                }
            },
            error: function () {
                alert("查询失败")
            }
        });
    });
});
