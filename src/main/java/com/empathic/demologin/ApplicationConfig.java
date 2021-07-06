package com.empathic.demologin;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableAutoConfiguration
public class ApplicationConfig {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		chainDefinition.addPathDefinition("/**", "anon"); // all paths are managed via annotations.
		chainDefinition.addPathDefinition("/login", "authc");

		// or allow basic authentication, but NOT require it.
		// chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");
		return chainDefinition;
	}

	@Bean
	public Realm realm() {
		return new ShiroRealm(jdbcTemplate);
	}
}
