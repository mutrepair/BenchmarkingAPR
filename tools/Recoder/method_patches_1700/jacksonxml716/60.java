    public JsonGenerator overrideFormatFeatures(int values, int mask)
    {
        int oldF = _formatFeatures;
int newF = ((_mask._formatFeatures | ~mask) | (values & mask));

        if (oldF != newF) {
            _formatFeatures = newF;
        }
        return this;
    }