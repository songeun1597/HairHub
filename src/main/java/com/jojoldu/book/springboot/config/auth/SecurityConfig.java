package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor  //final 필드와 @NonNull이 붙은 필드에 대해 자동으로 생성자를 생성
@EnableWebSecurity  //Spring Security의 보안 기능을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //Spring Security에서 HTTP 보안 설정을 정의하는 메서드
        http
                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable함.  -- .csrf().disable().headers().frameOptions().disable()
                .csrf().disable()  //CSRF(Cross-Site Request Forgery) 공격으로부터 보호하기 위한 설정을 비활성화
                                   // CSRF 보호를 비활성화하면, 클라이언트에서 CSRF 토큰을 보내지 않아도 됨
                .headers().frameOptions().disable()  //X-Frame-Options 헤더를 비활성화
                                                     //X-Frame-Options 헤더: 웹 페이지를 <iframe>, <frame>, 또는 <object> 요소에 포함시키는 것을 방지하여 클릭재킹 공격을 방지
                                                     //H2 데이터베이스 콘솔과 같은 도구를 <iframe>으로 로드할 수 있음

                //URL별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되어야만 antMatchers옵션을 사용할 수 있음
                .and()
                    .authorizeRequests()  //HTTP 요청에 대한 권한 부여 설정을 정의

                //권한 관리 대상을 지정하는 옵션
                    .antMatchers("/", "/designer/**",  "/desiner/**", "/salon/**", "/css/**", "/main/**", "/images/**", "/js/**", "/h2-console/**", "/designerList/**", "/reviewList", "/salonList").permitAll()  //특정 URL 패턴에 대한 요청을 인증 없이 접근할 수 있도록 허용(permitAll()옵션을 통해 전체 열람 권한부여)
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())  ///api/v1/**로 시작하는 모든 경로에 대해 USER 역할을 가진 사용자만 접근할 수 있도록 설정

                //설정된 값을 이외의 나머지 URL, authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/main")  //로그아웃 성공 후 리디렉션할 URL을 설정
                .and()
                    .oauth2Login()  //OAuth2 로그인 기능을 활성화
                        .userInfoEndpoint()  //로그인 성공 이후 사용자 정보를 가져올때 설정 담당
                            .userService(customOAuth2UserService);  //사용자 정보를 처리할 인터페이스 구현체(customOAuth2UserService)를 등록
    }
}
