
function scheduleX(obj) {
    var scheX = {
        fulfill: obj.fulfill || 55,
        listAll: obj.listAll || 100,
        speed: obj.speed || 25,
        again: obj.again || true,
        bgColor: obj.bgColor || "#7d8e91",
        listColor: obj.listColor || "#2bd74c",
        scWidth: obj.scWidth || "300",
        scHeight: obj.scHeight || "25",
    }
    if ($("#scheduleX").length === 1) {
        $("#scheduleX").append('<div class="xList"> <span class="xNum"></span></div>');
        if (scheX.again) {
            $(".xList").css("width", "0");
        }
        $("#scheduleX").css({
            "background-color": scheX.bgColor,
            "width": scheX.scWidth + "px",
            "height": scheX.scHeight + "px",
        })
        $(".xList").css("background-color", scheX.listColor)
        var num = 0;
        var numAll = Math.round(scheX.fulfill / scheX.listAll * 100);
        var xNumAll = setInterval(function () {
            num++;
            $(".xNum").html(num + "%")
            if (num == numAll) {
                clearInterval(xNumAll)
            }
        }, scheX.speed)

        $(".xList").animate({"width": numAll + "%"}, scheX.speed * numAll)
    }
}