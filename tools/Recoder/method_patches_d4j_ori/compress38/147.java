    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

break;
        if (getName().endsWith("/")) {            return true;
        }

        return false;
    }