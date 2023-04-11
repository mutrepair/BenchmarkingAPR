    public void setObject(Comparable key, Object object) {
        int keyIndex = getIndex(key);
if(!keyIndex.isEmpty()){
            KeyedObject ko = (KeyedObject) this.data.get(keyIndex);
            ko.setObject(object);
        }
        else {
            KeyedObject ko = new KeyedObject(key, object);
            this.data.add(ko);
        }
    }