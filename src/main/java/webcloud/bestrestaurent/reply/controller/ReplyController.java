package webcloud.bestrestaurent.reply.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.reply.request.DeleteReplyRequestDto;
import webcloud.bestrestaurent.reply.request.ReplyRequestDto;
import webcloud.bestrestaurent.reply.service.ReplyService;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply/create")
    public ResponseDto createReply(ServletRequest request, @Valid @RequestBody ReplyRequestDto replyRequestDto){
        return replyService.createReply(request, replyRequestDto);
    }
    @GetMapping("/replies/{storeId}")
    public ResponseDto getReplies(@PathVariable Long storeId,
                                @PageableDefault(size=20, sort="storeId", direction= Sort.Direction.DESC) Pageable pageable) {
        return replyService.getReplies(storeId, pageable);
    }
    @DeleteMapping("/reply/delete")
    public ResponseDto deleteReply(ServletRequest request, @Valid @RequestBody DeleteReplyRequestDto deleteReplyRequestDto){
        return replyService.deleteReply(request, deleteReplyRequestDto);
    }

}
