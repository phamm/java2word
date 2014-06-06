package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.ISuperStylin;

public class TableCellStyle implements ISuperStylin{
	
	private IElement element;
	StringBuilder style = new StringBuilder("");
	
	private String bgColor = "";
	private int gridSpan = 0;
	private String vMerge = null;
	
	@Override
	public String getNewContentWithStyle(String txt) {
		
		doStyleBgColor(style);
		doStyleGridSpan(style);
		doVMerge(style);
		
		if(!"".equals(style.toString())){
			style.insert(0, "<w:tcPr>");
			style.append("</w:tcPr>\n");			
		}
		
		return txt.replace("{styleCellPh}", style);
	}

	@Override
	public void setElement(IElement element) {
		this.element = element;		
	}

	@Override
	public IElement create() {
		return this.element;
	}

	//### Useful external methods ############################
    public TableCellStyle bgColor(String bgColor) {
		this.bgColor = bgColor;
		return this;
	}
    /*
     * 2 means: it will merge this cell with the second one. 
     * 3 means: it will merge this cell with the second AND the third ones. 
     * 
     * */
    public TableCellStyle gridSpan(int gridSpan) {
    	this.gridSpan = gridSpan;
    	return this;
    }
    
    /**
<<<<<<< HEAD
     * accept value: continue or restart
     * Refer to: http://officeopenxml.com/WPtableCellProperties.php
     * 			Merge 	This element specifies that the cell is part of a vertically merged set of cells. defines the number of logical columns across which the cell spans. It has a single attribute w:val which specifies how the cell is part of a vertically merged region. The cell can be part of an existing group of merged cells or it can start a new group of merged cells. Possible values are:
    				continue -- the current cell continues a previously existing merge group
    				restart -- the corrent cell starts a new merge group
					If omitted, the value is assumed to be continue. See the discussion of <w:tblGrid> at Table Grid/Column Definition.
					Reference: ECMA-376, 3rd Edition (June, 2011), Fundamentals and Markup Language Reference § 17.4.85.
     * Example:
			tblPropSummary.addRow(TableRow.with( TableCell.with("Cell merge 1").withStyle().vMerge(_RESTART).create() ,TableCell.with("Cell merge 2").withStyle().vMerge(_RESTART).create() , "Cell not merge 3" )
			tblPropSummary.addRow(TableRow.with( TableCell.with("").withStyle().vMerge(_CONTINUE).create() ,TableCell.with("").withStyle().vMerge(_CONTINUE).create(), "Cell not merge 4"))
=======
     * continue means: no merge
     * restart means: with with other cell in vertical until continue
>>>>>>> branch 'master' of https://github.com/phamm/java2word.git
     * @param val
     * @return
     */
    public TableCellStyle vMerge(String val){
    	this.vMerge = val;
    	return this;
    }
    
	//### Chunk of code ######################################
    private void doStyleBgColor(StringBuilder style) {
    	if (!"".equals(bgColor)) {
    		style.append("\n            	<w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"" + this.bgColor + "\"/>\n");
    	}
    }
	
    private void doStyleGridSpan(StringBuilder style) {
    	if (gridSpan > 0) {
    		style.append("\n            	<w:gridSpan w:val=\"" + this.gridSpan + "\"/>");
    	}
    }
    private void doVMerge(StringBuilder style) {
    	if (vMerge != null) {
    		style.append("\n            	<w:tcW w:w=\"0\" w:type=\"auto\"/>");
    		style.append("\n            	<w:vmerge w:val=\"" + this.vMerge + "\" />");
    	}
    }
    
}
