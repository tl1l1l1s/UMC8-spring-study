package umc._th.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc._th.spring.domain.Review;
import umc._th.spring.domain.enums.MissionStatus;
import umc._th.spring.service.MemberService.MemberQueryService;
import umc._th.spring.service.MissionService.MissionQueryService;
import umc._th.spring.service.ReviewService.ReviewCommandService;
import umc._th.spring.service.StoreService.StoreQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			// 상점 찾는 쿼리
			StoreQueryService storeService = context.getBean(StoreQueryService.class);

			String name = "요아정";
			Float score = 4.0f;

			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

			storeService.findStoreByNameAndScore(name, score)
					.forEach(System.out::println);

			MissionQueryService missionService = context.getBean(MissionQueryService.class);

			Long memberId = 1L;
			MissionStatus missionStatus = MissionStatus.CHALLENGING;

			missionService.findAllMissionsByMemberIdAndStatus(memberId, missionStatus, PageRequest.of(1, 10))
					.forEach(System.out::println);

			MemberQueryService memberService = context.getBean(MemberQueryService.class);
			memberService.findById(memberId).ifPresent(System.out::println);

			missionService.findAllMissionsByRegion(1L, 1L, PageRequest.of(1, 10))
					.forEach(System.out::println);

			ReviewCommandService reviewService = context.getBean(ReviewCommandService.class);
			Review writtenReview = reviewService.createReview(memberId, 1L, "리뷰 작성 확인", 5F);
			System.out.println(writtenReview);
		};
	}

}
