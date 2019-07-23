function collection(itemID) {
    let data = {"itemID":itemID};
    $.when(addCollection(data))
        .done(function () {
            showMessage("收藏成功",1)
        }).fail(function () {
            showMessage("收藏失败",0)
    })
}

function like(itemID) {
    let data = {"itemID":itemID};
    if(localStorage.getItem(itemID + "hot") == 1) {
        showMessage("热度+0，您已点过赞");
        return;
    }
    $.when(addHot(data))
        .done(function () {
            showMessage("热度+1",1);
            $("#hot").text(parseInt($("#hot").text()) + 1);
            localStorage.setItem(itemID + "hot",'1');
        }).fail(function () {
        showMessage("热度+0，请检查网络连接")
    });
}