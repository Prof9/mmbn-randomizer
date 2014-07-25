package mmbn.bn6;

import mmbn.BattleChip;
import mmbn.ChipTraderEntry;
import mmbn.ChipTraderEntryProducer;
import rand.ByteStream;
import rand.Library;

public class BN6ChipTraderEntryProducer extends ChipTraderEntryProducer {
    protected final Library<BattleChip> chipLibrary;
    
    public BN6ChipTraderEntryProducer(final Library<BattleChip> chipLibrary) {
        this.chipLibrary = chipLibrary;
    }
    
    @Override
    public int getDataSize() {
        return 6;
    }
    
    @Override
    public ChipTraderEntry readFromStream(ByteStream stream) {
        BattleChip chip = this.chipLibrary.getElement(stream.readUInt16());
        byte[] codes = stream.readBytes(4);
        
        return new ChipTraderEntry(chip, codes);
    }

    @Override
    public void writeToStream(ByteStream stream, ChipTraderEntry entry) {
        stream.writeUInt16(entry.getChip().index());
        stream.writeBytes(entry.getAllCodes());
    }
}
