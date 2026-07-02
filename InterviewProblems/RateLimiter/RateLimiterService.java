package InterviewProblems.RateLimiter;

import java.util.HashMap;

import InterviewProblems.RateLimiter.enums.RateLimitType;
import InterviewProblems.RateLimiter.enums.UserTier;
import InterviewProblems.RateLimiter.factory.RateLimiter;
import InterviewProblems.RateLimiter.factory.RateLimiterFactory;
import InterviewProblems.RateLimiter.models.RateLimitConfig;
import InterviewProblems.RateLimiter.models.User;

public class RateLimiterService {
    private final HashMap<UserTier, RateLimiter> rateLimiters;

    public RateLimiterService() {
        rateLimiters = new HashMap<>();

        rateLimiters.put(
            UserTier.FREE, 
            RateLimiterFactory.createRateLimiter(
                RateLimitType.TOKEN_BUCKET, 
                new RateLimitConfig(10, 60)
            )
        );

        rateLimiters.put(
            UserTier.PREMIUM, 
            RateLimiterFactory.createRateLimiter(
                RateLimitType.FIXED_WINDOW, 
                new RateLimitConfig(100, 60)
            )
        );
    }

    public boolean allowRequest(User user) {
        RateLimiter limiter = rateLimiters.get(user.getTier());
        if (limiter == null) {
            throw new IllegalArgumentException("No limiter configured for tier.");
        }
        return limiter.allowRequest(user.getUserId());
    }
}
