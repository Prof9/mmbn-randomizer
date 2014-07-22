package mmbn.bn6;

import mmbn.ChipLibrary;
import mmbn.Item;
import mmbn.ItemProducer;
import mmbn.MysteryDataContents;
import rand.ByteConverter;
import rand.ByteStream;

public class BN6MysteryDataContentsProducer extends ItemProducer<MysteryDataContents> {
    public BN6MysteryDataContentsProducer(ChipLibrary library) {
        super(library, new Item.Type[] {
            Item.Type.BATTLECHIP,
            Item.Type.SUBCHIP,
            Item.Type.ZENNY,
            Item.Type.ITEM,
            Item.Type.BUGFRAG,
            Item.Type.BATTLECHIP_TRAP,
            Item.Type.ZENNY_TRAP,
            Item.Type.HP_MEMORY,
            Item.Type.NAVICUST_PROGRAM,
            Item.Type.REGUP,
            Item.Type.SUBMEMORY,
            Item.Type.EXPMEMORY
        });
    }
    
    @Override
    public MysteryDataContents readFromStream(ByteStream stream) {
        byte[] bytes = stream.readBytes(8);
        MysteryDataContents item = new MysteryDataContents(bytes);
        
        Item.Type type = getItemType(ByteConverter.readUInt8(bytes, 0) - 1);
        int probability = ByteConverter.readUInt8(bytes, 1);
        int subValue = ByteConverter.readUInt8(bytes, 3);
        int value = ByteConverter.readInt32(bytes, 4);
        
        setItem(item, type, value, subValue);
        item.setProbability(probability);
        
        return item;
    }

    @Override
    public void writeToStream(ByteStream stream, MysteryDataContents item) {
        byte[] bytes = item.base();
        
        ByteConverter.writeUInt8((short)(getItemTypeIndex(item.type()) + 1), bytes, 0);
        ByteConverter.writeUInt8((short)item.getProbability(), bytes, 1);
        ByteConverter.writeUInt8((short)item.subValue(), bytes, 3);
        ByteConverter.writeInt32(item.value(), bytes, 4);
        
        stream.writeBytes(bytes);
    }
}
