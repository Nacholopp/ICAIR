package ui;

import javax.swing.*;

public class FrameIcair extends JFrame{
    final private PanelPrincipal pnlPrincipal;

    public FrameIcair(){
        this.setTitle("ICAIR");
        this.setResizable(false);
        pnlPrincipal=new PanelPrincipal();
        this.add(pnlPrincipal);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
