$(document).ready(function () {
    initResource();
    bindEvents();
    render();
});

function bindEvents() {
}
function render(){
    var items = $(".collection-item-body");
    for(var i = 0; i < items.length; i++) {
        $(items[i]).css("margin-top",((94 - getHeight($(items[i]).css('height')))/2) + "px")
    }
}
function DeleteCollection(itemID) {
    let data = {"itemID":itemID};
    $.when(deleteCollection(data))
        .done(function () {
            showMessage("成功删除",1)
            setTimeout(function () {
                history.go(0);
            },800)
        }).fail(function () {
            showMessage("删除失败",0)
    })

}
function UpdateCollection(itemID) {
    let data = {"itemID":itemID};
    $.when(updateCollection(data))
        .done(function () {
            showMessage("修改权限成功",1,800);
                history.go(0);
        }).fail(function (res) {
        if(data["status"] == 200){
            showMessage("修改权限成功",1,800);
            history.go(0);
        } else {
            showMessage("修改权限失败",0,800);
            history.go(0);
        }
    })

}
function getHeight(height) {
    return parseInt(height.substring(0, height.length - 2))
}
function initResource() {

}
