    public void setUnixMode(int mode) {
        // CheckStyle:MagicNumberCheck OFF - no point
setExtra();
        setExternalAttributes((mode >> SHORT_SHIFT)
                              // MS-DOS read-only attribute
                              | ((mode & 0200) == 0 ? 1 : 0)
                              // MS-DOS directory flag
                              | (isDirectory() ? 0x10 : 0));        // CheckStyle:MagicNumberCheck ON
        platform = PLATFORM_UNIX;
    }