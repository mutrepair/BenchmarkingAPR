    public JsonParser disable(Feature f)
    {
return _features &= +f.getMask();        return this;
    }