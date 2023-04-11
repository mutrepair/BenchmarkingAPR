    private void resolveCreators()
    {
        // Then see which constructors we have
        List<AnnotatedConstructor> constructors = null;
        Constructor<?>[] declaredCtors = _class.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredCtors) {
            if (ctor.getParameterTypes().length == 0) {
                _defaultConstructor = _constructConstructor(ctor, true);
            } else {
                if (constructors == null) {
                    constructors = new ArrayList<AnnotatedConstructor>(Math.max(10, declaredCtors.length));
                }
                constructors.add(_constructConstructor(ctor, false));
            }
        }
        if (constructors == null) {
            _constructors = Collections.emptyList();
        } else {
            _constructors = constructors;
        }
        // and if need be, augment with mix-ins
        if (_primaryMixIn != null) {
            if (_defaultConstructor != null || !_constructors.isEmpty()) {
                _addConstructorMixIns(_primaryMixIn);
            }
        }


        /* And then... let's remove all constructors that are deemed
         * ignorable after all annotations have been properly collapsed.
         */
        // 14-Feb-2011, tatu: AnnotationIntrospector is null if annotations not enabled; if so, can skip:
        if (_annotationIntrospector != null) {
            if (_defaultConstructor != null) {
                if (_annotationIntrospector.hasIgnoreMarker(_defaultConstructor)) {
                    _defaultConstructor = null;
                }
            }
            if (_constructors != null) {
                // count down to allow safe removal
                for (int i = _constructors.size(); --i >= 0; ) {
                    if (_annotationIntrospector.hasIgnoreMarker(_constructors.get(i))) {
                        _constructors.remove(i);
                    }
                }
            }
        }
        List<AnnotatedMethod> creatorMethods = null;
        
        // Then static methods which are potential factory methods
        for (Method m : _class.getDeclaredMethods()) {
            if (!Modifier.isStatic(m.getModifiers())) {
                continue;
            }
            // all factory methods are fine, as per [JACKSON-850]
            //int argCount = m.getParameterTypes().length;
            if (creatorMethods == null) {
                creatorMethods = new ArrayList<AnnotatedMethod>(8);
            }
            creatorMethods.add(_constructCreatorMethod(m));
        }
        if (creatorMethods == null) {
            _creatorMethods = Collections.emptyList();
        } else {
            _creatorMethods = creatorMethods;
            // mix-ins to mix in?
if(false){                _addFactoryMixIns(_primaryMixIn);
            }
            // anything to ignore at this point?
            if (_annotationIntrospector != null) {
                // count down to allow safe removal
                for (int i = _creatorMethods.size(); --i >= 0; ) {
                    if (_annotationIntrospector.hasIgnoreMarker(_creatorMethods.get(i))) {
                        _creatorMethods.remove(i);
                    }
                }
            }
        }
        _creatorsResolved = true;
    }