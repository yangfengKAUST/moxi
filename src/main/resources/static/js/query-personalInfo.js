

$(function () {
    $('#btSearch').click(function () {
        var seriesNumber = $('#checkSeriesNumber').val();
        var idNumber = $('#idNumber').val();
        console.log("seriesnumber" + seriesNumber)
        console.log("idNumber" + idNumber)
        var tbody = window.document.getElementById("tbody-result");

        $.ajax({
            type: "post",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            url: "/user/queryPersonalInfo",
            data: "{\"idNumber\":\"" + idNumber
            + "\",\"seriesNumber\":\"" + seriesNumber
            + "\"}",
            success: function (msg) {
                if (msg.ret) {
                    var str = "";
                    var data = msg.data;

                    for (i in data) {

                        var picPath = data[i].photoNumber
                        // var last = picPath.split('/')
                        // var name = last[last.length - 1]
                        // var idGroup = name.split('.')
                        // var idName = idGroup[0]

                        // console.log("photo number " + idName)
                        str += "<tr>" +
                            "<td align='center'>" + data[i].seriesNumber + "</td>" +
                            "<td align='center'>" + data[i].applyNumber + "</td>" +
                            "<td align='center'>" + data[i].name + "</td>" +
                            "<td align='center'>" + data[i].gender + "</td>" +
                            "<td align='center'>" + data[i].cardFormat + "</td>" +
                            "<td align='center'>" + data[i].cardId + "</td>" +
                            "<td align='center'>" + data[i].birth + "</td>" +
                            "<td align='center'>" + data[i].nation + "</td>" +
                            "<td align='center'>" + data[i].politics + "</td>" +
                            "<td align='center'>" + data[i].marriage + "</td>" +
                            "<td align='center'>" + data[i].award + "</td>" +
                            "<td align='center'>" + data[i].education + "</td>" +
                            "<td align='center'>" + data[i].educationLevel + "</td>" +
                            "<td align='center'>" + data[i].major + "</td>" +
                            "<td align='center'>" + data[i].school + "</td>" +
                            "<td align='center'>" + data[i].afterSchool + "</td>" +
                            "<td align='center'>" + data[i].majorLevel + "</td>" +
                            "<td align='center'>" + data[i].note + "</td>" +
                            "<td align='center'>" + data[i].familyNumber + "</td>" +
                            "<td align='center'>" + data[i].passLife + "</td>" +
                            "<td align='center'>" + data[i].phoneNumber + "</td>" +
                            "<td align='center'>" + data[i].teleNumber + "</td>" +
                            "<td align='center'>" + data[i].status + "</td>" +
                            '<img onload="AutoResizeImage(350,520,this)" ' +
                            'src="'+picPath+'">' +
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
