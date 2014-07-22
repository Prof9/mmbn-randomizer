package mmbn;

public class Item {
    public enum Type {
        BATTLECHIP,
        BATTLECHIP_TRAP,
        BUGFRAG,
        EXPMEMORY,
        HP,
        HP_MEMORY,
        ITEM,
        NAVICUST_PROGRAM,
        REGUP,
        SUBCHIP,
        SUBMEMORY,
        ZENNY,
        ZENNY_TRAP,
    }
    
    protected final byte[] base;
    
    protected Item.Type type;
    
    protected int value;
    protected int subValue;
    
    protected BattleChip chip;
    
    public Item(byte[] base) {
        this.base = base.clone();
        this.type = null;
        this.value = 0;
        this.subValue = 0;
        this.chip = null;
    }
    
    public byte[] base() {
        return this.base.clone();
    }
    public int value() {
        return this.value;
    }
    public int subValue() {
        return this.subValue;
    }
    
    public boolean isEmpty() {
        return this.type == null;
    }
    public boolean isChip() {
        return this.type == Item.Type.BATTLECHIP ||
                this.type == Item.Type.BATTLECHIP_TRAP;
    }
    public boolean isZenny() {
        return this.type == Item.Type.ZENNY ||
                this.type == Item.Type.ZENNY_TRAP;
    }
    public boolean isBugFrag() {
        return this.type == Item.Type.BUGFRAG;
    }
    public boolean isItem() {
        return this.type == Item.Type.ITEM ||
                this.type == Item.Type.EXPMEMORY ||
                this.type == Item.Type.HP_MEMORY ||
                this.type == Item.Type.REGUP ||
                this.type == Item.Type.SUBCHIP ||
                this.type == Item.Type.SUBMEMORY;
    }
    public boolean isProgram() {
        return this.type == Item.Type.NAVICUST_PROGRAM;
    }
    public boolean isTrap() {
        return this.type == Item.Type.BATTLECHIP_TRAP ||
                this.type == Item.Type.ZENNY_TRAP;
    }
    
    protected void checkChip() {
        if (!isChip()) {
            throw new IllegalStateException("This container does not contain a "
                    + "BattleChip.");
        }
    }
    protected void checkProgram() {
        if (!isProgram()) {
            throw new IllegalStateException("This container does not contain a "
                    + "NaviCust program.");
        }
    }
    
    public Item.Type type() {
        return this.type;
    }
    
    public BattleChip getChip() {
        checkChip();
        return this.chip;
    }
    
    public int getCode() {
        checkChip();
        return this.subValue;
    }
    
    public void setChipCode(BattleChip chip, int code) {
        this.type = Item.Type.BATTLECHIP;
        this.chip = chip;
        this.value = chip.index();
        this.subValue = code;
    }
    
    public void setChipCodeTrap(BattleChip chip, int code) {
        this.type = Item.Type.BATTLECHIP_TRAP;
        this.chip = chip;
        this.value = chip.index();
        this.subValue = code;
    }
    
    public int getAmount() {
        if (isZenny() || isBugFrag()) {
            return this.value;
        } else if (isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }
    public void setAmount(int amount) {
        if (!isZenny() && !isBugFrag()) {
            throw new IllegalStateException("This container does not contain "
                    + "Zenny or BugFrags.");
        }
        this.value = amount;
    }
    
    public void setZenny(int amount) {
        this.type = Item.Type.ZENNY;
        this.value = amount;
        this.chip = null;
    }
    
    public void setZennyTrap(int amount) {
        this.type = Item.Type.ZENNY_TRAP;
        this.value = amount;
        this.chip = null;
    }
    
    public void setBugFrags(int amount) {
        this.type = Item.Type.BUGFRAG;
        this.value = amount;
        this.chip = null;
    }
    
    public int getItem() {
        if (!isItem()) {
            throw new IllegalStateException("This container does not contain "
                    + "an item.");
        }
        return this.value;
    }
    
    public void setItem(int item) {
        this.type = Item.Type.ITEM;
        this.value = item;
        this.chip = null;
    }
    
    public void setExpMemory(int item) {
        setItem(item);
        this.type = Item.Type.EXPMEMORY;
    }
    
    public void setHPMemory(int item) {
        setItem(item);
        this.type = Item.Type.HP_MEMORY;
    }
    
    public void setRegUP(int item) {
        setItem(item);
        this.type = Item.Type.HP_MEMORY;
    }
    
    public void setSubChip(int item) {
        setItem(item);
        this.type = Item.Type.SUBCHIP;
    }
    
    public void setSubMemory(int item) {
        setItem(item);
        this.type = Item.Type.SUBMEMORY;
    }
    
    public int getProgram() {
        checkProgram();
        return this.value;
    }
    
    public int getColor() {
        checkProgram();
        return this.subValue;
    }
    
    public void setProgramColor(int program, int color) {
        this.value = program;
        this.subValue = color;
        this.chip = null;
    }
}
