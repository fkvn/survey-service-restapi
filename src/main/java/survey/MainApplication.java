package survey;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MainApplication {

	public static void main(String[] args) {

		SpringApplication.run(MainApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.cors().and().authorizeRequests().antMatchers(HttpMethod.GET, "/surveys/**").permitAll()
					// .antMatchers(HttpMethod.GET, "/canvas/oauth_callback").permitAll()
					.antMatchers(HttpMethod.GET, "/**").hasAuthority("survey-service")
					.antMatchers(HttpMethod.POST, "/**").hasAuthority("survey-service")
					.antMatchers(HttpMethod.PUT, "/**").hasAuthority("survey-service")
					.antMatchers(HttpMethod.PATCH, "/**").hasAuthority("survey-service")
					.antMatchers(HttpMethod.DELETE, "/**").hasAuthority("survey-service").anyRequest()
					.authenticated().and().oauth2ResourceServer().jwt()
					.jwtAuthenticationConverter(new JwtAuthenticationConverter() {

						@Override
						protected Collection<GrantedAuthority> extractAuthorities(final Jwt jwt) {

							@SuppressWarnings("deprecation")
							Collection<GrantedAuthority> authorities = super.extractAuthorities(jwt);
							List<String> scopes = jwt.getClaimAsStringList("scope");
							if (scopes != null && scopes.contains("alice-survey-service-api"))// check if user has
																																								// the scope
																																								// survey-service
							{
								authorities.add(new SimpleGrantedAuthority("survey-service"));
							}
							return authorities;
						}
					});

		}
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {

				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
						.exposedHeaders("Content-Disposition");
			}
		};
	}

	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("survey.web.controller")).build();
	}

}
