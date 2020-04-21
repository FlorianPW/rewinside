package net.quatulo.lobby.nbt;

import java.util.HashSet;
import java.util.Set;

public class NBTListCompound {
    private NBTList owner;
    private Object compound;

    protected NBTListCompound(NBTList parent, Object obj) {
        this.owner = parent;
        this.compound = obj;
    }

    public void setString(String key, String value) {
        if (value == null) {
            this.remove(key);
        } else {
            try {
                this.compound.getClass().getMethod("setString", String.class, String.class).invoke(this.compound, key, value);
                this.owner.save();
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    public void setInteger(String key, int value) {
        try {
            this.compound.getClass().getMethod("setInt", String.class, Integer.TYPE).invoke(this.compound, key, value);
            this.owner.save();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public int getInteger(String value) {
        try {
            return (Integer)this.compound.getClass().getMethod("getInt", String.class).invoke(this.compound, value);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    public void setDouble(String key, double value) {
        try {
            this.compound.getClass().getMethod("setDouble", String.class, Double.TYPE).invoke(this.compound, key, value);
            this.owner.save();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public double getDouble(String key) {
        try {
            return (Double)this.compound.getClass().getMethod("getDouble", String.class).invoke(this.compound, key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0.0D;
        }
    }

    public String getString(String key) {
        try {
            return (String)this.compound.getClass().getMethod("getString", String.class).invoke(this.compound, key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return "";
        }
    }

    public boolean hasKey(String key) {
        try {
            return (Boolean)this.compound.getClass().getMethod("hasKey", String.class).invoke(this.compound, key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public Set<String> getKeys() {
        try {
            return (Set)ReflectionMethod.LISTCOMPOUND_GET_KEYS.run(this.compound, new Object[0]);
        } catch (Exception var2) {
            var2.printStackTrace();
            return new HashSet();
        }
    }

    public void remove(String key) {
        try {
            this.compound.getClass().getMethod("remove", String.class).invoke(this.compound, key);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}
