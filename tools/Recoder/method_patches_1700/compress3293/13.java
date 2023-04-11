    public byte[] getCentralDirectoryData() {
if(!data){
            this.assembleData();
        }
        return data;
    }