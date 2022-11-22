package com.das.das_backend.domain.feed.domain;

import com.das.das_backend.domain.comment.domain.Comment;
import com.das.das_backend.domain.user.domain.User;
import com.das.das_backend.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Integer id;

    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    @Size(max = 1000)
    private String content;

    @NotNull
    @Size(max = 500)
    private String dasUrl;

    @Column(nullable = false)
    private Integer views;

    @Column(nullable = false)
    private Integer likeCounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @Builder
    public Feed(String title, String content, String dasUrl, Integer views, Integer likeCounts, User user) {
        this.title = title;
        this.content = content;
        this.dasUrl = dasUrl;
        this.views = views;
        this.likeCounts = likeCounts;
        this.user = user;
    }

    public void modifyFeed(String title, String content, String dasUrl) {
        this.title = title;
        this.content = content;
        this.dasUrl = dasUrl;
    }

    public void addLikeCount() {
        this.likeCounts += 1;
    }

    public void subLikeCount() {
        this.likeCounts -= 1;
    }

    public void addViews() {
        this.views += 1;
    }

}
