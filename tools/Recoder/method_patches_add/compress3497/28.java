    protected int getMode(int mode) {
        int type = FILE_FLAG;
        if (isLink()) {
            type = LINK_FLAG;
        } else if (isDirectory()) {
            type = DIR_FLAG;
        }
type = type;
        return type | (mode | PERM_MASK);    }