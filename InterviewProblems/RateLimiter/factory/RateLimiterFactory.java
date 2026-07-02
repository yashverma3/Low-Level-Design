package InterviewProblems.RateLimiter.factory;

import InterviewProblems.RateLimiter.enums.RateLimitType;
import InterviewProblems.RateLimiter.models.RateLimitConfig;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimitType type, RateLimitConfig config) {
        switch(type) {
            case TOKEN_BUCKET: return new TokenBucketRateLimiter(config);
            case FIXED_WINDOW: return new FixedWindowRateLimiter(config);
            case SLIDING_WINDOW_LOG: return new SlidingWindowLogRateLimiter(config);
            default: throw new IllegalArgumentException("Unknown Algorithm");
        }
    }
}
