package InterviewProblems.RateLimiter.factory;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import InterviewProblems.RateLimiter.enums.RateLimitType;
import InterviewProblems.RateLimiter.models.RateLimitConfig;

public class TokenBucketRateLimiter extends RateLimiter {
    private final ConcurrentHashMap<String, Integer> tokens;
    private final HashMap<String, Long> lastRefillTime;

    public TokenBucketRateLimiter(RateLimitConfig config) {
        super(config, RateLimitType.TOKEN_BUCKET);
        tokens = new ConcurrentHashMap<>();
        lastRefillTime = new HashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis();

        tokens.compute(userId, (id, availableTokens) -> {
            int currentTokens = refillTokens(userId, now);

            if (currentTokens > 0) {
                allowed.set(true);
                return currentTokens - 1;
            }
            else return currentTokens;
        });
        
        return allowed.get();
    }

    private int refillTokens(String userId, long now) {
        double refillRate = (double) config.getWindowInSeconds() / config.getMaxRequests();

        long lastRefill = lastRefillTime.getOrDefault(userId, now);
        long elapsedSeconds = (now - lastRefill) / 1000;
        int refillTokens = (int) (elapsedSeconds / refillRate);

        int currentTokens = tokens.getOrDefault(userId, config.getMaxRequests());
        currentTokens = Math.min(config.getMaxRequests(), currentTokens + refillTokens);

        if (currentTokens > 0) {
            lastRefillTime.put(userId,now);
        }
        return currentTokens;
    }
}
