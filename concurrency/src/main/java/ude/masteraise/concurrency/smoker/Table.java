package ude.masteraise.concurrency.smoker;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * A <code>Table</code> can be used by an {@linkplain Agent} and the three {@linkplain Smoker} instances to trade
 * the smokingResources they need to smoke.
 * </p>
 */
public class Table
{
    private Set<SmokingResource> smokingResources;

    public Table()
    {
        this.smokingResources = new HashSet<>();
    }

    public boolean putSmokingResource(SmokingResource smokingResource)
    {
        return smokingResources.add(smokingResource);
    }

    public boolean takeSmokingResource(SmokingResource smokingResource)
    {
        return smokingResources.remove(smokingResource);
    }

    public Set<SmokingResource> getAvailableResources()
    {
        return smokingResources;
    }

    public boolean isEmpty()
    {
        return smokingResources.size() == 0;
    }
}
