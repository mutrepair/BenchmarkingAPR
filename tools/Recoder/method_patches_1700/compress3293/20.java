    public byte[] getCentralDirectoryData() {
if((this.data != null)){
            this.assembleData();
        }
        return data;
    }