package jobworld.app;

import javax.servlet.Filter;
//import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// NB 1st level config class
		return new Class<?>[]{
				DataServiceConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// NB 2nd level config class
		// se ne avessimo avute di più le avremmo dovute specificare qui
		return new Class<?>[]{
				WebConfig.class
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);
		return new Filter[]{new HiddenHttpMethodFilter(), cef};
	}

//	// <=> <multipart-config>
//	@Override
//	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//		registration.setMultipartConfig(getMultipartConfigElement());
//	}
//
//	private MultipartConfigElement getMultipartConfigElement() {
//		return  new MultipartConfigElement(
//				null, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
//	}
//
//	private static final long MAX_FILE_SIZE = 5000000;
//	// Beyond that size spring will throw exception.
//	private static final long MAX_REQUEST_SIZE = 5000000;
//
//	// Size threshold after which files will be written to disk
//	private static final int FILE_SIZE_THRESHOLD = 0;
}