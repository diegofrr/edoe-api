package com.projetodsc.edoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.projetodsc.edoe.filter.FiltersToken;

@SpringBootApplication
public class EDoeApplication {
	
	@Bean
	public FilterRegistrationBean<FiltersToken> filterJwt(){
		FilterRegistrationBean<FiltersToken> filterRB = new FilterRegistrationBean<FiltersToken>();
		filterRB.setFilter(new FiltersToken());
		filterRB.addUrlPatterns("/auth/login", "/api/itens", "/api/itens/cadastrar");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(EDoeApplication.class, args);
	}

}
