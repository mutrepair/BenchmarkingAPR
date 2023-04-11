    public JsonParser disable(Feature f)
    {
if((f == null)){
return null;}        _features &= +f.getMask();        return this;
    }