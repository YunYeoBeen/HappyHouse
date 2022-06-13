//package com.example.config;
//
//import javax.servlet.ServletContextListener;
//
//import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.example.controller.interceptor.LoginCheckInterceptor;
//import com.example.listener.RootPathListener;
//
//// 항상 설정 파일은 @Configuration을 붙여주자.
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//	// view controller를 add하는 곳
//	// 한마디로 serlvet-context에서 view-controller태그 부분에 해당하는 부분이다.
//	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/index.do").setViewName("/index");
//		registry.addViewController("/").setViewName("index");
//		registry.addViewController("apt/apt_list_default.do").setViewName("/apt/apt_list");
//		registry.addViewController("apt/apt_list_Name_default.do").setViewName("/apt/aptListByName");
//		registry.addViewController("User/login_form.do").setViewName("/User/login_form");
//		registry.addViewController("User/register_form.do").setViewName("/User/register_form");
//		registry.addViewController("User/member_detail.do").setViewName("/User/member_detail");
//		
//	}
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/*/auth/**");
//	}
//	
//	@Bean
//	public ServletListenerRegistrationBean<ServletContextListener> addListener(){
//		ServletListenerRegistrationBean<ServletContextListener> bean = 
//				new ServletListenerRegistrationBean<ServletContextListener>();
//		bean.setListener(new RootPathListener());
//		return bean;
//	}
//
//}
