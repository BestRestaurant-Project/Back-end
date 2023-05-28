package webcloud.bestrestaurent.reply.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeleteReplyRequestDto {
    private Long storeId;
    private Long replyId;
}
