package com.security.service;

import com.security.domain.Member;
import com.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findById(memberId)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾을 수 없습니다."));
    }

    /*
     * 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
     * 여기서 PasswordEncoder를 통해 UserDetails 객체를 생성할 때 encoding을 해줬다.
     * 그 이유는 Spring Security는 사용자 검증을 위해 encoding된 password와 그렇지 않은 password를 비교하기 때문이다.
     * 실제로는 DB 자체에 encoding된 password 값을 갖고 있고 그냥 member.getPassword()로 encoding된 password를 꺼내는 것이 좋지만
     * 예제에서는 편의를 위해 검증 객체를 생성할 때 encoding을 해줬다.
     */
    private UserDetails createUserDetails(Member member){
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().split(","))
                .build();
    }
}
