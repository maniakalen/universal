package application;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import application.config.Config;
public class Root
{
    private MainFrame _mainFrame;
    private PluginManager _manager;
    private application.config.Config _config;
    public Root() {
        this.setFrame( this.createNewFrame() );
        String file = System.getProperty("vmConfig");
        this._config = new application.config.Config(file);
        this.assignPluginManager();
        
        
    }
    public application.config.Config getConfig() {
        return this._config;
    }
    public MainFrame getFrame() {
        return this._mainFrame;
    }
    public void setFrame(MainFrame frame) {
        this._mainFrame = frame;
    }
    
    public MainFrame createNewFrame() {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
    public void assignPluginManager() {
        this._manager = new PluginManager(this);
        this._manager.registerPlugins();
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new Root()).getFrame().setVisible(true);  
            }
        });
    }
}
