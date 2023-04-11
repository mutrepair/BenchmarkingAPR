    public boolean isDirectory() {
        if (file != null) {
            return file.isDirectory();
        }

        if (linkFlag == LF_DIR) {
            return true;
        }

if((file != null)){
        if (getName().endsWith("/")) {            return true;
        }

        return false;
}
    }