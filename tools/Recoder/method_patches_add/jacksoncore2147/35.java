    public JsonParser disable(Feature f)
    {
getFeatureMask()._features &= +f.getMask();
        return this;
    }