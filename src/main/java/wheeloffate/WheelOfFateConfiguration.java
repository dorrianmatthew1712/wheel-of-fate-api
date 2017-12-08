package wheeloffate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WheelOfFateConfiguration extends WebMvcConfigurerAdapter {

	// Allowing CORS access from hosted web app
	@Value("${allowed-origins}")
	private String[] allowedOrigins;
	
	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**").allowedOrigins(allowedOrigins);
	}
}
