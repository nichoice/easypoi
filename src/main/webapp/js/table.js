var $table = $('#ub');

//bootstrapTable使用中文
$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

//防止表头与表格不对齐
$(window).resize(function () {
    $table.bootstrapTable('resetView');
});

$(function () {
    //使用严格模式
    "use strict";

    tableInit();
    $('#ub').bootstrapTable('hideLoading');
})

//初始化Table
function tableInit() {
    //先销毁表格
    $table.bootstrapTable('destroy');

    $table.bootstrapTable({
        //请求地址
        url: '/ub',
        //请求方式
        method: 'post',
        //请求内容类型
        contentType: "application/x-www-form-urlencoded",
        //数据类型
        dataType: "json",
        //table高度，
        //如果没有设置height属性，
        //表格自动根据记录条数觉得表格高度
        height: '481',
        //是否显示行间隔色
        striped: true,
        //是否启用排序
        sortable: false,
        //排序方式
        sortOrder: "asc",
        //是否使用缓存
        cache: false,
        //每行的唯一标识
        uniqueId: "id",
        //指定工具栏
        //toolbar: "#toolbar",
        //显示隐藏列
        showColumns: true,
        //显示刷新按钮
        showRefresh: false,
        //切换显示样式
        showToggle: false,
        //显示Table脚部
        showFooter: false,
        //是否显示详细视图
        cardView: false,
        //是否显示父子表
        detailView: false,
        //detail格式化
        //detailFormatter: genderDetail,
        //是否显示分页
        pagination: true,
        //是否显示分页按钮
        showPaginationSwitch: false,
        //是否启用点击选中行
        clickToSelect: false,
        //最少要显示的列数
        minimumCountColumns: 2,
        //cell没有值时显示
        undefinedText: '-',
        //分页方式：client客户端分页，server服务端分页
        sidePagination: "server",
        //每页的记录行数
        pageSize: 15,
        //初始化加载第1页，默认第1页
        pageNumber: 1,
        //可供选择的每页的行数
        pageList: "[15, 20, 30, 50]",
        paginationFirstText: "首页",
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        paginationLastText: "末页",
        buttonsClass: 'default',
        iconsPrefix: 'glyphicon',
        queryParams: queryParams,
        icons: {
            //paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
            //paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
            //refresh: 'glyphicon-refresh icon-refresh',
            toggle: 'glyphicon-list-alt icon-list-alt',
            columns: 'glyphicon-th icon-th',
            //detailOpen: 'glyphicon-plus icon-plus',
           // detailClose: 'glyphicon-minus icon-minus'
        }, columns: [{
            title: '序号',
            field: 'index',
            align: 'center',
            valign: 'middle',
            formatter: genderIndex
        }, {
            title: '设备类型',
            field: 'device',
            align: 'center',
            valign: 'middle',
            formatter: genderDevice
        }, {
            title: 'IP地址',
            field: 'code',
            align: 'center',
            valign: 'middle'
        }, {
            title: '故障类型',
            field: 'type',
            align: 'center',
            valign: 'middle',
            formatter: genderEvent
        }, {
            title: '故障时间',
            field: 'time',
            align: 'center',
            valign: 'middle',
            formatter: genderTime
        }],
        responseHandler: function (res) {
            if (res.status == 0) {
                var obj = {
                    "total": res.total,
                    "rows": res.records,
                };
            } else {
                var obj = {
                    "total": 0,
                    "rows": [],
                }
            }
            return obj;
        }, onLoadSuccess: function () {
            //加载成功时执行
            console.log("加载成功!");
        }, onLoadError: function () {
            //加载失败时执行
            layer.msg("加载失败!", {icon: 2, time: 2000});
        }, formatLoadingMessage: function () {
            //正在加载
            return "请稍等，正在加载中...";
        }, formatNoMatches: function () {
            //没有匹配的结果
            return '无符合条件的记录';
        }
    })
}

//生成序号
function genderIndex(value, row, index) {
    return index + 1;
}

//格式化时间
function genderTime(value, row, index) {
    return timeStamp2String(value) ;
}

//替换device数据为文字
function genderDevice(value, row, index) {
    if (value == null || value == undefined) {
        return "-";
    } else if (value == 1) {
        return "IP摄像机";
    } else if (value == 2) {
        return "门禁";
    }else if (value == 3) {
        return "对讲";
    }else if (value == 4) {
        return "报警主机";
    }
}

//替换event数据为文字
function genderEvent(value, row, index) {
    if (value == null || value == undefined) {
        return "-";
    } else if (value == 1) {
        return "开机";
    } else if (value == 2) {
        return "关机";
    }else if (value == 3) {
        return "摄像机离线";
    }else if (value == 4) {
        return "摄像机上线";
    }else if (value == 5) {
        return "磁盘错误";
    }else if (value == 6) {
        return "磁盘恢复";
    }
}

/*
//自定义列内容事件
window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        editData(row);
    },
    'click #remove': function (e, value, row, index) {
        delData(row.id, "one");
    }
};
*/

//查询条件与分页数据
function queryParams(params) {
    //排序方式
    params.order = "modify_time desc";
    //第几页
    params.nowPage = this.pageNumber;
    //device
    params.device = $("#device option:selected").val();
    //type
    params.type = $('#type option:selected').val();
    //time
    params.date_begin = $('#date_begin').val();
    params.date_end = $('#date_end').val();

    return params;
}

function timeStamp2String (time){
    var datetime = new Date(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1;
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minute = datetime.getMinutes();
    var second = datetime.getSeconds();
    return year + "-" + month + "-" + date +" "+hour+":"+minute+":"+second;
};
