function showMessage(message, type = 2) {
    var that;
    if(type===2) {
        $("#messageinfo.alert-body").html(message);
        that = $("#messageinfo.alert-body");
    } else if(type === 1 ) {
        $("#messagesuccess.alert-body").html(message);
        that = $("#messagesuccess.alert-body");
    } else {
        $("#messagewarning.alert-body").html(message);
        that = $("#messagewarning.alert-body");
    }
    $(that).alert();
    setTimeout(function () {
        $(that).alert('close');
    },1000)
}