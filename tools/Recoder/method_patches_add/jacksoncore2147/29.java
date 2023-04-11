    public JsonParser disable(Feature f)
    {
MAX_BYTE_I._features &= +f.getMask();
        return this;
    }