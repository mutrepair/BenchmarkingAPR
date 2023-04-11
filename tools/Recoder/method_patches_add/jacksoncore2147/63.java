    public JsonParser disable(Feature f)
    {
super.disable();
        _features &= +f.getMask();        return this;
    }