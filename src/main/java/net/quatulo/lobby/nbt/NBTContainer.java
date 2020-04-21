package net.quatulo.lobby.nbt;

public class NBTContainer extends NBTCompound {
    private Object nbt;

    public NBTContainer() {
        super((NBTCompound)null, (String)null);
        this.nbt = ObjectCreator.NMS_NBTTAGCOMPOUND.getInstance(new Object[0]);
    }

    protected NBTContainer(Object nbt) {
        super((NBTCompound)null, (String)null);
        this.nbt = nbt;
    }

    public NBTContainer(String nbtString) throws IllegalArgumentException {
        super((NBTCompound)null, (String)null);

        try {
            this.nbt = ReflectionMethod.PARSE_NBT.run((Object)null, new Object[]{nbtString});
        } catch (Exception var3) {
            var3.printStackTrace();
            throw new IllegalArgumentException("Malformed Json: " + var3.getMessage());
        }
    }

    protected Object getCompound() {
        return this.nbt;
    }

    protected void setCompound(Object tag) {
        this.nbt = tag;
    }
}
