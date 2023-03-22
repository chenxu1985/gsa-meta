package cn.ac.gsa.controller;

import cn.ac.gsa.send.SendWe;
import cn.ac.gsa.utility.HttpRequestUtil;
import cn.ac.gsa.utility.SSHUtil;
import cn.ac.gsa.utility.SSHUtils;
import net.minidev.json.JSONObject;
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
public class TaxonomyController {
   // @Scheduled(cron = "0 0 12 ? * 1") //每周一中午12点
//    @Scheduled(cron = "0 48 15 * * ?")//每1分钟监测
   @Scheduled(cron = "0 0 16 * * ?") //每天3点钟执行
    public void taxonomyDownload() {
          String ip = "192.168.130.12";
          String username = "webdb";
          String pass = "web12@ngdc$2021!";

          SSHUtils sshutil = new SSHUtils(ip, username, pass,22);
          Date to = new Date();
          String toDay = new SimpleDateFormat( "yyyyMMdd").format(to);
          String fileDir = "/disk/webdb/taxonomy/"+toDay;
          File f = new File(fileDir);
          if(!f.exists()){
              try {
                  String cmd = "wget https://ftp.ncbi.nlm.nih.gov/pub/taxonomy/new_taxdump/new_taxdump.zip -P "+fileDir;
                  System.out.println(cmd);
                  sshutil.execCommandByJSch(cmd);
                  Thread.sleep(1500000);
                  sshutil.closeSession();
                  SendWe.sendDownLoad("ChenXu","taxonomy下载完成，路径：/disk/webdb/taxonomy/"+toDay);
              } catch (Exception e) {
                  System.out.println("下载报错，下载日期："+to+"");
                  SendWe.sendDownLoad("ChenXu","taxonomy下载失败");
                  e.printStackTrace();
              }

          }else {
              System.out.println("已经下载："+to+"的数据");
          }

    }
}
