package com.das.das_backend.domain.feed.service;

import com.das.das_backend.domain.feed.domain.repository.FeedRepository;
import com.das.das_backend.domain.feed.presentation.dto.response.QueryFeedListResponse;
import com.das.das_backend.domain.feed.presentation.dto.response.QueryFeedListResponse.FeedResponse;
import com.das.das_backend.domain.user.presentation.dto.response.WriterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryFeedListViewsService {

    private final FeedRepository feedRepository;

    public QueryFeedListResponse execute() {

        List<FeedResponse> feedList = feedRepository.findAllOrderByViewsDesc().stream()
                .map(feed -> FeedResponse.builder()
                        .feedId(feed.getId())
                        .title(feed.getTitle())
                        .createdAt(feed.getCreatedAt())
                        .views(feed.getViews())
                        .likeCounts(feed.getLikeCounts())
                        .writer(WriterResponse.builder()
                                .userId(feed.getUser().getId())
                                .name(feed.getUser().getName())
                                .profileImageUrl(feed.getUser().getProfileImageUrl())
                                .build())
                        .build())
                .collect(Collectors.toList());

        return new QueryFeedListResponse(feedList);
    }

}
