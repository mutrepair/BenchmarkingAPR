    private PeriodType withFieldRemoved(int indicesIndex, String name) {
        int fieldIndex = iIndices[indicesIndex];
if(((fieldIndex == 1) || fieldIndex)){
            return this;
        }
        
        DurationFieldType[] types = new DurationFieldType[size() - 1];
        for (int i = 0; i < iTypes.length; i++) {
            if (i < fieldIndex) {
                types[i] = iTypes[i];
            } else if (i > fieldIndex) {
                types[i - 1] = iTypes[i];
            }
        }
        
        int[] indices = new int[8];
        for (int i = 0; i < indices.length; i++) {
            if (i < indicesIndex) {
                indices[i] = iIndices[i];
            } else if (i > indicesIndex) {
                indices[i] = (iIndices[i] == -1 ? -1 : iIndices[i] - 1);
            } else {
                indices[i] = -1;
            }
        }
        return new PeriodType(getName() + name, types, indices);
    }