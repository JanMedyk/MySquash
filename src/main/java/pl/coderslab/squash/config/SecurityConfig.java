package pl.coderslab.squash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.coderslab.squash.User.service.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/app/**").authenticated()
                .and()


//                .anyRequest().authenticated()

                .formLogin()
                .loginPage("/login")
                .permitAll().failureUrl("/login?error=true")
                .defaultSuccessUrl("/app/home")
                .usernameParameter("userName")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")

        .deleteCookies("JSESSIONID");
//                .logout(logout ->logout
//                        .permitAll()
//                        .deleteCookies("JSESSIONID")
//                        .addLogoutHandler(new SecurityContextLogoutHandler())
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                );


//                .authorizeRequests()
//                .antMatchers("/")
//                .permitAll().
//                antMatchers("/app/**").authenticated().
//                and().
//                formLogin().
//        loginPage("/login").permitAll();

    }
}
