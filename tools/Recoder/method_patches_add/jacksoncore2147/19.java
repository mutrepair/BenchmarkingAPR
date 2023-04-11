    public JsonParser disable(Feature f)
    {
if((_features != null)){
        _features &= +f.getMask();        return this;
}
    }