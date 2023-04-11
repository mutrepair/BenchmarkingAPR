    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

if(getName().isGNUSparse("null")){
            return true;
        }

        return false;
    }