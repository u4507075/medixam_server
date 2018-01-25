/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import exam.Style;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author bon
 */
public class Textpanel extends JLabel {

   ArrayList layouts;
   Font font1 = new Font("SansSerif", Font.PLAIN, 12);
   Font font2 = new Font("Browallia New", Font.PLAIN, 30);
   Font font3 = new Font("Cordia New", Font.PLAIN, 30);
   Font font5 = new Font("FreeSans", Font.PLAIN, 30);
   Font font6 = new Font("Liberation Sans", Font.PLAIN, 30);
   Font font4 = new Font("Symbol", Font.PLAIN, 30);
   Font font7 = new Font("OpenSymbol", Font.PLAIN, 30);
   Font[] fonts = {font1,font2,font3,font4,font5,font6,font7};
   ArrayList stylelist;
   public Textpanel(ArrayList stylelist)
   {
       this.stylelist = stylelist;
   }
    @Override
  public void paint(Graphics g) {
    if (layouts == null)
      getLayouts(g);

    Point pen = new Point(0, 0);
    Graphics2D g2d = (Graphics2D)g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    
    //g2d.setColor(java.awt.Color.BLACK); // or a property
    //g2d.setFont(font);
    

    if(layouts!=null)
    {
    Iterator it = layouts.iterator();
    while (it.hasNext()) {
      TextLayout layout = (TextLayout) it.next();
      pen.y += (layout.getAscent());
      //g2d.setFont(font);
      layout.draw(g2d, pen.x, pen.y);
      pen.y += layout.getDescent();
    }
    }
    //this.setPreferredSize(new Dimension(500,layouts.size()*80));
  }

