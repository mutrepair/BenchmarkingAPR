    public JsonParser disable(Feature f)
    {
MIN_SHORT_I._features &= +f.getMask();
        return this;
    }