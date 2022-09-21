package com.das.das_backend.domain.user.presentation;

import com.das.das_backend.domain.user.presentation.dto.request.UserSignInRequest;
import com.das.das_backend.domain.user.presentation.dto.request.UserSignUpRequest;
import com.das.das_backend.domain.user.presentation.dto.response.TokenResponse;
import com.das.das_backend.domain.user.service.UserSignInService;
import com.das.das_backend.domain.user.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserSignInService userSignInService;
    private final UserSignUpService userSignUpService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid UserSignUpRequest request) {
        userSignUpService.execute(request);
    }

    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid UserSignInRequest request) {
        return userSignInService.execute(request);
    }

}
