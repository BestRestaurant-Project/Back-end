package webcloud.bestrestaurent.reply.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import webcloud.bestrestaurent.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long replyId;

    //@ManyToOne(fetch = FetchType.LAZY,targetEntity = Post.class)
    //@JoinColumn(name = "post_id")
    @Column(name = "store_id")
    private Long storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "content")
    private String content;

    @Builder
    public Reply(Long storeId, String content){
        this.storeId = storeId;
        this.content = content;
    }

    public void insertUser(User user) {
        this.user = user;
    }
}
