import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class docFilter extends DocumentFilter {
	
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, 
    		javax.swing.text.AttributeSet attr)
    
        throws BadLocationException {
    			fb.insertString(offset, text.replaceAll("[^0-9.]", ""), attr);	
    }
    public void insertString(DocumentFilter.FilterBypass fb, int offset, int length, String text, 
    		javax.swing.text.AttributeSet attr)

            	throws BadLocationException {
    				fb.insertString(offset, text.replaceAll("[^0-9.]", ""), attr);	
    			}
}