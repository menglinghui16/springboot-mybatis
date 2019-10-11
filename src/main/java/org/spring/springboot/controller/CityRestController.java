package org.spring.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.spring.springboot.dao.CityDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {
	private static final Logger log = Logger.getLogger(CityRestController.class);

    @Autowired
    private CityService cityService;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private CityDao cityDao;


    @RequestMapping(value = "/api/city")
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
        return cityService.findCityByName(cityName);
    }
    
    @RequestMapping(value = "/test11")
    public String test11() {
    	System.out.println("调用成功了！！！");
    	log.info("log日志！！！");
    	return "调用成功了！";
    }
    @RequestMapping(value = "/test12")
    public String test12() {
    	log.info("log日志12！！！");
    	log.info("输出错误了11111:"+1/0);
    	return "调用成功了12！";
    }
    
    @RequestMapping(value = "/testInterface")
    public String testInterface() {
    	SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);//关闭session的自动提交
    	cityDao = session.getMapper(CityDao.class);//利用反射生成mapper对象
    	List<City> list = new ArrayList<City>(); 
    	list.add(new City(1001L,"北京市","北京市"));
    	list.add(new City(1002L,"天津市","天津市"));
    	list.add(new City(1004L,"重庆市","重庆市"));
    	list.add(new City(1004L,"上海市","上海市"));
        try {
            int i=1;
            for(City city:list){
            	cityDao.insertSelective(city);

                if (i % 10 == 0 || i == list.size()) {
                    //手动每1000个一提交，提交后无法回滚
                    session.commit();
                    session.clearCache();//注意，如果没有这个动作，可能会导致内存崩溃。
                }
                i++;
            }
        }catch (Exception e){
            //没有提交的数据可以回滚
            session.rollback();
        }finally {
            session.close();
        }
    	
    	
        return "调用成功了qqqqq!";
    }

}
