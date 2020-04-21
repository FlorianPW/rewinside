package net.quatulo.lobby.nbt;

public class NBTList {
    private String listName;
    private NBTCompound parent;
    private NBTType type;
    private Object listObject;

    protected NBTList(NBTCompound owner, String name, NBTType type, Object list) {
        this.parent = owner;
        this.listName = name;
        this.type = type;
        this.listObject = list;
        if (type != NBTType.NBTTagString && type != NBTType.NBTTagCompound) {
            System.err.println("List types != String/Compound are currently not implemented!");
        }

    }

    protected void save() {
        this.parent.set(this.listName, this.listObject);
    }

    public NBTListCompound addCompound() {
        if (this.type != NBTType.NBTTagCompound) {
            (new Throwable("Using Compound method on a non Compound list!")).printStackTrace();
            return null;
        } else {
            try {
                Object compound = ClassWrapper.NMS_NBTTAGCOMPOUND.getClazz().newInstance();
                if (MinecraftVersion.getVersion().getVersionId() >= MinecraftVersion.MC1_14_R1.getVersionId()) {
                    ReflectionMethod.LIST_ADD.run(this.listObject, new Object[]{0, compound});
                } else {
                    ReflectionMethod.LEGACY_LIST_ADD.run(this.listObject, new Object[]{compound});
                }

                return new NBTListCompound(this, compound);
            } catch (Exception var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

    public NBTListCompound getCompound(int id) {
        if (this.type != NBTType.NBTTagCompound) {
            (new Throwable("Using Compound method on a non Compound list!")).printStackTrace();
            return null;
        } else {
            try {
                Object compound = ReflectionMethod.LIST_GET.run(this.listObject, new Object[]{id});
                return new NBTListCompound(this, compound);
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public String getString(int i) {
        if (this.type != NBTType.NBTTagString) {
            (new Throwable("Using String method on a non String list!")).printStackTrace();
            return null;
        } else {
            try {
                return (String)ReflectionMethod.LIST_GET_STRING.run(this.listObject, new Object[]{i});
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public void addString(String s) {
        if (this.type != NBTType.NBTTagString) {
            (new Throwable("Using String method on a non String list!")).printStackTrace();
        } else {
            try {
                if (MinecraftVersion.getVersion().getVersionId() >= MinecraftVersion.MC1_14_R1.getVersionId()) {
                    ReflectionMethod.LIST_ADD.run(this.listObject, new Object[]{0, ClassWrapper.NMS_NBTTAGSTRING.getClazz().getConstructor(String.class).newInstance(s)});
                } else {
                    ReflectionMethod.LEGACY_LIST_ADD.run(this.listObject, new Object[]{ClassWrapper.NMS_NBTTAGSTRING.getClazz().getConstructor(String.class).newInstance(s)});
                }

                this.save();
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public void setString(int i, String s) {
        if (this.type != NBTType.NBTTagString) {
            (new Throwable("Using String method on a non String list!")).printStackTrace();
        } else {
            try {
                ReflectionMethod.LIST_SET.run(this.listObject, new Object[]{i, ClassWrapper.NMS_NBTTAGSTRING.getClazz().getConstructor(String.class).newInstance(s)});
                this.save();
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    public void remove(int i) {
        try {
            ReflectionMethod.LIST_REMOVE_KEY.run(this.listObject, new Object[]{i});
            this.save();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public int size() {
        try {
            return (Integer)ReflectionMethod.LIST_SIZE.run(this.listObject, new Object[0]);
        } catch (Exception var2) {
            var2.printStackTrace();
            return -1;
        }
    }

    public NBTType getType() {
        return this.type;
    }
}
