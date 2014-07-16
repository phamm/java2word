package word.w2004.elements.tableElements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;
import word.api.interfaces.IFluentElementStylable;
import word.w2004.elements.Paragraph;

public class TableCell implements IElement, IFluentElement<TableCell>, IFluentElementStylable<TableCellStyle>{
	
	StringBuilder txt = new StringBuilder("");
	private TableCellStyle style = new TableCellStyle();
    private final String tableCellTop = "\n     <w:tc>\n        {styleCellPh}";
    private final String tableCellBottom = "\n      </w:tc>";
    
    public final static String BORDER_TOP = "top";
    public final static String BORDER_BOTTOM = "bottom";
    public final static String BORDER_LEFT = "left";
    public final static String BORDER_RIGHT = "right";
    private StringBuffer border = new StringBuffer();
    
    public void setBorders(String ... borders){
    	for (int i = 0; i < borders.length; i++) {  
    		if(BORDER_TOP.equals(borders[i]))
    			border.append("\n                    <w:top w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"000000\"/> ");
    		if(BORDER_LEFT.equals(borders[i]))
    			border.append("\n                    <w:left  w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"000000\"/> ");
    		if(BORDER_BOTTOM.equals(borders[i]))
    			border.append("\n                    <w:bottom w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"000000\"/> ");
    		if(BORDER_RIGHT.equals(borders[i]))
    			border.append("\n                    <w:right w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"000000\"/> ");
    	}
    }
	
    @Override
    public String getContent() {
    	String withStyle = style.getNewContentWithStyle(txt.toString());
        return withStyle;
    }
    
    /* 
     * Private Contructor, so you have to use Fluent Interface to create it 
     * */
    private TableCell() {
    }

    private void setCell(Object cell) {		
    	if(cell !=null){
	    	if (cell instanceof String) { //new Par
	        	txt.append(tableCellTop);
	        	if(!border.toString().isEmpty())
	        		txt.append("<w:tcPr><w:tcBorders>" + border.toString() + "</w:tcBorders></w:tcPr>");
	        	String cellValue = cell.toString();
	        	if(cellValue.equals("")){        		
	        		txt.append("<w:p><w:r></w:r></w:p>");//Empty Paragraph        		
	        	}else{
	        		txt.append(Paragraph.with(cellValue).create().getContent());
	        	}
	    	
	        	txt.append(tableCellBottom);    	
	        } else if (cell instanceof IElement) {
	
	        	if(cell instanceof TableCell){ 
	        		//simple do a getContent because object is already a TableCell
	        		txt.append(tableCellTop);
	        		if(!border.toString().isEmpty())
	            		txt.append("<w:tcPr><w:tcBorders>" + border.toString() + "</w:tcBorders></w:tcPr>");
	        		txt.append(((TableCell) cell).txt.toString()); //it is a Paragraph at this moment
	        		txt.append(tableCellBottom);  
	        	}else{
	        		//Paragraph for example...
	        		//apply parent styles
	        		//((Paragraph) cell).withStyle().
	            	IElement elm = (IElement) cell;
	            	
	            	//simple do a getContent because object is already a TableCell
	        		txt.append(tableCellTop);
	        		if(!border.toString().isEmpty())
	            		txt.append("<w:tcPr><w:tcBorders>" + border.toString() + "</w:tcBorders></w:tcPr>");
	        		if (elm.getContent() != null && elm.getContent().isEmpty() == false)
	        			txt.append( elm.getContent() );
	        		else
	        			txt.append("<w:p><w:r></w:r></w:p>");//Empty Paragraph  
	        		txt.append(tableCellBottom);
	        	}
	        	
	        } else {
	            throw new IllegalArgumentException(
	                    "Parameter can only be String of IElement. You gave me: " + cell.getClass().toString());
	        }
    	}else{
    		txt.append(tableCellTop);
        	if(!border.toString().isEmpty())
        		txt.append("<w:tcPr><w:tcBorders>" + border.toString() + "</w:tcBorders></w:tcPr>");
        	txt.append("<w:p><w:r></w:r></w:p>");//Empty Paragraph        		
        	txt.append(tableCellBottom); 
    	}
        
    }

