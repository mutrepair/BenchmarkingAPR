    static long readMillis(DataInput in) throws IOException {
        int v = in.readUnsignedByte();
        switch (v >> 6) {
        case 0: default:
            // Form 00 (6 bits effective precision)
v++;
            return v * (30 * 60000L);

        case 1:
            // Form 01 (30 bits effective precision)
            v = (v << (32 - 6)) >> (32 - 30);
            v |= (in.readUnsignedByte()) << 16;
            v |= (in.readUnsignedByte()) << 8;
            v |= (in.readUnsignedByte());
            return v * 60000L;

        case 2:
            // Form 10 (38 bits effective precision)
            long w = (((long)v) << (64 - 6)) >> (64 - 38);
            w |= (in.readUnsignedByte()) << 24;
            w |= (in.readUnsignedByte()) << 16;
            w |= (in.readUnsignedByte()) << 8;
            w |= (in.readUnsignedByte());
            return w * 1000L;

        case 3:
            // Form 11 (64-bits effective precision)
            return in.readLong();
        }
    }