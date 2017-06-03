<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" type="image/x-icon" href="icon/favicon_64.ico"/>
    <link rel="shortcut icon" type="image/x-icon" href="icon/favicon_64.ico"/>
    <title>设备巡检</title>
    <!-- jQuery -->
    <script type="text/javascript" src="dependents/jquery/jquery.min.js"></script>
    <!-- bootstrap -->
    <script type="text/javascript" src="dependents/bootstrap/js/bootstrap.js"></script>
    <link type="text/css" rel="stylesheet" href="dependents/bootstrap/css/bootstrap.min.css">
    <!-- bootstrap-table -->
    <script type="text/javascript" src="dependents/bootstrap-table/1.9.1/bootstrap-table.js"></script>
    <script type="text/javascript" src="dependents/bootstrap-table/1.9.1/bootstrap-table-locale-all.js"></script>
    <link rel="stylesheet" type="text/css" href="dependents/bootstrap-table/1.9.1/bootstrap-table.css"/>
    <!--[if lt IE 9]>
    <script src="dependents/bootstrap/plugins/ie/html5shiv.js"></script>
    <script src="dependents/bootstrap/plugins/ie/respond.js"></script>
    <![endif]-->
    <!--[if lt IE 8]>
    <script src="dependents/bootstrap/plugins/ie/json2.js"></script>
    <![endif]-->
    <!-- font-awesome -->
    <link rel="stylesheet" type="text/css" href="dependents/fontAwesome/css/font-awesome.min.css" media="all"/>
    <!-- layer -->
    <script type="text/javascript" src="dependents/layer/layer.js"></script>
    <!-- base -->
    <link rel="stylesheet" href="css/base.css">
    <!-- datepicker -->
    <script type="text/javascript" src="js/bootstrap-datepicker.zh-CN.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap-datepicker3.css">
</head>
<body>
<ol class="breadcrumb" style="background-color: #262626">
    <li><strong><span style="color: #2277aa; ">设备巡检</span> </strong></li>
</ol>
<div class="container-fluid">
    <h4>查询条件</h4>
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group form-inline" style="border: 1px solid #ccc;padding: 10px; border-radius: 3px;">
                <div class="input-group input-group-sm">
                    <label for="device">设备类型</label>
                    <select id="device">
                        <option value="-1">全部</option>
                        <option value="1">IPC</option>
                        <option value="2">门禁</option>
                        <option value="3">对讲</option>
                        <option value="4">报警主机</option>
                    </select>
                </div>
                <div class="input-group input-group-sm">
                    <label for="type">故障类型</label>
                    <select id="type">
                        <option value="-1">全部</option>
                        <option value="1">开机</option>
                        <option value="2">关机</option>
                        <option value="3">摄像机离线</option>
                        <option value="4">摄像机上线</option>
                        <option value="5">磁盘错误</option>
                        <option value="6">磁盘恢复</option>
                    </select>
                </div>
                &nbsp;
                <div class="input-group">
                    <label for="date_begin">起始时间</label>
                    <input type="text" id="date_begin" style="margin: 10px 0; width: 200px">
                    &nbsp;
                    <label for="date_end">结束时间</label>
                    <input type="text" id="date_end" style="margin: 10px 0;width: 200px;">
                </div>
                &nbsp;
                <div class="input-group">
                    <button id="btn_search" type="button" class="btn btn-primary btn-sm">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                    </button>
                    &nbsp;
                    <button id="btn_clean_search" type="button" class="btn btn-danger btn-sm">清空条件</button>
                    &nbsp;
                    <button id="download" type="button" class="btn btn-success btn-sm" onclick="exportEvent()">下载
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 表格 -->
    <table id="ub" class="table table-bordered"></table>

</div>
<script type="text/javascript" src="js/table.js"></script>
<script type="text/javascript" src="js/table_crud.js"></script>
<script>
    function DatePicker(beginSelector, endSelector) {
        $(beginSelector).datepicker(
            {
                language: "zh-CN",
                autoclose: true,
                startView: 0,
                format: "yyyy-mm-dd",
                clearBtn: true,
                todayBtn: false,
                endDate: new Date()
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(endSelector).datepicker('setStartDate', new Date(ev.date.valueOf()))
            } else {
                $(endSelector).datepicker('setStartDate', null);
            }
        })
        $(endSelector).datepicker(
            {
                language: "zh-CN",
                autoclose: true,
                startView: 0,
                format: "yyyy-mm-dd",
                clearBtn: true,
                todayBtn: false,
                endDate: new Date()
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(beginSelector).datepicker('setEndDate', new Date(ev.date.valueOf()))
            } else {
                $(beginSelector).datepicker('setEndDate', new Date());
            }
        })
    }
    DatePicker("#date_begin", "#date_end");

    function exportEvent() {
        var type = $("#type option:selected").val();
        var device = $("#device option:selected").val();
        var date_begin = $('#date_begin').val();
        var date_end = $('#date_end').val();
        var url = "/exportEventReport?device=" + device + "&type=" + type + "&date_begin=" + date_begin + "&date_end=" + date_end;
        window.open(url);
    }
</script>
</body>
</html>
