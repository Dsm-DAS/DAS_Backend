package com.das.das_backend.domain.feed.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class FeedDetailResponse {

    private final Integer feedId;
    private final String title;
    private final String content;
    private final String dasUrl;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Integer views;
    private final Integer likeCounts;
    private final boolean liked;
    private final Writer writer;
    private final List<CommentResponse> commentList;

    @Getter
    @Builder
    public static class CommentResponse {
        private final Integer commentId;
        private final String content;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;
        private final Writer writer;
    }

    @Getter
    @Builder
    public static class Writer {
        private final Integer userId;
        private final String name;
        private final String profileImageUrl;
    }

}
