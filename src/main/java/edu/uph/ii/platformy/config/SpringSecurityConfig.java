package edu.uph.ii.platformy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;


@EnableWebSecurity
@EnableGlobalMethodSecurity (securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{


        @Bean
        @Profile (ProfileNames.INMEMORY)
        public UserDetailsService userDetailsService ( PasswordEncoder passwordEncoder )
        {
                InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
                User.UserBuilder userBuilder = User.builder();

                manager.createUser( userBuilder.username( "user" ).password( passwordEncoder.encode( "user" ) ).roles( "USER" ).build() );
                manager.createUser( userBuilder.username( "admin" ).password( passwordEncoder.encode( "admin" ) ).roles( "ADMIN" ).build() );
                manager.createUser( userBuilder.username( "test" ).password( passwordEncoder.encode( "test" ) ).roles( "USER", "ADMIN" ).build() );

                return manager;
        }


    /*
        @Override
        public void configure ( AuthenticationManagerBuilder auth ) throws Exception
        {
                PasswordEncoder encoder = passwordEncoder();
                auth
                        .inMemoryAuthentication()
                        .passwordEncoder( encoder )
                        .withUser( "user" ).password( encoder.encode( "user" ) ).roles( "USER" )
                        .and()
                        .withUser( "admin" ).password( encoder.encode( "admin" ) ).roles( "ADMIN" )
                        .and()
                        .withUser( "oba" ).password( encoder.encode( "oba" ) ).roles( "USER", "ADMIN" );
        }
*/


        @Bean
        public PasswordEncoder passwordEncoder ()
        {
                return new BCryptPasswordEncoder();
        }


        @Override
        protected void configure ( HttpSecurity http ) throws Exception
        {
                http
                        .authorizeRequests()
                        .antMatchers( "/statics/**", "/webjars/**", "/" ).permitAll()
                        .antMatchers( "vehicle/list" ).permitAll();
                http
                        .formLogin()
                        .loginPage( "/login" )
                        .permitAll();
                http
                        .logout()
                        .permitAll();

                http.exceptionHandling().accessDeniedHandler( createAccessDeniedHandler() );
        }


        private AccessDeniedHandler createAccessDeniedHandler ()
        {
                AccessDeniedHandlerImpl impl = new AccessDeniedHandlerImpl();
                impl.setErrorPage( "/accessDenied" );
                return impl;
        }
}


