package com.yuhao.TeachingServiceSystem.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yuhao.TeachingServiceSystem.common.Module;
import com.yuhao.TeachingServiceSystem.common.NeedLogin;
import com.yuhao.TeachingServiceSystem.common.SessionCache;
import com.yuhao.TeachingServiceSystem.dto.SystemLogDTO;
import com.yuhao.TeachingServiceSystem.dto.api.BaseAPIOutput;
import com.yuhao.TeachingServiceSystem.event.LogEvent;
import com.yuhao.TeachingServiceSystem.exception.BizException;
import com.yuhao.TeachingServiceSystem.exception.NoLoginException;
import com.yuhao.TeachingServiceSystem.model.User;
import com.yuhao.TeachingServiceSystem.util.DevUtils;
import com.yuhao.TeachingServiceSystem.util.MD5Util;
import com.yuhao.TeachingServiceSystem.util.MyWebUtils;
import com.yuhao.TeachingServiceSystem.util.SpringSecurityUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;


/**
 * 实现aop的一个例子，@Aspect和@Component要同时有。 可惜的是，似乎没法拦截controller层。
 *
 * @date 2013-6-19
 *
 */
// @Aspect
// @Component
public class ControllerAOP extends BaseControllerAOP {
    private static Logger logger = Logger.getLogger(ControllerAOP.class);

    @Autowired
    private SessionCache sessionCache;

    @Autowired
    private ApplicationContext applicationContext;

    public static final String EDP = "execution(* com.hnluchuan.*.*.controller..*.*(..))";
    private String clientSecret = "123498sdfasdf89ujalkdsf098asdf2332a";

    public ControllerAOP() {
    }

    @Pointcut(EDP)
    public void pointcut() {
    }

    @Before("pointcut()")
    public void logBefore() {
    }

