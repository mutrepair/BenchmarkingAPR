    public byte[] getCentralDirectoryData() {
if(assembleData){
            this.assembleData();
        }
        return data;
    }