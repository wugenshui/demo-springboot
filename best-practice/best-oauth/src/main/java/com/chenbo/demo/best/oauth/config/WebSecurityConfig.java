package com.github.wugenshui.best.oauth.config;

import com.github.wugenshui.best.oauth.service.JdbcUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : chenbo
 * @date : 2020-04-25
 */
@Configuration
@EnableWebSecurity
//@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcUserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // 认证管理器，密码模式
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置用户管理服务
        auth.userDetailsService(userDetailService);
    }

    //@Override
    //public void configure(WebSecurity web) throws Exception {
    //    web.ignoring()
    //            .antMatchers("/user/login");
    //}

//    /**
//     * 配置安全拦截机制
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // OPTIONS请求不需要鉴权
//        http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/**")
//                .and()
//                .cors().and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/application/check/", "/csrf", "/doc.html", "/v2/api-docs", "/webjars/**", "/swagger-resources/**",
//                        "/swagger-ui.html", "/oauth/**", "/oauth/authorize").permitAll()
//                // 其他接口无权限限制，只需token
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .formLogin();
//    }
}
