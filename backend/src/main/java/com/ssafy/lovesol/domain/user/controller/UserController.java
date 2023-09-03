package com.ssafy.lovesol.domain.user.controller;

import com.ssafy.lovesol.domain.user.dto.request.CreateUserAccountRequestDto;
import com.ssafy.lovesol.domain.user.dto.request.LoginRequestDto;
import com.ssafy.lovesol.domain.user.dto.request.UpdateUserAccountInfoDto;
import com.ssafy.lovesol.domain.user.dto.response.UserResponseDto;
import com.ssafy.lovesol.domain.user.entity.User;
import com.ssafy.lovesol.domain.user.service.UserService;
import com.ssafy.lovesol.global.response.ResponseResult;
import com.ssafy.lovesol.global.response.SingleResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "응답이 성공적으로 반환되었습니다."),
        @ApiResponse(responseCode = "400", description = "응답이 실패하였습니다.",
                content = @Content(schema = @Schema(implementation = ResponseResult.class)))})
@Tag(name = "User Controller", description = "유저 컨트롤러")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Sign Up", description = "사용자가 회원가입 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공")
    })
    @PostMapping("/signup")
    public ResponseResult createUserAccount(
            @Valid @RequestBody CreateUserAccountRequestDto createUserAccountRequestDto) {
        log.info("UserController_createUserAccount -> 사용자의 회원가입");
        if (userService.createUserAccount(createUserAccountRequestDto) >= 0) {
            return ResponseResult.successResponse;
        }
        return ResponseResult.failResponse;
    }

    @Operation(summary = "Login", description = "사용자가 로그인 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공")
    })
    @PostMapping("/login")
    public ResponseResult login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        log.info("UserController_login -> 로그인 시도");
        userService.login(loginRequestDto, response);
        return ResponseResult.successResponse;
    }


    @Operation(summary = "Deposit", description = "사용자가 자동 입금 날짜 및 금액을 설정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description ="자동 입금 설정 성공")
    })


    @PutMapping("/account")
    public ResponseResult setDeposit(@Valid @RequestBody UpdateUserAccountInfoDto updateUserAccountInfoDto){
        log.info("UserController_Deposit -> 자동 입금 정보 설정");
        userService.UpdateDepositInfo(updateUserAccountInfoDto);
        return ResponseResult.successResponse;
    }

    @GetMapping("/account/{id}")
    public SingleResponseResult<UserResponseDto> setDeposit(@PathVariable String id){
        log.info("UserController_Deposit -> 자동 입금 정보 조회");
        User user = userService.getUserById(id);
        return new SingleResponseResult<UserResponseDto>(
                UserResponseDto.builder()
                        .id(user.getId())
                        .personalAccount(user.getPersonalAccount())
                        .name(user.getName())
                        .phoneNumber(user.getPhoneNumber())
                        .birthAt(user.getBirthAt())
                        .amount(user.getAmount())
                        .depositAt(user.getDepositAt())
                        .build()
        );
    }

}
