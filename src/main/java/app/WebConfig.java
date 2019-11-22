package app;
/**
 * Classe WebConfig
 * 
 * @author Giuseppe Costantini
 * @author Simone di Saverio
 * @author Lorenzo Giuliani
 * @author Savio Feng
 * @version 1.0
 */
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"it.univpm.advprog.singers"})
public class WebConfig {
	//Declare our static resources. I added cache to the java config but it's not required.
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/")
					.setCachePeriod(31556926);
		}

		@Bean 
		StandardServletMultipartResolver multipartResolver() {
			return new StandardServletMultipartResolver();
		}

//		@Bean
//		UrlBasedViewResolver tilesViewResolver() {
//			UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
//			tilesViewResolver.setViewClass(TilesView.class);
//			return tilesViewResolver;
//		}
	//
//		@Bean
//		TilesConfigurer tilesConfigurer() {
//			TilesConfigurer tilesConfigurer = new TilesConfigurer();
//			tilesConfigurer.setDefinitions(
//					"/WEB-INF/layouts/layouts.xml",
//					"/WEB-INF/views/**/views.xml"
//			);
//			tilesConfigurer.setCheckRefresh(true);
//			return tilesConfigurer;
//		}

//		@Bean
//		public Validator validator() {
//			final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//			validator.setValidationMessageSource(messageSource());
//			return validator;
//		}
	//
//		// <=> <mvc:annotation-driven validator="validator"/>
//		@Override
//		public Validator getValidator() {
//			return validator();
//		}
		@Bean
		InternalResourceViewResolver viewResolver(){
		  InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
		  resolver.setPrefix("/WEB-INF/views/"); 
		  resolver.setSuffix(".jsp" );
		  resolver.setRequestContextAttribute("requestContext"); 
		  resolver.setViewClass(JstlView.class);
		  return resolver;
		}
		
		// <=> replacement for 'typeConversionService'  bean
		@Override
		public void addFormatters(FormatterRegistry formatterRegistry) {
			formatterRegistry.addFormatter(dateFormatter());
		}

		@Bean
		public DateFormatter dateFormatter() {
			return new DateFormatter();
		}

		@Bean
		ReloadableResourceBundleMessageSource messageSource() {
			ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
			messageSource.setBasenames("WEB-INF/i18n/messages", "WEB-INF/i18n/application");
			messageSource.setDefaultEncoding("UTF-8");
			messageSource.setFallbackToSystemLocale(false);
			return messageSource;
		}

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(localeChangeInterceptor());
			registry.addInterceptor(themeChangeInterceptor());
			registry.addInterceptor(webChangeInterceptor());
		}

		@Bean
		LocaleChangeInterceptor localeChangeInterceptor() {
			LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
			interceptor.setParamName("lang");
			return interceptor;
		}

		@Bean ResourceBundleThemeSource themeSource() {
			return new ResourceBundleThemeSource();
		}

		@Bean
		ThemeChangeInterceptor themeChangeInterceptor() {
			return new ThemeChangeInterceptor();
		}

		@Bean
		WebContentInterceptor webChangeInterceptor() {
			WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
			webContentInterceptor.setCacheSeconds(0);
			webContentInterceptor.setSupportedMethods("GET", "POST", "PUT", "DELETE");
			return webContentInterceptor;
		}

		@Bean
		CookieLocaleResolver localeResolver() {
			CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
			cookieLocaleResolver.setDefaultLocale(Locale.ITALIAN);
			cookieLocaleResolver.setCookieMaxAge(3600);
			cookieLocaleResolver.setCookieName("locale");
			return cookieLocaleResolver;
		}

		@Bean
		CookieThemeResolver themeResolver() {
			CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
			cookieThemeResolver.setDefaultThemeName("standard");
			cookieThemeResolver.setCookieMaxAge(3600);
			cookieThemeResolver.setCookieName("theme");
			return cookieThemeResolver;
		}

		// <=> <mvc:default-servlet-handler/>
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}

		// <=> <mvc:view-controller .../>
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("singers/list");
		}
}
