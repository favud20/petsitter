package com.pet.sitter.common.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaNo;

    @Column
    @NotNull
    private String qnaTitle;

    @Column
    @NotNull
    private String qnaContent;

    @Column(name = "qna_date", columnDefinition = "TIMESTAMP")
    @NotNull
    private LocalDateTime qnaDate;

    @Column
    @NotNull
    private String qnaPw;

    @Column
    @NotNull
    private Integer qnaViewCnt;

    @Column
    private String qnaFile;


    @ManyToOne
    @JoinColumn (name = "id", referencedColumnName = "id")
    private Member member;

    @OrderBy("id desc")
    @JsonIgnoreProperties({"question"}) //무한참조방지
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<QuestionFile> QuestionList = new ArrayList<>();

    //조회수증가
    public void increaseViewCount() {
        this.qnaViewCnt++;
    }

    @Builder
    public Question(Long qnaNo, String qnaTitle, String qnaContent, LocalDateTime qnaDate, String qnaPw, Integer qnaViewCnt, String qnaFile, Member member,List<QuestionFile> QuestionList) {
        this.qnaNo = qnaNo;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaDate = qnaDate;
        this.qnaPw = qnaPw;
        this.qnaViewCnt = qnaViewCnt;
        this.qnaFile = qnaFile;
        this.member = member;
        this.QuestionList = QuestionList;
    }


}
