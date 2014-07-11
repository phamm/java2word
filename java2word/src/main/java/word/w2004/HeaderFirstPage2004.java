package word.w2004;

import word.api.interfaces.IElement;
import word.api.interfaces.IHeader;

/**
 * header for first page
 * @company NxTech Corp
 * @author Mai Pham
 * @date Jun 5, 2014
 * @scottsdale 
 * @verion
 */
public class HeaderFirstPage2004 implements IHeader{
	
	StringBuilder txt = new StringBuilder("");
	private boolean hasBeenCalledBefore = false; // if getContent has already been called, I cached the result for future invocations
	private boolean hideHeaderAndFooterFirstPage = false;
	
	/**
	 * This is a different fluent way. 
	 * Because there is no create "with", we will have to create a static method to return an instance of the Header2004. 
	 * * Notice that this class doesn't implement IFluentInterface.
	 * 
	 * */
	public static HeaderFirstPage2004 create() {
		return new HeaderFirstPage2004();
	}
	public void addEle(IElement e) {
		this.txt.append("\n" + e.getContent());
	}

	public void addEle(String str) {
		this.txt.append("\n" + str);		
	}

	public String getContent() {
		if("".equals(txt.toString())){
			return "";
		}
		if(hasBeenCalledBefore ){
			return txt.toString();	
		}else{
			hasBeenCalledBefore = true;
		}
		
		txt.insert(0, HEADER_TOP);
		txt.append(HEADER_BOTTON);
		
		return txt.toString();
	}
	
	public String getHideHeaderAndFooterFirstPageXml() {
		return HIDE_HEADER__FOOTER_FIRST_PAGE;
	}	

	public boolean getHideHeaderAndFooterFirstPage() {
		return this.hideHeaderAndFooterFirstPage ;
	}

	public void setHideHeaderAndFooterFirstPage(boolean value) {
		this.hideHeaderAndFooterFirstPage = value;
	}

	private static final String HEADER_TOP = "\n\n	<w:hdr w:type=\"first\">";
	private static final String HEADER_BOTTON = "\n	</w:hdr><w:titlePg/> ";
	public static final String HIDE_HEADER__FOOTER_FIRST_PAGE =
		"\n            <w:hdr w:type=\"first\"> "
		+"\n                <w:p wsp:rsidR=\"00005E72\" wsp:rsidRDefault=\"00005E72\"> "
		+"\n                    <w:pPr> "
		+"\n                        <w:pStyle w:val=\"Header\"/> "
		+"\n                    </w:pPr> "
		+"\n                </w:p> "
		+"\n            </w:hdr> "
		+"\n            <w:ftr w:type=\"first\"> "
		+"\n                <w:p wsp:rsidR=\"00005E72\" wsp:rsidRDefault=\"00005E72\"> "
		+"\n                    <w:pPr> "
		+"\n                        <w:pStyle w:val=\"Footer\"/> "
		+"\n                    </w:pPr> "
		+"\n                </w:p> "
		+"\n            </w:ftr> "
		+"\n            <w:pgSz w:w=\"11900\" w:h=\"16840\"/> "
		+"\n            <w:pgMar w:top=\"629\" w:right=\"2517\" w:bottom=\"1259\" w:left=\"1888\" w:header=\"709\" w:footer=\"709\" w:gutter=\"0\"/> "
		+"\n            <w:cols w:space=\"708\"/> "
		+"\n            <w:titlePg/> "		
		;


}
