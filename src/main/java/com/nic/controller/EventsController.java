package com.nic.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nic.pojo.Event;
import com.nic.pojo.Pages;
import com.nic.pojo.Query;
import com.nic.pojo.Resultmap;
import com.nic.service.EventServer;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.view.PoiBaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Nic on 2017/5/25.
 * 故障查询下载
 */
@Controller
public class EventsController {
    private static Logger logger = LoggerFactory.getLogger(EventsController.class);
    @Autowired
    private EventServer eventServer;

    public void setEventServer(EventServer eventServer) {
        this.eventServer = eventServer;
    }

    @ResponseBody
    @RequestMapping(value = "/ub", method = RequestMethod.GET)
    public Pages<Event> GetShowEvent(HttpServletRequest request, HttpServletResponse response) {
        return showEvent(request, response, "GET");
    }

    @ResponseBody
    @RequestMapping(value = "/ub", method = RequestMethod.POST)
    public Pages<Event> PostShowEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return showEvent(request, response, "POST");
    }

    public Pages<Event> showEvent(HttpServletRequest request, HttpServletResponse response, String method) {
        String limit = request.getParameter("limit");
        String nowPage = request.getParameter("nowPage");

        String type = request.getParameter("type");
        String device = request.getParameter("device");

        String startTime = request.getParameter("date_begin");
        String endTime = request.getParameter("date_end");

        Map<String, Object> map = new HashMap();
        if (!type.equals("-1")) {
            map.put("type", type);
        }
        if (!device.equals("-1")) {
            map.put("device", device);
        }
        if (!startTime.isEmpty()) {
            map.put("startTime" , startTime);
        }
        if (!endTime.isEmpty()) {
            map.put("endTime" , endTime);
        }

        // 当前页数
        int nowPaged = Integer.parseInt(null == nowPage ? "1" : nowPage);
        // 每页显示数
        int limitd = Integer.parseInt(null == limit ? "10" : limit);

        Pages<Event> pages = new Pages<>();
        //开始分页,参数1为请求第几页,参数2为请求条数
        PageHelper.startPage(nowPaged, limitd);

        List<Event> list = eventServer.getEventsbyTime(map);
        List<Resultmap> eventlist = eventServer.find(map);
        System.out.println(JSON.toJSONString(eventlist));

        //取记录总条数
        PageInfo<Event> pageInfo = new PageInfo<>(list);
        int total = (int) pageInfo.getTotal();

        pages.setSuccess(true);
        pages.setMsg("共查询出" + total + "条数据!");
        pages.setRecords(list);
        pages.setTotal(total);
        pages.setStatus(0);
        return pages;
    }

    @ResponseBody
    @RequestMapping(value = "/exportEventReport", method = RequestMethod.GET)
    public void downloadexcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String device, String type) {
        String date_begin = request.getParameter("date_begin");
        String date_end = request.getParameter("date_end");

        Map<String, Object> map = new HashMap<String, Object>();
        if (Integer.valueOf(device) != -1) {
            map.put("device", device);
        }
        if (Integer.valueOf(type) != -1) {
            map.put("type", type);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd" + "-" + "HHmmss");
        List<Event> eventList = eventServer.getEventsbyTime(map);
        System.out.println(JSON.toJSONString(eventList));
        modelMap.put(NormalExcelConstants.DATA_LIST, eventList);
        modelMap.put(NormalExcelConstants.FILE_NAME, "设备故障" + simpleDateFormat.format(new Date()));
        modelMap.put(NormalExcelConstants.CLASS, Event.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("故障列表", "故障"));
        PoiBaseView.render(modelMap, request, response, NormalExcelConstants.JEECG_EXCEL_VIEW);
    }
}
