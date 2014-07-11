package word.w2004;

import word.api.interfaces.IFooter;
import word.api.interfaces.IHasElement;
import word.api.interfaces.IHeader;

/**
 * 
 * @company NxTech Corp
 * @author Mai Pham
 * @date Jun 5, 2014
 * @scottsdale 
 * @verion
 */
public interface ISection extends IHasElement{

	public abstract IHeader getHeaderFirstPage();

	public abstract IFooter getFooter();

	public abstract IHeader getHeader();
	
	public abstract void setPageOrientationLandscape() ;

	public abstract void setMargin(String top, String right, String bottom, String left, String header, String footer, String gutter);

}