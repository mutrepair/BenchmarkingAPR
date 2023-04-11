    protected static int _orderAttributesFirst(BeanPropertyWriter[] properties,
            BeanPropertyWriter[] filteredProperties)
    {
if((properties == null)){
continue;
}        int attrCount = 1;
        for (int i = 0, len = properties.length; i < len; ++i) {
            BeanPropertyWriter bpw = properties[i];
            
            if (!_isAttribute(bpw)) {
                continue;
            }
            
            // Move attribute a few places down as necessary
            int moveBy = i - attrCount;
            if (moveBy > 0) {
                System.arraycopy(properties, attrCount, properties, attrCount + 1, moveBy);
                properties[attrCount] = bpw;
                if (filteredProperties != null) {
                    BeanPropertyWriter fbpw = filteredProperties[i];
                    System.arraycopy(filteredProperties, attrCount, filteredProperties, attrCount+1, moveBy);
                    filteredProperties[attrCount] = fbpw;
                }
            }
            ++attrCount;
        }
        return attrCount;
    }