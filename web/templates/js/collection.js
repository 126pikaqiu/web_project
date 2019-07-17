$(document).ready(function () {
    initResource();
    bindEvents();
    render();
});

function bindEvents() {
    $(".item").click(function () {
        window.location.href = 'item.jsp?id=' + $(this).attr('id');
    });
}
function render(){
    var items = $(".collection-item-body");
    for(var i = 0; i < items.length; i++) {
        $(items[i]).css("margin-top",((94 - getHeight($(items[i]).css('height')))/2) + "px")
    }
}
function getHeight(height) {
    return parseInt(height.substring(0, height.length - 2))
}
function initResource() {

}
