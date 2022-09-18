package ru.community.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//настройка всяких цепей фильтров, пропуск неавторизованных пользователей к логину и регистрации
//config all chains of filters, authentificated users check etc
@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // (1)
	//сам пропуск и недопуск людей
	//authentificated pages
  @Override
  protected void configure(HttpSecurity http) throws Exception {  // (2)
      http
        .authorizeRequests()
        		.antMatchers("/auth/**","/my-profile","/","/create-post").authenticated()
        		.antMatchers("/register","/image/**","css/style.css").permitAll()
        		.and()
        		.formLogin().successForwardUrl("/")
        		.and()
        		.logout().logoutSuccessUrl("/hello");
  }
  //In-Memory аутенфикация, бесполезно когда есть бд
  //In-Memory authentification, useless when i got DB
  /*@Bean
  public UserDetailsService jdbcUserDetailsManager() {
	  UserDetails user = User.builder()
			  .username("username")
			  .password("{bcrypt}$2a$12$XVP2nqxU1EtsCUQd.n8nse5I2VLB7AZV1sX7/QJaleyG6hanGrHCe")
			  .roles("USER")
			  .build() ;
	  UserDetails admin = User.builder()
			  .username("admin")
			  .password("{bcrypt}$2a$12$XVP2nqxU1EtsCUQd.n8nse5I2VLB7AZV1sX7/QJaleyG6hanGrHCe")
			  .roles("ADMIN","USER")
			  .build();
	  UserDetails komol = User.builder()
			  .username("komol")
			  .password("{bcrypt}$2a$12$XVP2nqxU1EtsCUQd.n8nse5I2VLB7AZV1sX7/QJaleyG6hanGrHCe")
			  .roles("ADMIN","KOMOL","USER")
			  .build();
	  return new InMemoryUserDetailsManager(user,admin,komol);
  }*/
  //jdbcAuthentication
  private customUserDetailsService uds;
  //раскодирофщик паролей, которые шифруются, перед попаданием в бд, после метода регистрации
  //Password encoder, that crypting in register method
  @Bean
  public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
  }
  //Использование своей БД и всё такое
  //Using my DB and etc
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
	  DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	  authenticationProvider.setPasswordEncoder(passwordEncoder());
	  authenticationProvider.setUserDetailsService(uds);
	  return authenticationProvider;
  }
  @Autowired
  public void setUds(customUserDetailsService uds) {
	  this.uds = uds;
}
}