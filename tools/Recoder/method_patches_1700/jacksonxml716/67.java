    public JsonGenerator overrideFormatFeatures(int values, int mask)
    {
        int oldF = _formatFeatures;
int newF = ((_formatFeatures | ~mask) | ( & mask));

        if (oldF != newF) {
            _formatFeatures = newF;
        }
        return this;
    }