package org.zerock.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//DispatcherServlet 사용를 위한 상속 후 구현
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class, SecurityConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ServletConfig.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); //404 에러페이지 별도 지정 설정
        //파일 업로드 처리
        MultipartConfigElement multipartConfig = new MultipartConfigElement("D:\\kimwoocheol\\PersonelStudy\\IntelliJ\\SpringMavenProject_Java\\out\\artifacts\\SpringMavenProject_war_exploded\\upload\\temp", 20971520, 41943040, 20971520);
        registration.setMultipartConfig(multipartConfig);
    }

    //한글 처리 필터 메소드
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("springSecurityFilterChain");
        return new Filter[] { characterEncodingFilter, delegatingFilterProxy };   //순서 바뀔시 시큐리티 한글 처리 안됨
    }

}
