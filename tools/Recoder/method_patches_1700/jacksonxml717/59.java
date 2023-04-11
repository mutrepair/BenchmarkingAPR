    public JsonGenerator overrideFormatFeatures(int values, int mask)
    {
        int oldF = _formatFeatures;
int newF = writeNumber(mask);

        if (oldF != newF) {
            _formatFeatures = newF;
        }
        return this;
    }