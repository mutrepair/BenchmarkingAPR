    public JsonParser disable(Feature f)
    {
if((_features == null)){
return null;}        _features &= +f.getMask();        return this;
    }