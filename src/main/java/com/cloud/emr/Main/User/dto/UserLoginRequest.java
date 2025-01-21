package com.cloud.emr.Main.User.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserLoginRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    String userLoginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    String userPassword;

}
