package org.newdawn.slick.tools.hiero.effects;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * An effect to spread a gradient down the text
 *
 * @author offset
 */
public class OffsetGradientEffect implements StorableEffect {
   /** The color of the top */
   private Color top = new Color(255,255,0);
   /** The color of the bottom */
   private Color bottom = new Color(255,0,0);
   /** The configuration panel for this effect */
   private ConfigPanel confPanel = new ConfigPanel();
   /** The offset on the y axis at which the gradient should begin */
   private float yoff = 0;
       
   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#postGlyphRender(java.awt.Graphics2D, org.newdawn.slick.tools.hiero.effects.DrawingContext, org.newdawn.slick.tools.hiero.effects.Glyph)
    */
   public void postGlyphRender(Graphics2D g, DrawingContext context, Glyph glyph) {
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#postPageRender(java.awt.Graphics2D, org.newdawn.slick.tools.hiero.effects.DrawingContext)
    */
   public void postPageRender(Graphics2D g, DrawingContext context) {
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#preGlyphRender(java.awt.Graphics2D, org.newdawn.slick.tools.hiero.effects.DrawingContext, org.newdawn.slick.tools.hiero.effects.Glyph)
    */
   public void preGlyphRender(Graphics2D g, DrawingContext context, Glyph glyph) {
      int top = -context.getMaxGlyphHeight();
      int bottom = context.getMaxGlyphDecent();
      
      yoff = ((Number)confPanel.yspinner.getValue()).floatValue();
      GradientPaint paint = new GradientPaint(glyph.getX(), top+yoff, this.top, glyph.getX(), bottom, this.bottom);
      g.setPaint(paint);
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#prePageRender(java.awt.Graphics2D, org.newdawn.slick.tools.hiero.effects.DrawingContext)
    */
   public void prePageRender(Graphics2D g, DrawingContext context) {
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#getConfigurationPanel()
    */
   public JPanel getConfigurationPanel() {
      confPanel.newTop = top;
      confPanel.newBottom = bottom;
      
      return confPanel;
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#getEffectName()
    */
   public String getEffectName() {
	      return "OffsetGradient(davedes)";
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#getInstance()
    */
   public Effect getInstance() {
      return new OffsetGradientEffect();
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.Effect#setConfigurationFromPanel(javax.swing.JPanel)
    */
   public void setConfigurationFromPanel(JPanel panel) {
      top = confPanel.newTop;
      bottom = confPanel.newBottom;
   }

   /**
    * @see java.lang.Object#toString()
    */
   public String toString() {
      return "OffsetGradient(davedes)";
   }
   
   /**
    * A panel to configure this effect
    *
    * @author kevin
    */
   private class ConfigPanel extends JPanel {
      /** The button to change the color */
      private JButton topButton = new JButton("Set Top");
      /** The color for the top*/
      private Color newTop;
      
      /** The button to change the color */
      private JButton bottomButton = new JButton("Set Bottom");
      /** The color for the bottom */
      private Color newBottom;
               
      /** The control to set the gradient offset */
      private JSpinner yspinner = new JSpinner(new SpinnerNumberModel(0,-500,500,1));
               
      /**
       * Create a new configuration panel
       */
      public ConfigPanel() {
         setLayout(null);
         
         JLabel label = new JLabel("Color");
         label.setBounds(5,30,200,25);
         add(label);
         topButton.setBounds(5,55,150,25);
         add(topButton);
                       
         JLabel tsl = new JLabel("Y-Offset", JLabel.TRAILING);
                        tsl.setBounds(5, 115, 150, 25);
                        add(tsl);
                       
                        yspinner.setBounds(160, 115, 100, 25);
                        add(yspinner);
                       
         topButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               newTop = JColorChooser.showDialog(ConfigPanel.this, "Choose Color", newTop);
            }
         });
         
         bottomButton.setBounds(5,85,150,25);
         add(bottomButton);
         
         bottomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               newBottom = JColorChooser.showDialog(ConfigPanel.this, "Choose Color", newBottom);
            }
         });
         setSize(300,155);
      }
      
      /**
       * @see javax.swing.JComponent#paint(java.awt.Graphics)
       */
      public void paint(Graphics g) {
         super.paint(g);
         
         g.setColor(newTop);
         g.fillRect(160,55,100,25);
         g.setColor(Color.black);
         g.drawRect(160,55,100,25);
         g.setColor(newBottom);
         g.fillRect(160,85,100,25);
         g.setColor(Color.black);
         g.drawRect(160,85,100,25);
      }
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.StorableEffect#load(java.lang.String, java.util.Properties)
    */
   public void load(String prefix, Properties props) {
      int red = Integer.parseInt(props.getProperty(prefix+"top.red"));
      int green = Integer.parseInt(props.getProperty(prefix+"top.green"));
      int blue = Integer.parseInt(props.getProperty(prefix+"top.blue"));
      top = new Color(red,green,blue);
      red = Integer.parseInt(props.getProperty(prefix+"bottom.red"));
      green = Integer.parseInt(props.getProperty(prefix+"bottom.green"));
      blue = Integer.parseInt(props.getProperty(prefix+"bottom.blue"));
      bottom = new Color(red,green,blue);
      yoff = Float.parseFloat(props.getProperty(prefix+"yoff", "0"));
   }

   /**
    * @see org.newdawn.slick.tools.hiero.effects.StorableEffect#store(java.lang.String, java.util.Properties)
    */
   public void store(String prefix, Properties props) {
      props.setProperty(prefix+"top.red", ""+top.getRed());
      props.setProperty(prefix+"top.green", ""+top.getGreen());
      props.setProperty(prefix+"top.blue", ""+top.getBlue());
      props.setProperty(prefix+"bottom.red", ""+bottom.getRed());
      props.setProperty(prefix+"bottom.green", ""+bottom.getGreen());
      props.setProperty(prefix+"bottom.blue", ""+bottom.getBlue());
      yoff = ((Number)confPanel.yspinner.getValue()).floatValue();
      props.setProperty(prefix+"yoff", ""+yoff);
   }
}
