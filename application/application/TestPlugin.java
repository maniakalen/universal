package application;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class TestPlugin extends Plugin
{
    @Override
    public void registerPlugin(JFrame frame) {
        MainFrame f = (MainFrame)frame;
        f.setSize(new Dimension(150, 100));
        JMenu m = new JMenu(this.getName());
        for ( String key : this._list.keySet() ) {
            JMenuItem item = new JMenuItem(key);
            item.addActionListener(this._getNewItemInstance(this._list.get(key)));
            m.add(item);
        }
        
        f.getFrameMenuBar().add(m); 
    }
    
    
    private PluginItemAbstract _getNewItemInstance(String cls) {
        PluginItemAbstract p;
        try {
            Class<?> c = Class.forName(cls);
            p = (PluginItemAbstract)c.newInstance();
        } catch (Exception ex) {
            System.err.println(ex.toString());
            p = new PluginItemAbstract() { 
                @Override
                public void actionPerformed(ActionEvent e) {}
            };
        }
        return p;
    }
    
    public static class ItemDos extends PluginItemAbstract { 
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked two");
        }
    }
}
