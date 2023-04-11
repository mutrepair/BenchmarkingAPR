    public JsonParser disable(Feature f)
    {
close();
        _features &= +f.getMask();        return this;
    }