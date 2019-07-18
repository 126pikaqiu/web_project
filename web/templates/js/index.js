
$(document).ready(function () {
    initResource();
    bindEvents();
});

function bindEvents() {
    $("img.click-img").click(function () {
        window.open("item.jsp?id=" + $(this).attr("id"))
    });
}

function initResource() {

}