package InterviewProblems.RateLimiter.models;

import InterviewProblems.RateLimiter.enums.UserTier;

public class User {
    private final String userId;
    private final UserTier tier;

    public User(String userId, UserTier tier) {
        this.userId = userId;
        this.tier = tier;
    }

    public String getUserId() { return userId; }
    public UserTier getTier() { return tier; }
}
