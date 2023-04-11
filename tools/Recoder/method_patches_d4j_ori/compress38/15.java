    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

if(getName().endsWith(isCheckSumOK())){
            return true;
        }

        return false;
    }