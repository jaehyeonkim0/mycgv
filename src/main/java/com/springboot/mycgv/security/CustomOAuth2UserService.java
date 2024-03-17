package com.springboot.mycgv.security;

import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import com.springboot.mycgv.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    /**
     * loadUser() 메서드가 호출될 때는 이미 사용자 정보를 가져오기 위한 인증 및 권한 부여가 완료된 상태
     * @param userRequest the user request
     * @return
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest = {}", userRequest);
        log.info("액세스 토큰 = {}",userRequest.getAccessToken().getTokenValue());
        log.info("userNameAttributeName = {}",userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName());

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("client Name = {}", clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;
        String name = null;
        String pnumber = null;

        switch (clientName){
            case "kakao":
                email = getKakaoInfo(paramMap).get("email");
                name = getKakaoInfo(paramMap).get("name");
                break;
            case "Naver":
                pnumber = getNaverInfo(paramMap).get("pnumber");
                email = getNaverInfo(paramMap).get("email");
                name = getNaverInfo(paramMap).get("name");
                break;
            case "google":
                log.info("google Oauth 로그인");
                break;
        }

        return generateDTO(email, name, pnumber, paramMap);
    }


    private MemberSecurityDTO generateDTO(String email, String name, String pnumber, Map<String, Object> params){

        Optional<Member> result = memberRepository.getWithRoles(email);

        //데이터베이스에 해당 이메일을 사용자가 없다면
        if(result.isEmpty()){
//            Member member = Member.builder()
//                    .id("guest")
//                    .password(bCryptPasswordEncoder.encode("123"))
//                    .email(email)
//                    .name(name)
//                    .social(true)
//                    .build();
//            member.addRole(MemberRole.GUEST);
//            memberRepository.save(member);

            //MemberSecurityDTO 구성 및 반환
            MemberSecurityDTO memberSecurityDTO =
                    new MemberSecurityDTO("guest", "123", email, name, pnumber, false, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_GUEST")));
            memberSecurityDTO.setProps(params);

            return memberSecurityDTO;
        }else {
            Member member = result.get();
            MemberSecurityDTO memberSecurityDTO =
                    new MemberSecurityDTO(
                            member.getId(),
                            member.getPassword(),
                            member.getEmail(),
                            member.getName(),
                            member.getPnumber(),
                            member.isDel(),
                            member.isSocial(),
                            member.getRoleSet()
                                    .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                                    .collect(Collectors.toList())
                    );

            return memberSecurityDTO;
        }
    }

    private Map<String, String> getKakaoInfo(Map<String, Object> paramMap){
        LinkedHashMap accountMap = (LinkedHashMap) paramMap.get("kakao_account");
        LinkedHashMap properties = (LinkedHashMap) paramMap.get("properties");

        String email = (String) accountMap.get("email");
        String name = (String) properties.get("nickname");

        Map<String, String> kakaoInfo = new LinkedHashMap<>();
        kakaoInfo.put("email", email);
        kakaoInfo.put("name", name);

        return kakaoInfo;
    }

    private Map<String, String> getNaverInfo(Map<String, Object> paramMap) {
        Object value = paramMap.get("response");
        LinkedHashMap naverInfo = (LinkedHashMap) value;

        String email = (String) naverInfo.get("email");
        String name = (String) naverInfo.get("name");

        String mobileWithhyphen = (String) naverInfo.get("mobile");
        String pnumber = mobileWithhyphen.replace("-", "");

        Map<String, String> map = Map.of(
                "email", email,
                "name", name,
                "pnumber", pnumber
        );

        return map;
    }


//    private String getKakaoEmail(Map<String, Object> paramMap){
//        log.info("KAKAO EMAIL-----------------------------------------");
//        Object value = paramMap.get("kakao_account");
//
//        log.info(value);
//
//        LinkedHashMap accountMap = (LinkedHashMap) value;
//
//        String email = (String)accountMap.get("email");
//        log.info("email = {}", email);
//
//        return email;
//    }
//
//    private String getKakaoName(Map<String, Object> paramMap){
//        Object value = paramMap.get("properties");
//
//        log.info(value);
//
//        LinkedHashMap accountMap = (LinkedHashMap) value;
//        String name = (String)accountMap.get("nickname");
//
//        return name;
//    }
}
