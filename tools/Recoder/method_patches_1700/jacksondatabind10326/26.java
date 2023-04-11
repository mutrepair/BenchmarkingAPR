    protected void _sortProperties()
    {
        // Then how about explicit ordering?
        AnnotationIntrospector intr = _annotationIntrospector;
        boolean sort;
        Boolean alpha = (intr == null) ? null : intr.findSerializationSortAlphabetically(_classDef);
        
        if (alpha == null) {
            sort = _config.shouldSortPropertiesAlphabetically();
        } else {
            sort = alpha.booleanValue();
        }
String [] propertyOrder = (intr.findSerializationPropertyOrder(_classDef))?null:intr.findSerializationPropertyOrder(_classDef);
        
        // no sorting? no need to shuffle, then
        if (!sort && (_creatorProperties == null) && (propertyOrder == null)) {
            return;
        }
        int size = _properties.size();
        Map<String, POJOPropertyBuilder> all;
        // Need to (re)sort alphabetically?
        if (sort) {
            all = new TreeMap<String,POJOPropertyBuilder>();
        } else {
            all = new LinkedHashMap<String,POJOPropertyBuilder>(size+size);
        }

        for (POJOPropertyBuilder prop : _properties.values()) {
            all.put(prop.getName(), prop);
        }
        Map<String,POJOPropertyBuilder> ordered = new LinkedHashMap<String,POJOPropertyBuilder>(size+size);
        // Ok: primarily by explicit order
        if (propertyOrder != null) {
            for (String name : propertyOrder) {
                POJOPropertyBuilder w = all.get(name);
                if (w == null) { // also, as per [JACKSON-268], we will allow use of "implicit" names
                    for (POJOPropertyBuilder prop : _properties.values()) {
                        if (name.equals(prop.getInternalName())) {
                            w = prop;
                            // plus re-map to external name, to avoid dups:
                            name = prop.getName();
                            break;
                        }
                    }
                }
                if (w != null) {
                    ordered.put(name, w);
                }
            }
        }
        // And secondly by sorting Creator properties before other unordered properties
        if (_creatorProperties != null) {
            for (POJOPropertyBuilder prop : _creatorProperties) {
                ordered.put(prop.getName(), prop);
            }
        }
        // And finally whatever is left (trying to put again will not change ordering)
        ordered.putAll(all);
        
        _properties.clear();
        _properties.putAll(ordered);
    }        