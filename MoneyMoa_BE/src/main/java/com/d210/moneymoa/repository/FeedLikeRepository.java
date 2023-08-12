package com.d210.moneymoa.repository;


import com.d210.moneymoa.dto.Feed;
import com.d210.moneymoa.dto.FeedLike;
import com.d210.moneymoa.dto.Member;
//import com.d210.moneymoa.dto.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {


    Optional<FeedLike> findByMemberIdAndFeedId(Long memberId,Long feedId);
}