    @After("pointcut()")
    public void logAfter() {
    }

    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {

            return process(joinPoint);

        } catch (Exception e) {
            throw e;
        } finally {
            if (logger.isInfoEnabled()) {
                HttpServletRequest request = MyWebUtils.getCurrentRequest();
                String url = request.getServletPath();
                logger.info("took " + (System.currentTimeMillis() - start) + "ms to process [" + url + "?" + getParameters(request) + "]");
            }
        }
    }

    private Object process(ProceedingJoinPoint joinPoint) throws InstantiationException, IllegalAccessException, ClassNotFoundException, Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("logAround...");
        }
        SystemLogDTO sl = new SystemLogDTO();

        Object[] args = joinPoint.getArgs();
        Object obj = null;

        String packageName = joinPoint.getTarget().getClass().getPackage().getName();
        if (logger.isDebugEnabled()) {
            logger.debug("packageName = " + packageName);
        }
        boolean isFromMobile = false; // packageName.equals(AppProperties.get("basePackage") + ".web.controller.m");

        try {
            HttpServletRequest request = MyWebUtils.getCurrentRequest();
            if (isFromMobile) {
                String sessionId = request.getHeader("sessionId");
                String methodName = joinPoint.getSignature().getName();
                Method method = null;
                for (Method m : joinPoint.getTarget().getClass().getDeclaredMethods()) {
                    if (m.getName().equals(methodName)) {
                        method = m;
                        break;
                    }
                }
                if (method != null) {
                    if (method.isAnnotationPresent(NeedLogin.class)) {
                        if (!DevUtils.isLocal()) {
                            System.out.println("method : " + methodName + "");
                            checkSessionId(sessionId);
                        }
                    }
                }
            }
            // 真正处理

            String requestUri = request.getRequestURI();
            requestUri = requestUri.substring(1, requestUri.length());
            obj = joinPoint.proceed(args);
            sl.setSuccess(true);
        } catch (Throwable e) {
            obj = processException(joinPoint, sl, packageName, e);
        } finally {
            boolean needLog = ifNeedLog(joinPoint, sl);
            // always log
            if (isFromMobile) {
                needLog = true;
            }
            setRequestInfo(sl);
            if (sl.getUrl() != null && sl.getUrl().startsWith("/admin/systemLog/")) {
                needLog = false;
            }
            if (needLog) {
                setUserInfo(sl);
                // Date date;
                sl.setDate(new Date());
                applicationContext.publishEvent(new LogEvent(sl));
            }
        }
        return obj;
    }

    private void checkSessionId(String sessionId) {
        if (!sessionCache.exists(sessionId)) {
            throw new NoLoginException("请先登陆");
        }
        sessionCache.touch(sessionId);
    }

    @SuppressWarnings({ "unchecked", "unused" })
    private void checkSign(HttpServletRequest request) {
        if (request.getServletPath().equals("/m/image/upload")) {
            return;
        }
        List<String> paramKeys = Lists.newArrayList();
        paramKeys.addAll(request.getParameterMap().keySet());
        Collections.sort(paramKeys);
        String str = "";
        for (String key : paramKeys) {
            if (!"sign".equals(key)) {
                str += key + "=" + request.getParameter(key);
            }
        }
        str += clientSecret;
        String clientSign = request.getParameter("sign");
        String calculatedSign = MD5Util.getMD5Encoding(str);
        logger.error("str = " + str + ", clientSign = " + clientSign + ", serverSign = " + calculatedSign);
        if (!calculatedSign.equalsIgnoreCase(clientSign)) {
            throw new BizException("非法请求");
        }
    }

    private Object processException(ProceedingJoinPoint joinPoint, SystemLogDTO sl, String packageName, Throwable e) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
            Throwable {
        if (e instanceof BizException) {
            logger.error(e.getMessage(), e);
        } else {
            logger.error(e, e);
        }
        sl.setSuccess(false);
        sl.setErrorMessage(e.getMessage());

        if (logger.isDebugEnabled()) {
            logger.debug("packageName = " + packageName);
        }
        if (1 == 2/*packageName.equals(AppProperties.get("basePackage") + ".web.controller.m")*/) {
            // 手机端的
            if (logger.isDebugEnabled()) {
                logger.debug("手机端的请求");
            }

            // check login
            HttpServletRequest request = MyWebUtils.getCurrentRequest();
            String url = request.getServletPath();
            if (SpringSecurityUtils.getCurrentUser() == null) {
                // no login
                if (url.startsWith("/m/user/register") || url.startsWith("/m/user/login")) {
                    // do nothing
                } else {
                    BaseAPIOutput dto = new BaseAPIOutput();
                    dto.setState("-2");
                    dto.setError("请先登陆");
                    // return JsonUtils.toJson(dto);
                }
            }
            /*
            if (e instanceof ObjectNotFoundException) {
                // 返回空的就行了
                String controllerName = joinPoint.getTarget().getClass().getSimpleName();
                String mobileModule = controllerName.substring(1).replace("Controller", "");
                if (logger.isDebugEnabled()) {
                    logger.debug("mobileModule = " + mobileModule);
                }
                String apiDTOClassName = null; // AppProperties.get("basePackage") + ".dto.api.info." + mobileModule + "InfoAPIDTO";
                MobileApiOutput output = new MobileApiOutput();
                output.setData(Class.forName(apiDTOClassName).newInstance());
                return JsonUtils.toJson(output);
            } else if (e instanceof BizException) {
                return JsonUtils.toJson(BaseAPIOutput.error(e));
            } else if (e instanceof NoLoginException) {
            	return JsonUtils.toJson(BaseAPIOutput.errorNoLogin(e));
            } else {
                // 其它的异常统一返回{state:"-1"}
                return JsonUtils.toJson(BaseAPIOutput.error(e));
            } */
        }
        throw e;
    }

    private void setRequestInfo(SystemLogDTO sl) {
        HttpServletRequest request = MyWebUtils.getCurrentRequest();
        String url = request.getServletPath();
        // String url;
        sl.setUrl(url);
        sl.setIp(request.getRemoteHost());
        sl.setRequestMethod(request.getMethod());
        sl.setUserAgent(request.getHeader("user-agent"));
        sl.setParams(getParameters(request));
        sl.setHeaders(getHeaders(request));
    }

    private String getHeaders(HttpServletRequest request) {
        Enumeration<String> e = request.getHeaderNames();
        List<String> list = new ArrayList<String>();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getHeader(name);
            list.add(name + "=" + value);
        }
        return StringUtils.join(list, ", ");
    }

    private String getParameters(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (request instanceof MultipartHttpServletRequest) {
        }

        // String params;
        if (MapUtils.isNotEmpty(map)) {
            StringBuilder params = new StringBuilder();
            for (Object key : map.keySet()) {
                // params.append(key.toString()).append("=").append(map.get(key).toString()).append("\n");
                Object value = map.get(key);
                if (value instanceof String[]) {
                    String[] arr = (String[]) value;
                    StringBuilder valueString = new StringBuilder();
                    for (String a : arr) {
                        valueString.append(a).append(",");
                    }
                    params.append(key.toString()).append("=").append(valueString).append("\n");
                } else {
                    params.append(key.toString()).append("=").append(value.toString()).append("\n");
                }
            }
            return params.toString();
        }
        return null;
    }

    private boolean ifNeedLog(ProceedingJoinPoint joinPoint, SystemLogDTO sl) {
        boolean needLog = false;
        // String module;
        try {
            Class<? extends Object> targetClass = joinPoint.getTarget().getClass();
            if (targetClass.isAnnotationPresent(Module.class)) {
                String module = ((Module) targetClass.getAnnotation(Module.class)).name().getRemark();
                sl.setModule(module);
            }

            // String operation;
            Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().equals(joinPoint.getSignature().getName())) {
                    //if (m.isAnnotationPresent(Log.class)) {
                    //  sl.setOperation(((Log) m.getAnnotation(Log.class)).operation());
                    //needLog = true;
                    //}
                }
            }
        } catch (SecurityException e1) {

        }
        ;
        return needLog;
    }

    private void setUserInfo(SystemLogDTO sl) {
        // User user;
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au != null && au.getPrincipal() != null && au.getPrincipal() instanceof User) {
            User user = (User) au.getPrincipal();
            // System.out.println(user.getUsername());
            //sl.setUser(user);
        }
    }

}
