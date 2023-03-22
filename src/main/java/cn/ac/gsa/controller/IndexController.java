package cn.ac.gsa.controller;

import cn.ac.gsa.send.SendWe;
import cn.ac.gsa.utility.HttpRequestUtil;
import cn.ac.gsa.utility.SSHUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author chenx
 */
@RestController
public class IndexController {

    @Scheduled(cron = "0 0 */3 * * ?")//每3小时
//    @Scheduled(cron = "0 50 15 * * ?")//每3小时
    public void index() {
        this.projectIndex();
        try {
            Thread.sleep(900000);
            this.sampleIndex();
            Thread.sleep(300000);
            this.gsaIndex();
            Thread.sleep(300000);
            this.humanIndex();
            Thread.sleep(120000);
            this.omixIndex();
            Process p =null;
            String[] arrP = new String[]{"/disk/webdb/software/bigsearch-api-1.5/bin/bigsearch-api","https://ngdc.cncb.ac.cn/gsa/bioprojectA.bs"};
            for(String a:arrP){
                System.out.println(a);
            }
            p = Runtime.getRuntime().exec(arrP);
            p.waitFor();
            p.destroy();

            Process s =null;
            String[] arrS = new String[]{"//disk/webdb/software/bigsearch-api-1.5/bin/bigsearch-api","https://ngdc.cncb.ac.cn/gsa/biosampleA.bs"};
            for(String a:arrS){
                System.out.println(a);
            }
            s = Runtime.getRuntime().exec(arrS);
            s.waitFor();
            s.destroy();


            Process g =null;
            String[] arrG = new String[]{"/disk/webdb/software/bigsearch-api-1.5/bin/bigsearch-api","https://ngdc.cncb.ac.cn/gsa/gsaA.bs"};
            for(String a:arrG){
                System.out.println(a);
            }
            g = Runtime.getRuntime().exec(arrG);
            g.waitFor();
            g.destroy();


            Process h =null;
            String[] arrH = new String[]{"/disk/webdb/software/bigsearch-api-1.5/bin/bigsearch-api","https://ngdc.cncb.ac.cn/gsa-human/hra.bs"};
            for(String a:arrH){
                System.out.println(a);
            }
            h = Runtime.getRuntime().exec(arrH);
            h.waitFor();
            h.destroy();

            Process o =null;
            String[] arrO = new String[]{"/disk/webdb/software/bigsearch-api-1.5/bin/bigsearch-api","https://ngdc.cncb.ac.cn/omix/omix.bs"};
            for(String a:arrO){
                System.out.println(a);
            }
            o = Runtime.getRuntime().exec(arrO);
            o.waitFor();
            o.destroy();

            SendWe.sendIndex("ChenXu","索引生成成功");
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
        } catch (Exception e) {
            SendWe.sendIndex("ChenXu","索引生成失败");
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
            e.printStackTrace();
        }
    }


//    @Scheduled(cron = "0 0 2 * * ?") //每天3点钟执行
    public void humanIndex() {
        String body="";
        String url="https://ngdc.cncb.ac.cn/gsa-human/make/ha";
        String result  = null;
        try {
            result = HttpRequestUtil.doHttpPostResponseJson(url, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Scheduled(cron = "0 0 3 * * ?") //每天3点钟执行
    public void projectIndex() {
        String body="";
        String url="https://ngdc.cncb.ac.cn/gsa/make/pa";
        String result  = null;
        try {
            result = HttpRequestUtil.doHttpPostResponseJson(url, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Scheduled(cron = "0 0 4 * * ?") //每天4点钟执行
    public void sampleIndex() {
        String body="";
        String url="https://ngdc.cncb.ac.cn/gsa/make/sa";
        String result  = null;
        try {
            result = HttpRequestUtil.doHttpPostResponseJson(url, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Scheduled(cron = "0 0 5 * * ?") //每天5点钟执行
    public void gsaIndex() {
        String body="";
        String url="https://ngdc.cncb.ac.cn/gsa/make/ea";
        String result  = null;
        try {
            result = HttpRequestUtil.doHttpPostResponseJson(url, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Scheduled(cron = "0 0 1 * * ?") //每天5点钟执行
    public void omixIndex() {
        String body="";
        String url="https://ngdc.cncb.ac.cn/omix/getOMixDB";
        String result  = null;
        try {
            result = HttpRequestUtil.doHttpPostResponseJson(url, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
