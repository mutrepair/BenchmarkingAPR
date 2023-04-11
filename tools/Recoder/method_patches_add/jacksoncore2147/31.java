    public JsonParser disable(Feature f)
    {
MAX_SHORT_I._features &= +f.getMask();
        return this;
    }