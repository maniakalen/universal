package application;
import application.config.Config;
public class PluginManager
{
    private Root _root;
    public PluginManager(Root r) {
        super();
        this._root = r;
    }
    public void registerPlugins() {
        for ( Config.ConfigPlugin item : this._root.getConfig().getPluginConfigList() ) {
            Plugin p = this._getNewPluginInstance(item.getName(), item.getClassName());
            p.itemsConfig(item.getPluginItems());
            p.registerPlugin(this._root.getFrame());
        }
    }
    private Plugin _getNewPluginInstance(String name, String cls) {
        Plugin p;
        try {
            Class c = Class.forName(cls);
            p = (Plugin)c.newInstance();
            p.setName(name);
            
        } catch (Exception c) {
            System.err.println(c.getMessage());
            p = new Plugin();
        }
        return p;
    }
}
