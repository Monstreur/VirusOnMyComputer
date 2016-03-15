package Zones;

import java.util.List;

import autres.CarreArea;

public interface ZoneMarchable {
	
	public List<ZoneMarchable> getZoneMarchables();

	public boolean addZoneMarchable(ZoneMarchable zm);
	
	public CarreArea getCarreArea();
	
	public void setButtonHover(boolean hover);

	public void drawHL();
	
}
