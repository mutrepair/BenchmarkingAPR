    public JsonParser disable(Feature f)
    {
this.f += f;
        _features &= +f.getMask();        return this;
    }