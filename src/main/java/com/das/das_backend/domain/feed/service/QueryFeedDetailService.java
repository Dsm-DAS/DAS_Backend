package com.das.das_backend.domain.feed.service;

import com.das.das_backend.domain.comment.facade.CommentFacade;
import com.das.das_backend.domain.feed.domain.Feed;
import com.das.das_backend.domain.feed.facade.FeedFacade;
import com.das.das_backend.domain.feed.presentation.dto.response.QueryFeedDetailResponse;
import com.das.das_backend.domain.like.facade.LikeFacade;
import com.das.das_backend.domain.user.domain.User;
import com.das.das_backend.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryFeedDetailService {

    private final FeedFacade feedFacade;
    private final UserFacade userFacade;
    private final LikeFacade likeFacade;
    private final CommentFacade commentFacade;

    @Transactional
    public QueryFeedDetailResponse execute(Integer feedId) {

        User user = userFacade.getCurrentUser();

        Feed feed = feedFacade.getFeedById(feedId);
        User writer = feed.getUser();

        feed.addViews();

        return QueryFeedDetailResponse.builder()
                .feedId(feedId)
                .title(feed.getTitle())
                .content(feed.getContent())
                .dasUrl(feed.getDasUrl())
                .views(feed.getViews())
                .likeCounts(feed.getLikeCounts())
                .liked(likeFacade.checkLiked(user, feed))
                .writer(userFacade.getWriter(writer))
                .commentList(commentFacade.getComments(feed))
                .build();
    }

}
