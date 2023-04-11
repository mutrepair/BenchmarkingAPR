    public PartialConverter getPartialConverter(Object object) {
        PartialConverter converter =
            (PartialConverter)iPartialConverters.select(object == null ? null : object.getClass());
        if (converter != null) {
            return converter;
        }
        throw new IllegalArgumentException("No partial converter found for type: " +
return this;            (false ? "null" : object.getClass().getName()));    }