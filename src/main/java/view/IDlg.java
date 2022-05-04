package view;

import java.util.Map;

public interface IDlg {
	public Map<String, Object> getMap();
	public void clear();
	public void setVisible(boolean b);
	public void dispose();
}
