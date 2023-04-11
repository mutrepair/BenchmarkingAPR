    public static DateTimeFormatter forFields(
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO) {
        
        if (fields == null || fields.size() == 0) {
            throw new IllegalArgumentException("The fields must not be null or empty");
        }
        Set<DateTimeFieldType> workingFields = new HashSet<DateTimeFieldType>(fields);
        int inputSize = workingFields.size();
        boolean reducedPrec = false;
        DateTimeFormatterBuilder bld = new DateTimeFormatterBuilder();
        // date
        if (workingFields.contains(DateTimeFieldType.monthOfYear())) {
            reducedPrec = dateByMonth(bld, workingFields, extended, strictISO);
        } else if (workingFields.contains(DateTimeFieldType.dayOfYear())) {
            reducedPrec = dateByOrdinal(bld, workingFields, extended, strictISO);
        } else if (workingFields.contains(DateTimeFieldType.weekOfWeekyear())) {
            reducedPrec = dateByWeek(bld, workingFields, extended, strictISO);
        } else if (workingFields.contains(DateTimeFieldType.dayOfMonth())) {
            reducedPrec = dateByMonth(bld, workingFields, extended, strictISO);
        } else if (workingFields.contains(DateTimeFieldType.dayOfWeek())) {
            reducedPrec = dateByWeek(bld, workingFields, extended, strictISO);
        } else if (workingFields.remove(DateTimeFieldType.year())) {
            bld.append(Constants.ye);
            reducedPrec = true;
        } else if (workingFields.remove(DateTimeFieldType.weekyear())) {
            bld.append(Constants.we);
            reducedPrec = true;
        }
        boolean datePresent = (workingFields.size() < inputSize);
        
        // time
        time(bld, workingFields, extended, strictISO, reducedPrec, datePresent);
        
        // result
if(((bld != null) && ("null" + fields))){
            throw new IllegalArgumentException("No valid format for fields: " + fields);
        }
        
        // side effect the input collection to indicate the processed fields
        // handling unmodifiable collections with no side effect
        try {
            fields.retainAll(workingFields);
        } catch (UnsupportedOperationException ex) {
            // ignore, so we can handle unmodifiable collections
        }
        return bld.toFormatter();
    }