  private void getLayouts(Graphics g) {
      
    String text = this.getText();  
    if(text.length()>0)
    {
    layouts = new ArrayList();

    Graphics2D g2d = (Graphics2D) g;
    FontRenderContext frc = g2d.getFontRenderContext();
/*
    for(int i=0;i<text.length();i++)
    {
        //EN = Unicode = U+00xx
        //TH = Unicode = U+0Exx
    String hex = String.format("%04x", (int) text.charAt(i));
    //System.out.println(hex);
    }*/
    //AttributedString attrStr = new AttributedString(text);
    //this.setFont(font);
    AttributedString attrStr = new AttributedString(text);
    for(int i=0;i<text.length();i++)
    {
        Font font = getDisplayFont(text.charAt(i));
        attrStr.addAttribute(TextAttribute.FONT, font, i, i+1);   
    }
    
    ArrayList list = new ArrayList();
    for(int i=0;i<text.length();i++)
    {
        ArrayList l = new ArrayList();
        for(int j=0;j<stylelist.size();j++)
        {
            if(i>=((Style)stylelist.get(j)).getStart() && i<((Style)stylelist.get(j)).getEnd())
            {
                l.add(((Style)stylelist.get(j)).getType());
            }
        }
        list.add(l);
    }
    
    for(int i=0;i<list.size();i++)
    {
        ArrayList l = (ArrayList)list.get(i);
        Style s = new Style();
        Font f = getDisplayFont(text.charAt(i));
        Font font = new Font(f.getFontName(), f.getStyle(), f.getSize());
        Map<TextAttribute,?> attributes = font.getAttributes();  
        Map<TextAttribute,Object> newAttributes = new HashMap<>(attributes);  
        for(int j=0;j<l.size();j++)
        {
            if(l.get(j).toString().equals(s.BOLD()))
            {
                newAttributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);  
            }
            else if(l.get(j).toString().equals(s.ITALIC()))
            {
                newAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);  
            }
            else if(l.get(j).toString().equals(s.SUBSCRIPT()))
            {
                newAttributes.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB);  
            }
            else if(l.get(j).toString().equals(s.SUPERSCRIPT()))
            {
                newAttributes.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);  
            }
            else if(l.get(j).toString().equals(s.STRIKE()))
            {
                attrStr.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, i, i+1);
            }
            else if(l.get(j).toString().equals(s.UNDERLINE()))
            {
                attrStr.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, i, i+1);
            }
        }
        font = font.deriveFont(newAttributes);  
        attrStr.addAttribute(TextAttribute.FONT, font, i, i+1);
    }
    /*
    for(int i=0;i<stylelist.size();i++)
    {
        Style style = (Style)stylelist.get(i);
        Font font = new Font(font1.getFontName(), font1.getStyle(), font1.getSize());
        if(style.getType().equals(style.BOLD()))
        {
            Map<TextAttribute,?> attributes = font.getAttributes();  
            Map<TextAttribute,Object> newAttributes = new HashMap<>(attributes);  
            newAttributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);  
            font = font.deriveFont(newAttributes);  
            attrStr.addAttribute(TextAttribute.FONT, font, style.getStart(), style.getEnd());
        }
        else if(style.getType().equals(style.ITALIC()))
        {
            Map<TextAttribute,?> attributes = font.getAttributes();  
            Map<TextAttribute,Object> newAttributes = new HashMap<>(attributes);  
            newAttributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);  
            font = font.deriveFont(newAttributes);  
            attrStr.addAttribute(TextAttribute.FONT, font, style.getStart(), style.getEnd());
        }
        else if(style.getType().equals(style.SUBSCRIPT()))
        {
            Map<TextAttribute,?> attributes = font.getAttributes();  
            Map<TextAttribute,Object> newAttributes = new HashMap<>(attributes);  
            newAttributes.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB);  
            font = font.deriveFont(newAttributes);  
            attrStr.addAttribute(TextAttribute.FONT, font, style.getStart(), style.getEnd());
        }
        else if(style.getType().equals(style.SUPERSCRIPT()))
        {
            Map<TextAttribute,?> attributes = font.getAttributes();  
            Map<TextAttribute,Object> newAttributes = new HashMap<>(attributes);  
            newAttributes.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);  
            font = font.deriveFont(newAttributes);  
            attrStr.addAttribute(TextAttribute.FONT, font, style.getStart(), style.getEnd());
        }
        else if(style.getType().equals(style.STRIKE()))
        {
            attrStr.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, style.getStart(), style.getEnd());
        }
        else if(style.getType().equals(style.UNDERLINE()))
        {
            attrStr.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, style.getStart(), style.getEnd());
        }
        
    }
    */
    
    /*
        Font font2 = new Font("TH Sarabun New", Font.PLAIN, 30);
    Map<TextAttribute,?> attributes = font2.getAttributes();  
    Map<TextAttribute,Object> newAttributes = new HashMap<>(attributes);  
    newAttributes.put(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUPER);  
    newAttributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);  
    font2 = font2.deriveFont(newAttributes);  
    
    for(int i=1;i<text.length();i++)
    {
        if(i==4)
        { 
            attrStr.addAttribute(TextAttribute.FONT, font2, 3, 4);
            attrStr.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 3, 4);
        }
        else if (i==7)
        {
            attrStr.addAttribute(TextAttribute.FOREGROUND, Color.RED, 1, 2);
        }
        else
        {
            attrStr.addAttribute(TextAttribute.FONT, font, i-1, i);   
        }
    }
    */
    
    LineBreakMeasurer measurer = new LineBreakMeasurer(
      attrStr.getIterator(), frc);
    float wrappingWidth;

    wrappingWidth = getSize().width - 15;
    
    while (measurer.getPosition() < text.length()) {
      TextLayout layout = measurer.nextLayout(wrappingWidth);
      layouts.add(layout);
    }
    }
  }
  private Font getDisplayFont(char c)
  {
      for(int i=0;i<fonts.length;i++)
      {
          if(fonts[i].canDisplay(c))
          {
              return fonts[i];
          }
      }
      return new Font(this.getFont().getFontName(),this.getFont().getStyle(),this.getFont().getSize());
  }
}
