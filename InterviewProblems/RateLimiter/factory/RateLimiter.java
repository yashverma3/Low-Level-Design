package InterviewProblems.RateLimiter.factory;

import InterviewProblems.RateLimiter.enums.RateLimitType;
import InterviewProblems.RateLimiter.models.RateLimitConfig;

public abstract class RateLimiter {
    protected final RateLimitConfig config;
    protected final RateLimitType type;

    public RateLimiter(RateLimitConfig config, RateLimitType type) {
        this.config = config;
        this.type = type;
    }

    public abstract boolean allowRequest(String userId);
}
