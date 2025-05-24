package umc._th.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc._th.spring.apiPayload.code.status.ErrorStatus;
import umc._th.spring.apiPayload.exception.handler.MemberHandler;
import umc._th.spring.apiPayload.exception.handler.StoreHandler;
import umc._th.spring.converter.ReviewConverter;
import umc._th.spring.domain.Member;
import umc._th.spring.domain.Review;
import umc._th.spring.domain.Store;
import umc._th.spring.repository.MemberRepository.MemberRepository;
import umc._th.spring.repository.ReviewRepository.ReviewRepository;
import umc._th.spring.repository.StoreRepository.StoreRepository;
import umc._th.spring.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review createReview(Long memberId, Long storeId, ReviewRequestDTO.AddReviewDTO request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(member, store, request);

        return reviewRepository.addReview(review);
    }
}
