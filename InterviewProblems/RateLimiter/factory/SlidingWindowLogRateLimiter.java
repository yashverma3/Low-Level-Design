package InterviewProblems.RateLimiter.factory;

import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import InterviewProblems.RateLimiter.enums.RateLimitType;
import InterviewProblems.RateLimiter.models.RateLimitConfig;

public class SlidingWindowLogRateLimiter extends RateLimiter {
    private final ConcurrentHashMap<String, ArrayDeque<Long>> requestLog;

    public SlidingWindowLogRateLimiter(RateLimitConfig config) {
        super(config, RateLimitType.SLIDING_WINDOW_LOG);
        requestLog = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis() / 1000;

        requestLog.compute(userId, (id, log) -> {
            if (log == null) log = new ArrayDeque<>();

            while(!log.isEmpty() && (now-log.peek()) >= config.getWindowInSeconds()) {
                log.poll();
            }

            if (log.size() < config.getMaxRequests()) {
                log.add(now);
                allowed.set(true);
            }

            return log;
        });

        return allowed.get();
    }
}
