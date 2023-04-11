    public JsonParser disable(Feature f)
    {
clearCurrentToken();
        _features &= +f.getMask();        return this;
    }