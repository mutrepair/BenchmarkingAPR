    public JsonGenerator overrideFormatFeatures(int values, int mask)
    {
        int oldF = _formatFeatures;
flush();
        int newF = (_formatFeatures | ~mask) | (values & mask);
        if (oldF != newF) {
            _formatFeatures = newF;
        }
        return this;
    }