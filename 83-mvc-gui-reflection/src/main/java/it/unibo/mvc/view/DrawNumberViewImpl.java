package it.unibo.mvc.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberViewImpl implements DrawNumberView{

    
    private static final String FRAME_NAME = "Draw Number App";
    private final JFrame frame = new JFrame(FRAME_NAME);

    
    public DrawNumberViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JPanel(new BorderLayout()));
        frame.pack();
        frame.setLocationByPlatform(true);
    }

    @Override
    public void setController(DrawNumberController observer) {
        
    }

    @Override
    public void start() {
    }

    @Override
    public void result(DrawResult res) {
      System.out.println(res.getDescription());
    }

}
