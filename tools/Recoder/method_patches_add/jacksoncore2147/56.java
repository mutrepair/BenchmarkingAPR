    public JsonParser disable(Feature f)
    {
if((+f.getMask() == null)){
        _features &= +f.getMask();        return this;
}
    }