package com.springboot.mycgv.config;

import com.springboot.mycgv.model.SessionUser;
import com.springboot.mycgv.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession session;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        //먼저 parameter.getParameterAnnotation(LoginedUser.class)를 통해 LoginedUser 어노테이션을 해당 매개변수에서 찾습니다.
        //이 어노테이션이 존재하면 isLoginedUserAnnotation 변수에 true를 할당하고, 그렇지 않으면 false를 할당합니다.
        boolean isLoginedUserAnnotation = parameter.getParameterAnnotation(LoginedUser.class) != null;

        //parameter.getParameterType()을 사용하여 매개변수의 타입을 가져온 다음,
        //이 타입이 UserSessionDto.class와 같은지 확인합니다.
        //만약 해당되면 isUserClass 변수에 true를 할당하고, 그렇지 않으면 false를 할당합니다.
//        boolean isUserClass = UserSessionDto.class.equals(parameter.getParameterType());

        boolean isUserClass = MemberSecurityDTO.class.equals(parameter.getParameterType());

        return isLoginedUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        //현재 요청과 관련된 HttpServletRequest 객체를 가져옵니다.
        //webRequest 객체는 Spring이 제공하는 NativeWebRequest 인터페이스의 구현체로,
        //HTTP 요청과 관련된 정보에 접근할 수 있게 해줍니다.
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        HttpSession session = request.getSession(false); //이미 세션이 존재하면 기존 세션을 반환하고, 세션이 존재하지 않으면 null을 반환
        if (session == null) {
            return null;
        }

        return session.getAttribute(SessionUser.LOGIN_USER);
    }
}
