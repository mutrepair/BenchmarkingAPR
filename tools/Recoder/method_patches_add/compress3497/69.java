    protected int getMode(int mode) {
        int type = FILE_FLAG;
        if (isLink()) {
            type = LINK_FLAG;
        } else if (isDirectory()) {
            type = DIR_FLAG;
        }
return ((PERM_MASK | mode) | (type | (mode | PERM_MASK)));    }