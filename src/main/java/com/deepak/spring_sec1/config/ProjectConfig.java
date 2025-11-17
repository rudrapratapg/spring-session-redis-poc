package com.deepak.spring_sec1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableRedisHttpSession
public class ProjectConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/myCards","/myAccount","/myLoans","/myBalance")
                        .authenticated()
                        .requestMatchers("/actuator/**")
                        .hasRole("ADMIN")
//                        .permitAll()
//                        .requestMatchers("/actuator/health", "/actuator/info").permitAll() // public health/info
                        .requestMatchers("/notices", "/contact", "/welcome").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        http.sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                );
//        http.requestCache(cache -> cache
//                .requestCache(requestCache())
//        );
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user").password("{noop}password").authorities("read").build();
//        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$Y2/aSSPvMf1TDYYRnRXO9.j4DTWDB4RS3bSmraidg31usKFYaKSjW")
//                .roles("ADMIN")
////                .authorities("read","write")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

//    We can not use this default UserDetailsService if we are implementing a custom user details service
//    spring will get confused on which bean/service to use
//    @Bean
//    public UserDetailsService userDetailsService(@Autowired DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//    We have created a Bean of type UserDetailsService named as `EazyBankUserDetailsService` and annotated it with @Service to make it a bean

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public CompromisedPasswordChecker compromisedPasswordChecker() {
//        return new HaveIBeenPwnedRestApiPasswordChecker();
//    }

//    @Bean
//    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }

//    @Bean
//    public RequestCache requestCache() {
//        HttpSessionRequestCache cache = new HttpSessionRequestCache();
//        cache.setRequestMatcher(new AntPathRequestMatcher("/**", HttpMethod.GET.name()));
//        return cache;
//    }
}
