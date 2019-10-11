package org.spring.springboot.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.spring.springboot.controller.CityRestController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskService {
	private static final Logger log = Logger.getLogger(CityRestController.class);

	@Scheduled(cron = "0/10 * * * * ?")
	public void testTask(){
		System.out.println("调用时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		log.info("log日志测试---");
	}
}
