/*
package com.nic.controller;

import com.nic.pojo.Event;
import com.nic.service.EventServer;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.view.PoiBaseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

*/
/**
 * Created by Nic on 2017/5/31.
 * 下载日志
 *//*


@Controller
public class DownloadExcel {
    @Autowired
    private EventServer eventServer;
    @RequestMapping(value = "/down" , method = RequestMethod.POST)
    public void download(ModelMap modelMap , HttpServletRequest request , HttpServletResponse response){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd" + "-" + "HHmmss");
        List<Event> list = eventServer.getEvents();
        modelMap.put(NormalExcelConstants.DATA_LIST , list);
        modelMap.put(NormalExcelConstants.FILE_NAME , "设备故障" + simpleDateFormat.format(date));
        modelMap.put(NormalExcelConstants.CLASS , Event.class);
        modelMap.put(NormalExcelConstants.PARAMS , new ExportParams("故障列表" , "故障"));
        PoiBaseView.render(modelMap , request , response , NormalExcelConstants.JEECG_EXCEL_VIEW);
    }
}
*/
