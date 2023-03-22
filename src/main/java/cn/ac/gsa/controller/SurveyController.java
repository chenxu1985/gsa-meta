package cn.ac.gsa.controller;

import cn.ac.gsa.send.SendWe;
import cn.ac.gsa.utility.HttpRequestUtil;
import net.minidev.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author chenx
 */
@RestController
public class SurveyController {


      @Scheduled(cron = "0 */10 * * * ?")//每10分钟监测
//      @Scheduled(cron = "0 */2 * * * ?")//每10分钟监测
      public void autoWar(){
          try {
              this.surveyGub();
              this.surveyGsaHuman();
              this.surveyGsaStatistics();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
//    @Scheduled(cron = "0 48 15 * * ?")//每10分钟监测
//    public void autoTest(){
//        try {
//            this.test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void surveyGub() throws Exception {
          String body="";
          String url="https://ngdc.cncb.ac.cn/gsub/check/checkRunning";
          String result  = HttpRequestUtil.doHttpPostResponseJson(url, body);
          if(result==null||"".equals(result)){
              SendWe.sendDownTime("ChenXu|WangYanQing","gsub服务器疑似宕机，请及时调查");
          }
          SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
          System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }

    public void surveyGsaHuman() throws Exception {
        String body = "";
        String url="https://ngdc.cncb.ac.cn/gsa-human/check/checkRunning";
        String result  = HttpRequestUtil.doHttpPostResponseJson(url, body);
        if(result==null||"".equals(result)){
            SendWe.sendDownTime("ChenXu|WangYanQing","gsa-human服务器疑似宕机，请及时调查");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }

    public void surveyGsaStatistics() throws Exception {
        String body="";
//        String url="https://ngdc.cncb.ac.cn/gsa/statistics";
        String url="https://ngdc.cncb.ac.cn/gsa/statistics";
        String result  = HttpRequestUtil.doHttpPostResponseJson(url, body);
        if(result==null||"".equals(result)){
            SendWe.sendDownTime("ChenXu","gsa统计页面出现错误，请及时调查");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }
//
//    public void test() throws Exception {
//        String body="";
//        String url="https://ngdc.cncb.ac.cn/gsub/check/checkRunning";
//        SendWe.sendDownTime("ChenXu|WangYanQing","测试");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }
}
