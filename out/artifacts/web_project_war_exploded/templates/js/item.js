function collection(itemID) {
    let data = {"itemID":itemID};
    $.when(addCollection(data))
        .done(function () {
            showMessage("收藏成功",1)
        }).fail(function () {
            showMessage("收藏失败",0)
    })
}