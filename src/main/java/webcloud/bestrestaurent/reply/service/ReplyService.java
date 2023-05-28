package webcloud.bestrestaurent.reply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import webcloud.bestrestaurent.ResponseDto;
import webcloud.bestrestaurent.config.security.JwtAuthenticationProvider;
import webcloud.bestrestaurent.reply.domain.Reply;
import webcloud.bestrestaurent.reply.domain.ReplyRepository;
import webcloud.bestrestaurent.reply.request.DeleteReplyRequestDto;
import webcloud.bestrestaurent.reply.request.ReplyRequestDto;
import webcloud.bestrestaurent.reply.response.GetReplyResponseDto;
import webcloud.bestrestaurent.user.domain.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final UserDetailsService userDetailsService;
    private final ReplyRepository replyRepository;

    public ResponseDto createReply(ServletRequest request, ReplyRequestDto replyRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Reply reply = Reply.builder()
                .storeId(replyRequestDto.getStoreId())
                .content(replyRequestDto.getContent())
                .build();

        reply.insertUser(user);
        replyRepository.save(reply);
        return new ResponseDto("SUCCESS", reply.getReplyId());

    }

    public ResponseDto getReplies(Long storeId, Pageable pageable){
        List<GetReplyResponseDto> pages = replyRepository.findByStoreId(storeId, pageable)
                .stream()
                .map(reply -> new GetReplyResponseDto(reply))
                .collect(Collectors.toList());

        return new ResponseDto("SUCCESS",pages);
    }

    @Transactional
    public ResponseDto deleteReply(ServletRequest request, DeleteReplyRequestDto deleteReplyRequestDto) {
        String token = jwtAuthenticationProvider.resolveToken((HttpServletRequest) request);
        User user = (User) userDetailsService.loadUserByUsername(jwtAuthenticationProvider.getUserPk(token));

        Reply reply = replyRepository.findById(deleteReplyRequestDto.getReplyId())
                .orElseThrow(()-> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        if(replyRepository.existsByUserAndStoreId(user,deleteReplyRequestDto.getStoreId())){
            // 해당 가게에서 AccessToken을 보낸 유저의 댓글이여야 함
            replyRepository.delete(reply);
            return new ResponseDto("SUCCESS", "댓글 삭제 완료!");
        }
        else{
            return new ResponseDto("FAIL", "댓글 삭제 실패, 유저를 확인해주세요");
        }
    }

}