	@Override
	public TableCell create() {
		return this;
	}
	
	
    /**
     * It can receive a String, TableCell or any other IElement.
     * for String, text will be wrapped on a Paragraph.
     * 
     * Use TableCell when you need to apply style to the Cell. Eg.: Background color. 
     *  PS.: If you need to apply "bold", "italic" or "underline" to the cell, use a Paragraph with its Pieces.
     *  
     * If you pass an IElement, the getContent() method will be blindly call. So be careful on what you pass. 
     * Remember you can always wrap elements or build your own String XML that represents what you want - Be creative!   
     * 
     * You can also pass 2 different kinds objects at the same time. Eg.:
     * <code>
     * tbl.addRow( TableRowV2.with( TableCell.with(Paragraph.with("Paragraph01").create()), "Simple String" ).withStyle().bold().create() );
     * </code>
     *   
     **/
	public static TableCell with(Object cell) {
		if(cell instanceof TableCell){
			//it is already TableCell so no need to create another instance
			//it is a Paragraph at this moment. It needs Column Top and Bottom    		
			/*((TableCell) cell).txt.insert(0, "\n		<w:tc>\n		{styleCellPh}");    		
			((TableCell) cell).txt.append("\n		</w:tc>"); */
			
			return (TableCell) cell; 
		}else{
			
			TableCell tableCell = new TableCell();
			tableCell.setCell(cell);
			return tableCell;
		}
	}

    /**
     * It can receive a String, TableCell or any other IElement.
     * for String, text will be wrapped on a Paragraph.
     * accept borders parameter
     * Use TableCell when you need to apply style to the Cell. Eg.: Background color. 
     *  PS.: If you need to apply "bold", "italic" or "underline" to the cell, use a Paragraph with its Pieces.
     *  
     * If you pass an IElement, the getContent() method will be blindly call. So be careful on what you pass. 
     * Remember you can always wrap elements or build your own String XML that represents what you want - Be creative!   
     * 
     * You can also pass 2 different kinds objects at the same time. Eg.:
     * <code>
     * tbl.addRow( TableRowV2.with( TableCell.with(Paragraph.with("Paragraph01").create()), "Simple String" ).withStyle().bold().create() );
     * </code>
     *   
     **/
	public static TableCell withBorders(TableCell cell, String ...borders) {
			//it is already TableCell so no need to create another instance
			//it is a Paragraph at this moment. It needs Column Top and Bottom    		
			((TableCell) cell).txt.insert(0, "\n		<w:tc>\n		{styleCellPh}"); 
			StringBuffer border = new StringBuffer();
			for (int i = 0; i < borders.length; i++) {  
	    		if(BORDER_TOP.equals(borders[i]))
	    			border.append("\n                    <w:top w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> ");
	    		if(BORDER_LEFT.equals(borders[i]))
	    			border.append("\n                    <w:left  w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> ");
	    		if(BORDER_BOTTOM.equals(borders[i]))
	    			border.append("\n                    <w:bottom w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> ");
	    		if(BORDER_RIGHT.equals(borders[i]))
	    			border.append("\n                    <w:right w:val=\"single\" w:sz=\"4\" wx:bdrwidth=\"10\" w:space=\"0\" w:color=\"000000\"/> ");
	    	}
			if(!border.toString().isEmpty())
				((TableCell) cell).txt.append("<w:tcPr><w:tcBorders>" + border.toString() + "</w:tcBorders></w:tcPr>");
			((TableCell) cell).txt.append("\n		</w:tc>"); 
			
			return (TableCell) cell; 
		
	}
	
	@Override
	public TableCellStyle withStyle() {
		style.setElement(this);
        return style;
	}

}
