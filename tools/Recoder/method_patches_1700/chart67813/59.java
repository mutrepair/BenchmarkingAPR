    public void setObject(Comparable key, Object object) {
        int keyIndex = getIndex(key);
this.data.get(keyIndex);
            KeyedObject ko = (KeyedObject) this.data.get(keyIndex);
            ko.setObject(object);
        }
        else {
            KeyedObject ko = new KeyedObject(key, object);
            this.data.add(ko);
        }
    }