package com.puppydog.mall.form;
import javax.validation.constraints.NotEmpty;

import com.puppydog.mall.dd.domain.Member;

import lombok.Data;

@Data
public class MemberForm {

    @NotEmpty
    private String id;

    @NotEmpty
    private String upw;

    @NotEmpty
    private String nickName;
    @NotEmpty
    private String address;

    public Member toEntity(){
        return Member.builder().id(id)
        .upw(upw)
        .nickName(nickName)
        .address(address)
        .build();
    }
}