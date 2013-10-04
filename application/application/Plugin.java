package application;
import javax.swing.JFrame;
import java.util.HashMap;
public class Plugin implements PluginInterface
{
    protected String _name;
    protected HashMap<String, String> _list;
    public void registerPlugin(JFrame frame) {}
    
    public void setName(String name) {
        this._name = name;
    }
    
    public String getName() {
        return this._name;
    }
    
    public void itemsConfig(HashMap<String, String> list) {
        this._list = list;   
    }
}
