    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

return file.isDirectory();        if (getName().endsWith("/")) {            return true;
        }

        return false;
    }