package webcloud.bestrestaurent.reply.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import webcloud.bestrestaurent.user.domain.User;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    List<Reply> findByStoreId(Long postId, Pageable pageable);
    boolean existsByUserAndStoreId(User user,Long storeId);
}
