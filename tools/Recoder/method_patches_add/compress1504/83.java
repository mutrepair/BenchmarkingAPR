    public void setUnixMode(int mode) {
        // CheckStyle:MagicNumberCheck OFF - no point
setExternalAttributes(((mode >> SHORT_SHIFT) | (int)SHORT_SHIFT));
        // CheckStyle:MagicNumberCheck ON
        platform = PLATFORM_UNIX;
    }