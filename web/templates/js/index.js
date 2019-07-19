
$(document).ready(function () {
    initResource();
    bindEvents();
});

function bindEvents() {
    $("img.click-img").click(function () {
        window.open("item.jsp?id=" + $(this).attr("alt"))
    });
    $("img.index-reflect-img").click(function () {
        window.open("item.jsp?id=" + $(this).attr("alt"))
    })
    $("img.index-reflect-img").mouseover(function () {
        $($(".index-body p")[$("img.index-reflect-img").index(this)]).css("visibility","visible")
    })
    $("img.index-reflect-img").mouseout(function () {
        $($(".index-body p")[$("img.index-reflect-img").index(this)]).css("visibility","hidden")
    })
}

function initResource() {

}