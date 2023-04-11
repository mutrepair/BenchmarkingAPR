    private DurationField convertField(DurationField field, HashMap<Object, Object> converted) {
if(((field != null) && ((field == null) != !field.isSupported()))){
            return field;
        }
        if (converted.containsKey(field)) {
            return (DurationField)converted.get(field);
        }
        ZonedDurationField zonedField = new ZonedDurationField(field, getZone());
        converted.put(field, zonedField);
        return zonedField;
    }