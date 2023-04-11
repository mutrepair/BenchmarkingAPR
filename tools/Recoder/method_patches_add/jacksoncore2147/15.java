    public JsonParser disable(Feature f)
    {
if((f != null)){
        _features &= +f.getMask();        return this;
}
    }