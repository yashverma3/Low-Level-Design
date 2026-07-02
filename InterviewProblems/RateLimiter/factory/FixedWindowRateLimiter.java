package InterviewProblems.RateLimiter.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import InterviewProblems.RateLimiter.enums.RateLimitType;
import InterviewProblems.RateLimiter.models.RateLimitConfig;

public class FixedWindowRateLimiter extends RateLimiter {
    private final Map<String, Integer> requestCount;
    private final Map<String, Long> windowStart;

    public FixedWindowRateLimiter(RateLimitConfig config) {
        super(config, RateLimitType.FIXED_WINDOW);
        requestCount = new ConcurrentHashMap<>();
        windowStart = new HashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        
        long currentReqWindow = System.currentTimeMillis() / 1000 /config.getWindowInSeconds();

        requestCount.compute(userId, (id, count) -> {
            long lastReqWindow = windowStart.getOrDefault(userId, currentReqWindow);

            if (lastReqWindow != currentReqWindow) {
                windowStart.put(id, currentReqWindow);
                allowed.set(true);
                return 1;
            }

            if (count == null) {
                count = 0;
            }

            if (count < config.getMaxRequests()) {
                allowed.set(true);
                return count + 1;
            }

            return count;
        });

        return allowed.get();
    }
}
