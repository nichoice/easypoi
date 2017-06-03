//刷新页面
function refresh() {
    $table.bootstrapTable('refresh');
}

//查询按钮点击事件
$("#btn_search").on("click", function () {
    refresh();
});

//清空条件按钮点击事件
$("#btn_clean_search").on("click", function () {
    $('#device').val("");
    $('#type').val("");
    $('#date_begin').val("");
    $('#date_end').val("");
    refresh();
});

//刷新按钮点击事件
$("#btn_refresh").on("click", function () {
    refresh();
});


//显隐分页按钮点击事件
$("#btn_togglepage").on("click", function () {
    $table.bootstrapTable('togglePagination');
});

//跳转按钮点击事件
$("#btn_selectpage").on("click", function () {
    var pageNum = 3;
    $table.bootstrapTable('selectPage', pageNum);
});

