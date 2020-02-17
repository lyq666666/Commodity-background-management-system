package com.lyq.ssm.controller;

import com.lyq.ssm.domain.SysLog;
import com.lyq.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {


    @Autowired
    private SysLogService sysLogService;

    private Date startTime;//开始访问时间
    private Long viewTime;//访问时长
    private Class ExecutionClass;//当前访问的类对象
    private Method ExecutionMethod;//当前访问的方法对象
    private String url;//当前的方法的url值
    private String ip;//当前访问ip值
    private String userName;//当前访问的用户名

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* com.lyq.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        startTime = new Date();//访问时间
        //获取访问的类
        ExecutionClass = jp.getTarget().getClass();
        //获取访问的方法
        String MethodName = jp.getSignature().getName();
        //获取方法的参数集合
        Object[] args = jp.getArgs();
        //获取参数列表的class对象集合
        Class[] argsClass = new Class[args.length];
        //根据类和方法名获取方法对象
        if (args.length == 0 || args == null) {//没有参数的情况
            ExecutionMethod = ExecutionClass.getMethod(MethodName);

        } else {
            for (int i = 0; i < args.length; i++) {
                argsClass[i] = args[i].getClass();
            }
            ExecutionMethod = ExecutionClass.getMethod(MethodName, argsClass);
        }
    }


    @After("execution(* com.lyq.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取操作的时长
        viewTime = new Date().getTime() - startTime.getTime();
        String[] ClassValue = null;
        String[] MethodValue = null;
        //获取当前的URL
        //1.通过反射获取当前类的RequestMapping注解上的value值
        if (ExecutionClass != null && ExecutionMethod != null && ExecutionClass != LogAop.class) {
            RequestMapping ClassAnnotation = (RequestMapping) ExecutionClass.getAnnotation(RequestMapping.class);
            if (ClassAnnotation != null) {

                ClassValue = ClassAnnotation.value();
            }

            RequestMapping MethodAnnotation = (RequestMapping) ExecutionMethod.getAnnotation(RequestMapping.class);
            if (MethodAnnotation != null) {

                MethodValue = MethodAnnotation.value();
            }
        }
        //2.获取当前·1方法上的注解的value值


        url = ClassValue[0] + MethodValue[0];


        /*获取当前的ip*/
        ip = request.getRemoteAddr();


        //获取当前用户
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        userName = user.getUsername();


        //查看日志的操作本身不计入日志操作记录中
        if (!ClassValue[0].equals("/sysLog")) {
            //接下来封装日志对象SysLog
            SysLog sysLog = new SysLog();
            sysLog.setIp(ip);
            sysLog.setExecutionTime(viewTime);
            sysLog.setVisitTime(startTime);
            sysLog.setUrl(url);
            sysLog.setUsername(userName);
            sysLog.setMethod("类名：" + ExecutionClass.getName() + "方法名：" + ExecutionMethod.getName());
            //调用Service将SysLog对象数据存入数据库中
            sysLogService.save(sysLog);
        }


    }


}
