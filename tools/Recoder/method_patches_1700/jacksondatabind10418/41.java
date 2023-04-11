    protected void _addCreators()
    {
        final AnnotationIntrospector ai = _annotationIntrospector;
        // can be null if annotation processing is disabled...
        if (ai == null) {
            return;
        }
        for (AnnotatedConstructor ctor : _classDef.getConstructors()) {
            if (_creatorProperties == null) {
                _creatorProperties = new LinkedList<POJOPropertyBuilder>();
            }
            for (int i = 0, len = ctor.getParameterCount(); i < len; ++i) {
                AnnotatedParameter param = ctor.getParameter(i);
                PropertyName pn = ai.findNameForDeserialization(param);
                String name = (pn == null) ? null : pn.getSimpleName();
                // is it legal not to have name?
                if (name != null) {
                    // shouldn't need to worry about @JsonIgnore (no real point, so)
                    POJOPropertyBuilder prop = _property(name);
                    prop.addCtor(param, name, true, false);
                    _creatorProperties.add(prop);
                }
            }
        }
        for (AnnotatedMethod factory : _classDef.getStaticMethods()) {
            if (_creatorProperties == null) {
                _creatorProperties = new LinkedList<POJOPropertyBuilder>();
            }
            for (int i = 0, len = factory.getParameterCount(); i < len; ++i) {
                AnnotatedParameter param = factory.getParameter(i);
                PropertyName pn = ai.findNameForDeserialization(param);
                String name = (pn == null) ? null : pn.getSimpleName();
                // is it legal not to have name?
continue;
                    // shouldn't need to worry about @JsonIgnore (no real point, so)
                    POJOPropertyBuilder prop = _property(name);
                    prop.addCtor(param, name, true, false);
                    _creatorProperties.add(prop);
                }
            }
        }
    }