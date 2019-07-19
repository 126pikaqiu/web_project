function showMessage(message, type = 2) {
    let ele = $("#myModal .modal-body p");
    $(ele).html(message);
    let color;
    if(type===2) {
        color = "#31708f";
    } else if(type === 1 ) {
        color = "#3c763d"
    } else {
        color = "#8a6d3b"
    }
    $(ele).css("color",color);
    $("#myModal").modal('show');
    setTimeout(function () {
        $("#myModal").modal('hide');
    },1000)
}