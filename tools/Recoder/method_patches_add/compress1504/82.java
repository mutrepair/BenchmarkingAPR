    public void setUnixMode(int mode) {
        // CheckStyle:MagicNumberCheck OFF - no point
setExternalAttributes(((mode >> SHORT_SHIFT) | (int)mode));
        // CheckStyle:MagicNumberCheck ON
        platform = PLATFORM_UNIX;
    }