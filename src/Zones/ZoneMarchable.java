package Zones;

import java.util.List;

public interface ZoneMarchable {
	
	public List<ZoneMarchable> getZoneMarchables();

	public boolean addZoneMarchable(ZoneMarchable zm);
	
}
