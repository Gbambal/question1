
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public static int numberOfTokens(int expiryLimit, List<List<Integer>> commands) {

        Map<Integer, Integer> tokenExpiry = new HashMap<>();
        int maxTime = 0;

        for (List<Integer> cmd : commands) {
            int type = cmd.get(0);
            int tokenId = cmd.get(1);
            int T = cmd.get(2);

            // track maximum time seen
            maxTime = Math.max(maxTime, T);

            if (type == 0) {
                // Create token
                tokenExpiry.put(tokenId, T + expiryLimit);

            } else if (type == 1) {
                // Reset only if token exists & not expired
                if (tokenExpiry.containsKey(tokenId) && tokenExpiry.get(tokenId) >= T) {
                    tokenExpiry.put(tokenId, T + expiryLimit);
                }
                // else ignore
            }
        }

        // Count tokens still active at maxTime
        int activeCount = 0;
        for (int expiry : tokenExpiry.values()) {
            if (expiry > maxTime) {
                activeCount++;
            }
        }

        return activeCount;
    }
}
