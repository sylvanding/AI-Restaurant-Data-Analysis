package com.jiang.service;

import com.jiang.pojo.DayData;
import com.jiang.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.annotation.Resource;

@Aspect
public class DayDataServiceImplAOP {

    @Resource
    private RedisUtil redisUtil;

    @Around(value = "execution(* com.jiang.service.DayDataServiceImpl.getEvrDay(..))")
    public Object aroundGetMonthEverydayData(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        //@Before
        if(redisUtil.hasKey("getMonthEverydayData")){
            return redisUtil.get("getMonthEverydayData");
        }
        //执行切点
        result = pjp.proceed();
        //@After
        redisUtil.set("getMonthEverydayData",result.toString(),1000);
        return result;
    }

}
