package word.w2004;

import word.api.interfaces.IBody;
import word.api.interfaces.IElement;
import word.api.interfaces.IFooter;
import word.api.interfaces.IHeader;

public class Body2004 implements IBody {

	StringBuilder txt = new StringBuilder("");
	IHeader header = new Header2004();
	IHeader headerFirstPage;
	IFooter footer = new Footer2004();

	private boolean isLandscape = false;

	private boolean defaultMargin = true;
	private String margingTop = null;
	private String marginBottom = null;
	private String marginLeft = null;
	private String marginHeader = null;
	private String marginRight = null;
	private String marginFooter = null;
	private String marginGutter = null;
	
	@Override
    public void setPageOrientationLandscape() {
        isLandscape = true;
    }
	
    /**
     * set margin page
     * @param top
     * @param right
     * @param bottom
     * @param left
     * @param header
     * @param footer
     * @param gutter
     */
    public void setMargin(String top, String right, String bottom, String left, String header, String footer, String gutter){
    	defaultMargin = false;
    	margingTop = top;
    	marginRight = right;
    	marginBottom = bottom;
    	marginLeft = left;
    	marginHeader = header;
    	marginFooter = footer;
    	marginGutter = gutter;
    }

	public void addEle(IElement e) {
		this.txt.append("\n" + e.getContent());
	}

	public void addEle(String str) {
		this.txt.append("\n" + str);
	}

	public String getContent() {
		StringBuilder res = new StringBuilder();
		res.append("\n<w:body>");

		res.append(txt.toString());

		String header = this.getHeader().getContent();
		String footer = this.getFooter().getContent();
		if (!"".equals(header) || !"".equals(footer)) {
			String header_footer_top = "<w:sectPr wsp:rsidR=\"00DB1FE5\" wsp:rsidSect=\"00471A86\">";
			if(isLandscape) {
				header_footer_top += "      <w:pgSz w:w=\"16834\" w:h=\"11904\" w:orient=\"landscape\"/>\n";
	            if(defaultMargin)
	            	header_footer_top += "      <w:pgMar w:top=\"720\" w:right=\"720\" w:bottom=\"720\" w:left=\"720\" w:header=\"708\" w:footer=\"708\" w:gutter=\"0\"/>\n";
	            else
	            	header_footer_top += "      <w:pgMar w:top=\"" + margingTop + "\" w:right=\"" + marginRight + "\" w:bottom=\"" + marginBottom + "\" w:left=\"" + marginLeft + "\" w:header=\"" + marginHeader + "\" w:footer=\"" +marginFooter+ "\" w:gutter=\"" + marginGutter + "\"/>\n";
	            header_footer_top += "      <w:cols w:space=\"708\"/>\n";
	        }else{
	        	header_footer_top   += "      <w:pgSz w:w=\"11904\" w:h=\"16834\" w:orient=\"portrait\"/>\n";
	            if(defaultMargin)
	            	header_footer_top += "      <w:pgMar w:top=\"720\" w:right=\"720\" w:bottom=\"720\" w:left=\"720\" w:header=\"708\" w:footer=\"708\" w:gutter=\"0\"/>\n";
	            else
	            	header_footer_top += "      <w:pgMar w:top=\"" + margingTop + "\" w:right=\"" + marginRight + "\" w:bottom=\"" + marginBottom + "\" w:left=\"" + marginLeft + "\" w:header=\"" + marginHeader + "\" w:footer=\"" +marginFooter+ "\" w:gutter=\"" + marginGutter + "\"/>\n";
	            header_footer_top += "      <w:cols w:space=\"708\"/>\n" ;
	        }
			String header_footer_botton = "</w:sectPr>";

			res.append("\n" + header_footer_top);
			res.append(header);// header has to be inside the w:body
			if (headerFirstPage != null)
				res.append(this.getHeaderFirstPage().getContent());
			res.append(footer);// header has to be inside the w:body
			if (this.getHeader().getHideHeaderAndFooterFirstPage()) {
				res.append(this.getHeader()
						.getHideHeaderAndFooterFirstPageXml());
			}

			res.append("\n" + header_footer_botton);
		}

		res.append("\n</w:body>");
		return res.toString();
	}

	// ### Getters and setters ###
	public IHeader getHeader() {
		return header;
	}

	@Override
	public IHeader getHeaderFirstPage() {
		if (headerFirstPage == null)
			headerFirstPage = HeaderFirstPage2004.create();
		return headerFirstPage;
	}

	public IFooter getFooter() {
		return footer;
	}

}
