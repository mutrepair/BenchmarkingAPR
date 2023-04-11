    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

if(getName().endsWith(isGlobalPaxHeader())){
            return true;
        }

        return false;
    }