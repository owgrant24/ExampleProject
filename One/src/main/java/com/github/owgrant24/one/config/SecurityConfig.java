package com.github.owgrant24.one.config;

import com.github.owgrant24.one.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@ComponentScan("com.github.owgrant24.one.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;

    @Override
    // Правила (ограничения прав)
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Указываем URL которые будут доступны только анонимным (неавторизованным) пользователям
                .antMatchers("/sign_up", "/login").anonymous()
                // Указываем URL которые будут доступны только авторизованным пользователям
                .antMatchers("/cars/**").authenticated()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
                .antMatchers("/resources/**").permitAll()
                .and().csrf().disable()
                .formLogin()
                // Указываем где у нас форма логина
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                // задаем что будет будет в качестве username
                .usernameParameter("email")
                // задаем что будет будет в качестве password в форме (по умолчанию - password)
                .passwordParameter("password")
                // При неудачной авторизации сюда будет редирект
                .failureUrl("/login?error=true")
                // Когда пользователь прошёл аутентификацию и хочет зайти на странички для не авторизированных
                // пользователей
                .and().exceptionHandling().accessDeniedPage("/cars")
                .and()
                // добавлена возможность logout
                .logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
