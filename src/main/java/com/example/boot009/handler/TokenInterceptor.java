//package com.example.boot009.handler;
//import com.example.boot009.mapper.StarMapper;
//import com.example.boot009.mapper.UserMapper;
//import com.example.boot009.util.TokenUtil;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        //跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
//        if(request.getMethod().equals("OPTIONS")){
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        }
//        response.setCharacterEncoding("utf-8");
//        String token = request.getHeader("token");
//        String username = request.getHeader("username");
//        System.out.println("username::"+username);
//        if (token!=null){
//            boolean result= TokenUtil.verify(token);
//            if (result){
//                System.out.println("通过拦截器");
//                return true;
//            }
//        }
//        response.setContentType("application/json; charset=utf-8");
//        try {
//            JSONObject json=new JSONObject();
//            json.put("msg","token verify fail");
//            json.put("code","500");
//            response.getWriter().append(json.toString());
//            System.out.println("认证失败，未通过拦截器");
//        } catch (Exception e) {
//            return false;
//        }
//        /**
//         * 还可以在此处检验用户存不存在等操作
//         */
//
//        return false;
//    }
//}
//
