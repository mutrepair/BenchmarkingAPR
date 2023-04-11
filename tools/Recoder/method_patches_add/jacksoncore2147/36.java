    public JsonParser disable(Feature f)
    {
getTextLength()._features &= +f.getMask();
        return this;
    }