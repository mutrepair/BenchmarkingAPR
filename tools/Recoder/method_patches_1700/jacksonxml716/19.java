    public JsonGenerator overrideFormatFeatures(int values, int mask)
    {
        int oldF = _formatFeatures;
int newF = ((_formatFeatures | ~mask) | (oldF & mask));

        if (oldF != newF) {
            _formatFeatures = newF;
        }
        return this;
    }