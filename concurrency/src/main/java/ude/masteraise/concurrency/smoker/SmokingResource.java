package ude.masteraise.concurrency.smoker;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This enumeration contains all resources that are traded by the {@linkplain Agent} and are needed by the
 * {@linkplain Smoker}s.
 */
public enum SmokingResource
{
    Tobacco,
    Match,
    Paper;

    private static final int GET = 2;

    /**
     * Returns a set of two random resources.
     *
     * @return the set with random calculated resources
     */
    public static Set<SmokingResource> random()
    {
        int sum = 0;
        Set<SmokingResource> result = new HashSet<>();
        List<SmokingResource> smokingResources = Arrays.stream(values()).collect(Collectors.toList());
        while (sum < GET)
        {
            int random = RandomUtils.nextInt(0, smokingResources.size());
            sum += result.add(smokingResources.remove(random))
                   ? 1
                   : 0;
        }
        return result;
    }
}
