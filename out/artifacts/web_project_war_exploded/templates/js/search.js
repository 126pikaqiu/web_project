let itemNum = 0;
let pageNum = 1;

$(document).ready(function () {
    initResource();
    bindEvents();
    changePageTo(1);
});

function bindEvents() {
    //换页的效果
    $(".paN").click(function () {
        changeStyle(this);
        changePageTo(this.innerHTML);
        pageNum = parseInt(this.innerHTML);
    });
    //所有查看按钮的点击
    $(".lay-item .lay-bt").click(function () {
        window.open($($(".lay-item a")[$(".lay-item .lay-bt").index($(this))]).attr("href"));
    });
    //enter检测
    $("#key").keydown(function (e) {
        if (e.keyCode === 13) {
            $("#search").click();
        }
    });
    $("#search").click(function () {
        pageNum = 1;
        search(1);
        $(".paN").html(function (n) {
            return n + 1 + ((parseInt(pageNum / 12)) * 12);
        });
        changeStyle($('.paN')[0]);
    });
    //排序
    $(".lay-head input").change(function () {
        search(1);
    });
    //分页
    //上下页
    $(".hava-bor").click(function () {
        //上一页
        if ($(this).is(".pn-prev")) {
            let page = "";
            if (pageNum === 1) return;//第一页的话无效

            if (pageNum % 12 === 1)
                page = $(".paN")[11];
            else if (pageNum % 12) {
                page = $(".paN")[pageNum % 12 - 2];
            } else {
                page = $(".paN")[10];
            }//确定哪页需要改变样式

            pageNum--;//页数减一
            changeStyle(page);//改变样式

            //给页脚赋新的值
            if (!(pageNum % 12)) {
                $(".paN").html(function (n) {
                    return n + 1 + ((parseInt(pageNum / 12) - 1) * 12);
                });
                $(".paN").removeClass("dis-none");
            }

            //换页
            changePageTo(pageNum);

        } else {//下一页
            let itemsNumber = itemNum;
            let allPageNumber = itemsNumber % 9 === 0 ? itemsNumber / 9 : parseInt(itemsNumber / 9) + 1;
            if (pageNum === allPageNumber) //最后一页便没有了下一页
                return;

            let page = $(".paN")[(pageNum % 12)];
            changeStyle(page);

            pageNum++;
            $(".paN").removeClass("dis-none");
            changePageTo(pageNum);

            //给页脚赋新的值
            $(".paN").html(function (n) {
                return n + 1 + ((parseInt(pageNum / 12)) * 12);
            });


            //减少部分页脚数
            if (!(pageNum - 1) % 12 && allPageNumber - pageNum < 11) {

                for (let i = allPageNumber - pageNum + 1; i < 12; i++) {
                    $($(".paN")[i]).addClass("dis-none");
                }
            }
        }
    });


    //直接跳转
    $(".p-skip a").click(function () {
        let itemsNumber = itemNum;
        let allPageNumber = itemsNumber % 9 === 0 ? itemsNumber / 9 : parseInt(itemsNumber / 9) + 1;
        let input = $(".input-txt");
        if (isNaN(input.val()) || input.val() < 0 || input.val() > allPageNumber)
            return;
        else {
            $(".paN").removeClass("dis-none");
            changePageTo(input.val());
            if (parseInt(input.val()) % 12)
                changeStyle($(".paN")[parseInt(input.val()) % 12 - 1]);
            else {
                changeStyle($(".paN")[11]);
            }
            pageNum = parseInt(input.val());
            $(".paN").html(function (n) {
                return n + 1 + ((parseInt(pageNum / 12)) * 12);
            });
            if (allPageNumber - pageNum < 11) {
                for (let i = allPageNumber % 12; i < 12; i++) {
                    $($(".paN")[i]).addClass("dis-none");
                }
            }
        }
    });

}

//改变页数的css
function changeStyle(page) {
    let lastPage = $(".curr");
    lastPage.removeClass("curr");
    lastPage.css({
        color: "#333",
        border: "1px solid #ddd",
        backgroundColor: "#f7f7f7",
        padding: "8px 15px",
    });
    page = $(page);
    page.addClass("curr");
    page.css({
        color: "#e4393c",
        border: "none",
        backgroundColor: "transparent",
        padding: "1px 15px",
    });
}

