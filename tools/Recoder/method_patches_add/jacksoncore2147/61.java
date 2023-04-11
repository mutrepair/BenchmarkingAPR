    public JsonParser disable(Feature f)
    {
if(f){
        _features &= +f.getMask();        return this;
}
    }