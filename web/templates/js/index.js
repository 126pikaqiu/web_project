


function bindEvents() {
    $(".click-img").click(function () {
        history.go("item.jsp?id=" + $(".click-img").attr("id"))
    })
}