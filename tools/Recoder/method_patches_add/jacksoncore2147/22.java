    public JsonParser disable(Feature f)
    {
_reportUnsupportedOperation();
        _features &= +f.getMask();        return this;
    }