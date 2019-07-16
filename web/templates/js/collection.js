$(document).ready(function () {
    initResource();
    bindEvents();
});

function bindEvents() {
    $(".item").click(function () {
        window.open("item.jsp?id=" + $(".item").attr("id"))
    });
    $(".delete").click(function () {
        $.ajax({
            url:'CollectServlet',
            method:'delete',
            success: function () {

            },
            error: function () {

            }
        })
    });
}

function initResource() {

}
