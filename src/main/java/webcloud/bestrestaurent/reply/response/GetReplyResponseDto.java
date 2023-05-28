package webcloud.bestrestaurent.reply.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import webcloud.bestrestaurent.reply.domain.Reply;

@Getter
@Setter
public class GetReplyResponseDto {

    private Long replyId;
    private Long storeId;
    private String content;
    private String userName; // 댓글을 생성한 user의 닉네임
    private Long userId; // 댓글을 생성한 user의 id

    public GetReplyResponseDto(Reply reply){
        this.replyId = reply.getReplyId();
        this.storeId = reply.getStoreId();
        this.content = reply.getContent();
        this.userName = reply.getUser().getUsername();
        this.userId = reply.getUser().getUserId();
    }

    @Builder
    public GetReplyResponseDto(Long replyId, Long storeId, String content, String userName, Long userId){
        this.replyId = replyId;
        this.storeId = storeId;
        this.content = content;
        this.userName = userName;
        this.userId = userId;
    }
}
