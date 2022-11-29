package com.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class TokenInfo {

    private String grantType; // JWT 대한 인증 타입 - Bearer 사용, 이후 HTTP 헤더에 prefix로 붙여주는 타입
    private String accessToken;
    private String refreshToken;

}
