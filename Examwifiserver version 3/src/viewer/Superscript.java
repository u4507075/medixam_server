/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author bon
 */
class Superscript extends HTMLEditorKit.ParserCallback {

    private boolean encounteredATableRow = false;

    @Override
    public void handleText(char[] data, int pos) {
        if(encounteredATableRow)
        {
            System.out.println(new String(data));
            System.out.println("Position "+pos);
        }
    }

    @Override
    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
        if(t == HTML.Tag.SUP) encounteredATableRow = true;
    }

    @Override
    public void handleEndTag(HTML.Tag t, int pos) {
        if(t == HTML.Tag.SUP) encounteredATableRow = false;
    }
}
