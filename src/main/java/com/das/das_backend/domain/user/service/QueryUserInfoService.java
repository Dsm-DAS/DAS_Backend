package com.das.das_backend.domain.user.service;

import com.das.das_backend.domain.user.domain.User;
import com.das.das_backend.domain.user.facade.UserFacade;
import com.das.das_backend.domain.user.presentation.dto.response.QueryMyInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryUserInfoService {

    private final UserFacade userFacade;

    @Transactional
    public QueryMyInfoResponse execute(Integer userId) {

        User user = userFacade.getUserById(userId);
        user.addViews();

        return QueryMyInfoResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .grade(user.getGrade())
                .classNum(user.getClassNum())
                .introduce(user.getIntroduce())
                .number(user.getNumber())
                .profileImageUrl(user.getProfileImageUrl())
                .viewCounts(user.getViewCounts())
                .build();
    }

}
