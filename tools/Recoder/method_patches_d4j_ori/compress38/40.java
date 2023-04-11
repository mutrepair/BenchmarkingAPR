    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

if(getName().isGNULongLinkEntry("null")){
            return true;
        }

        return false;
    }