function changePageTo(page) {
    search(page);
}

//搜索并返回数据
function search(page) {
    pageNum = parseInt(page);
    let key = $("#key").val();
    let order = $("#hot").is(":checked") ? "hot" : "name";
    let data = {"key": checkKey(key), "order": order, "page": page};
    $.when(searchByOrder(data))
        .done(function (msg) {
            console.log(msg);
            msg = eval("("+msg+")");
            itemNum = msg["pageNum"];
            msg = msg["items"];
            for (let i in msg) {
                $($(".lay-item")[i]).attr("class", "lay-item");
                $($(".lay-item a")[i]).attr("href", "item.jsp?id=" + msg[i]["id"]);
                $($(".lay-item img")[i]).attr("src", msg[i]["img"]);
                $($(".lay-title")[i]).html(msg[i]["name"]);
                $($("button.lay-bt")[i]).click(function () {
                    window.location.href = "item.jsp?id=" + msg[i]["id"];
                });
                $($(".hot")[i]).html(msg[i]["hot"]);
                $($(".lay-des")[i]).html(msg[i]["description"].substr(0, 30) + "...");
            }
            $(".paN").removeClass("dis-none");
            let itemsNumber = itemNum;
            let allPageNumber = itemsNumber % 9 === 0 ? itemsNumber / 9 : parseInt(itemsNumber / 9) + 1;
            $(".lay-footer .p-skip em b").html(allPageNumber);
            if (allPageNumber < 12 || pageNum > 12) {
                for (let i = allPageNumber % 12; i < 12; i++) {
                    $($(".paN")[i]).addClass("dis-none");
                }
            }
            $(".search-result").html("共搜索到" + itemNum + "个结果");
            if (!itemsNumber) {
                $(".lay-item").addClass("dis-none")
            }
            if (page * 9 - itemsNumber > 0 && page * 9 - itemsNumber <= 9) {
                for (let index = itemsNumber % 9; index < 9; index++) {
                    $($(".lay-item")[index]).addClass("dis-none");
                }
            }

        })
}

function checkKey(key) {
    if(!key){
        return '';
    }
    let result = {};
    result["unsure"] = '';
    key = key.toLowerCase();
    key = key.replace("少于","<").replace("是","").replace("为","").replace("是","").replace("=","").replace("artisit","&artisit=").replace("title","&title=").
    replace("description","&description=").replace("hot","&hot=").replace("year","&yearOfWork=").replace("genre","&genre=").replace("年份","&yearOfWork=").replace("多于",">").
    replace("小于","<").replace("大于",">").replace("作者",'&artist=').replace("作品名","title=").
    replace("描述",'&description=').replace("热度",'&hot=').replace("流派",'&genre=');
    let keys = key.split("&");
    for(let i in keys){
        if(keys[i].indexOf("=") === -1){
            if(/[0-9]/.test(keys[i])){
                result['price'] = keys[i].match(/\d{1,}/)[0];//当作价格
                continue;
            }
            result["unsure"] += keys[i].replace(/\d/,"");
        }else{
            let kes = keys[i].split("=");
            result[kes[0] + ""] = kes[1];
        }
    }
    let index = 0;
    let sql = "";
    for(let i in result){
        if(i === "unsure"){
            if(result[i])
                sql += " AND (artist LIKE '%" + result[i] + "%' or title LIKE '%"+ result[i] + "%' or description LIKE '%" + result[i] + "%')";
        }else if(i === "price" || i === "view" || i === "yearOfWork"){
            let number = result[i].replace(/[^0-9]/ig, "");
            if(!number){
                continue;
            }
            if((index = result[i].indexOf(">")) !== -1|| (index = result[i].indexOf("<")) !== -1){
                sql += " AND " + i + result[i].substr(index,index + 1) + number;
            }else{
                sql += " AND " + i + '=' + number;
            }
        }else{
            sql += ' AND ' + i + " LIKE '%" + result[i] + "%' ";
        }
    }
    return sql;
}

function initResource() {

}
