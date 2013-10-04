package application.config;

import org.jdom2.*;
import org.jdom2.input.*;
import java.util.*;
import java.io.File;
public class Config
{
    private ConfigSourceInstance _config;
    
    public Config(String file) {
        this._config = new ConfigXml(file);
    }
    
    public List<Config.ConfigPlugin> getPluginConfigList() {
        Element plugins = this._config.getNode("plugins");
        List<Config.ConfigPlugin> listing = new ArrayList<Config.ConfigPlugin>();
        for ( Element item : plugins.getChildren("plugin") ) {
            String name = item.getAttribute("name").getValue();
            String cls = item.getAttribute("class").getValue();
            List<Element> items = item.getChildren();
            listing.add(new Config.ConfigPlugin(name, cls, items)); 
        }
        return listing;
    }
    
    interface ConfigSourceInstance {
        public Element getNode(String name);
    }
    public class ConfigXml implements ConfigSourceInstance {
        private Document _data;
        
        public ConfigXml(String file) {
            try {

                SAXBuilder saxBuilder = new SAXBuilder();
                this._data = saxBuilder.build(new File("config.xml"));
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        public Element getNode(String name) {
            return this._data.getRootElement().getChild(name);
        }
    }
    
    public class ConfigPlugin {
        private String _name;
        private String _class;
        private List<Element> _items;
        
        public ConfigPlugin(String name, String cls, List<Element> nodes) {
            this._name = name;
            this._class = cls;
            this._items = nodes;
        }
        
        public String getName() { return this._name; }
        public String getClassName() { return this._class; }
        public HashMap<String, String> getPluginItems() {
            HashMap<String, String> itemsList = new HashMap<String, String>();
            for ( Element item : this._items ) {
                if ( item.getName() == "item" ) {
                    itemsList.put(item.getAttribute("name").getValue(), item.getAttribute("class").getValue());
                }
            }
            return itemsList;
        }
    }
}
