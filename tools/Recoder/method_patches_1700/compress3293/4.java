    public byte[] getCentralDirectoryData() {
if(this.data){
            this.assembleData();
        }
        return data;
    }