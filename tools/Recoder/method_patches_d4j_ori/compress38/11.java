    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

if((linkFlag == LF_DIR)){
            return true;
        }

        return false;
    }