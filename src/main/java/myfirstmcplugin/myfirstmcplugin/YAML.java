package myfirstmcplugin.myfirstmcplugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.entity.Player;

public class YAML
{

    private File file= null;

    private YamlConfiguration yaml= new YamlConfiguration();

    public YAML(File file)
    {

        this.file = file;

        if(!file.exists())
        {

            try
            {
                file.createNewFile();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }

        this.load();

    }

    public YAML(String path)
    {

        this.file = new File(path);

        if(!file.exists())
        {

            try
            {
                file.createNewFile();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        this.load();

    }

    private void load()
    {

        try
        {
            this.yaml.load(this.file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Save the Yaml's data to the file passed in the constructor.
     */
    public void save()
    {

        try
        {
            this.yaml.save(this.file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void delete()
    {
        try
        {
            this.file.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get an Integer from the given path.
     *
     * @param s
     *            Path to the Integer.
     * @return Integer at given path.
     */
    public int getInteger(String s)
    {

        return this.yaml.getInt(s);

    }

    /**
     * Save, then load the Yaml file. **Warning** Very Unstable.
     */
    public void reload()
    {

        this.save();

        this.load();

    }

    /**
     * Get a String from the path defined.
     *
     * @param s
     *            Path to the String.
     * @return String at given path.
     */
    public String getString(String s)
    {

        return this.yaml.getString(s);

    }

    /**
     * Gets an Object at the given path.
     *
     * @param s
     *            Path to given Object.
     * @return An Object at the given Path.
     */
    public Object get(String s)
    {

        return this.yaml.get(s);

    }

    /**
     * Gets a boolean at the given path.
     *
     * @param s
     *            Path to the boolean.
     * @return Boolean at the given path.
     */
    public boolean getBoolean(String s)
    {

        return this.yaml.getBoolean(s);

    }

    /**
     * If the given path has no variable, it will be given a variable.
     *
     * @param s
     *            Path to look for.
     * @param o
     *            Variable to be assigned if not existing.
     */
    public void add(String s, Object o)
    {

        if(!this.contains(s))
        {

            this.set(s, o);

        }

    }

    /**
     * Adds a String to a List of Strings.
     *
     * @param s
     *            Path to given String List.
     * @param o
     *            String to add to the String List.
     */
    public void addToStringList(String s, String o)
    {

        this.yaml.getStringList(s).add(o);

    }

    /**
     * Removes a String to a List of Strings.
     *
     * @param s
     *            Path to given String List.
     * @param o
     *            String to remove from the String List.
     */
    public void removeFromStringList(String s, String o)
    {

        this.yaml.getStringList(s).remove(o);

    }

    /**
     * Looks for a String List at given Path.
     *
     * @param s
     *            Path to String List.
     * @return String List at given Path.
     */
    public java.util.List<String> getStringList(String s)
    {

        return this.yaml.getStringList(s);

    }

    /**
     * Adds an Integer to a List of Integers.
     *
     * @param s
     *            Path to given Integer List.
     * @param o
     *            Integer to add to the Integer List.
     */
    public void addToIntegerList(String s, int o)
    {

        this.yaml.getIntegerList(s).add(o);

    }

    /**
     * Removes an Integer to a List of Integers.
     *
     * @param s
     *            Path to given Integer List.
     * @param o
     *            Integer to remove to the Integer List.
     */
    public void removeFromIntegerList(String s, int o)
    {

        this.yaml.getIntegerList(s).remove(o);

    }

    /**
     * Looks for a Integer List at given Path.
     *
     * @param s
     *            Path to Integer List.
     * @return Integer List at given Path.
     */
    public java.util.List<Integer> getIntegerList(String s)
    {

        return this.yaml.getIntegerList(s);

    }

    /**
     * Creates a new String List at given Path.
     *
     * @param s
     *            Path to create String List at.
     * @param list
     *            List to add.
     */
    public void createNewStringList(String s, java.util.List<String> list)
    {

        this.yaml.set(s, list);

    }

    /**
     * Creates a new Integer List at given Path.
     *
     * @param s
     *            Path to create Integer List at.
     * @param list
     *            List to add.
     */
    public void createNewIntegerList(String s, java.util.List<Integer> list)
    {

        this.yaml.set(s, list);

    }

    /**
     * **Untested/Unstable** Attempts to remove a variable at the given Path.
     *
     * @param s
     *            Path to given variable needing removal.
     */
    public void remove(String s)
    {

        this.set(s, null);

    }

    /**
     * Returns true if the given Path has a value.
     *
     * @param s
     *            Path to value.
     * @return True if the given Path has a value.
     */
    public boolean contains(String s)
    {

        return this.yaml.contains(s);

    }

    /**
     * Gets a double at the given Path.
     *
     * @param s
     *            Path to double.
     * @return Double at given Path.
     */
    public double getDouble(String s)
    {

        return this.yaml.getDouble(s);

    }

    /**
     * Sets a Object to the given Path.
     *
     * @param s
     *            Path to variable being assigned.
     * @param o
     *            Variable being assigned.
     */
    public void set(String s, Object o)
    {

        this.yaml.set(s, o);

    }

    /**
     * Increases an Integer by 1.
     *
     * @param s
     *            Path to Integer being incremented.
     */
    public void increment(String s)
    {

        this.yaml.set(s, this.getInteger(s) + 1);

    }

    /**
     * Decreases an Integer by 1.
     *
     * @param s
     *            Path to Integer being decremented.
     */
    public void decrement(String s)
    {

        this.yaml.set(s, this.getInteger(s) - 1);

    }

    /**
     * Increases an Integer by i.
     *
     * @param s
     *            Path to Integer being incremented.
     */
    public void increment(String s, int i)
    {

        this.yaml.set(s, this.getInteger(s) + i);

    }

    /**
     * Decreases an Integer by 1.
     *
     * @param s
     *            Path to Integer being decremented.
     */
    public void decrement(String s, int i)
    {

        this.yaml.set(s, this.getInteger(s) - i);

    }

    /**
     * Returns the YamlConfiguration's Options.
     *
     * @return YamlConfiguration's Options.
     */
    public YamlConfigurationOptions options()
    {

        return this.yaml.options();

    }

    public static YAML getPlayerYaml(MyFirstMCPlugin plugin, Player player)
    {

        return new YAML(plugin.getDataFolder().getAbsolutePath() + File.separator + "data" + File.separator + "players" + File.separator + player.getName() + ".yml");

    }

    public static YAML getOfflinePlayerYaml(MyFirstMCPlugin plugin, String string)
    {

        return new YAML(plugin.getDataFolder().getAbsolutePath() + File.separator + "data" + File.separator + "players" + File.separator + string + ".yml");

    }

}