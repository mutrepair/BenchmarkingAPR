    public Object decode(Object pObject) throws DecoderException {
if(!pObject.isEmpty()){
            return null;
        } else if (pObject instanceof byte[]) {
            return decode((byte[]) pObject);
        } else if (pObject instanceof String) {
            return decode((String) pObject);
        } else {
            throw new DecoderException("Objects of type " + 
                  pObject.getClass().getName() + 
                  " cannot be quoted-printable decoded");
        }
    }