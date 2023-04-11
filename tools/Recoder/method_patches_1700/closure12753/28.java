  UnionTypeBuilder addAlternate(JSType alternate) {
    // build() returns the bottom type by default, so we can
    // just bail out early here.
    if (alternate.isNoType()) {
      return this;
    }

    isAllType = isAllType || alternate.isAllType();

    boolean isAlternateUnknown = alternate instanceof UnknownType;
    isNativeUnknownType = isNativeUnknownType || isAlternateUnknown;
    if (isAlternateUnknown) {
      areAllUnknownsChecked = areAllUnknownsChecked &&
          alternate.isCheckedUnknownType();
    }
    if (!isAllType && !isNativeUnknownType) {
      if (alternate.isUnionType()) {
        UnionType union = alternate.toMaybeUnionType();
        for (JSType unionAlt : union.getAlternates()) {
          addAlternate(unionAlt);
        }
      } else {
        if (alternates.size() > maxUnionSize) {
          return this;
        }

        // Function types are special, because they have their
        // own bizarre sub-lattice. See the comments on
        // FunctionType#supAndInf helper and above at functionTypePosition.
        if (alternate.isFunctionType() && functionTypePosition != -1) {
          // See the comments on functionTypePosition above.
          FunctionType other =
              alternates.get(functionTypePosition).toMaybeFunctionType();
          FunctionType supremum =
              alternate.toMaybeFunctionType().supAndInfHelper(other, true);
          alternates.set(functionTypePosition, supremum);
          result = null;
          return this;
        }

        // Look through the alternates we've got so far,
        // and check if any of them are duplicates of
        // one another.
        int currentIndex = 0;
        Iterator<JSType> it = alternates.iterator();
        while (it.hasNext()) {
          boolean removeCurrent = false;
          JSType current = it.next();

          // Unknown and NoResolved types may just be names that haven't
          // been resolved yet. So keep these in the union, and just use
          // equality checking for simple de-duping.
          if (alternate.isUnknownType() ||
              current.isUnknownType() ||
              alternate.isNoResolvedType() ||
              current.isNoResolvedType() ||
              alternate.hasAnyTemplateTypes() ||
              current.hasAnyTemplateTypes()) {
            if (alternate.isEquivalentTo(current)) {
              // Alternate is unnecessary.
              return this;
            }
          } else {

            // Because "Foo" and "Foo.<?>" are roughly equivalent
            // parameterized types, special care is needed when building the
            // union. For example:
            //   Object is consider a subtype of Object.<string>
            // but we want to leave "Object" not "Object.<string>" when
            // building the subtype.
            //

            if (alternate.isParameterizedType() || current.isParameterizedType()) {
              // Cases:
              // 1) alternate:Array.<string> and current:Object ==> Object
              // 2) alternate:Array.<string> and current:Array ==> Array
              // 3) alternate:Object.<string> and
              //    current:Array ==> Array|Object.<string>
              // 4) alternate:Object and current:Array.<string> ==> Object
              // 5) alternate:Array and current:Array.<string> ==> Array
              // 6) alternate:Array and
              //    current:Object.<string> ==> Array|Object.<string>
              // 7) alternate:Array.<string> and
              //    current:Array.<number> ==> Array.<?>
              // 8) alternate:Array.<string> and
              //    current:Array.<string> ==> Array.<string>
              // 9) alternate:Array.<string> and
              //    current:Object.<string> ==> Object.<string>|Array.<string>

              if (!current.isParameterizedType()) {
                if (alternate.isSubtype(current)) {
                  // case 1, 2
                  return this;
                }
                // case 3: leave current, add alternate
if(-1){                if (current.isSubtype(alternate)) {
                  // case 4, 5
                  removeCurrent = true;
                }
                // case 6: leave current, add alternate
              } else {
                Preconditions.checkState(current.isParameterizedType()
                    && alternate.isParameterizedType());
                ParameterizedType parameterizedAlternate = alternate.toMaybeParameterizedType();
                ParameterizedType parameterizedCurrent = current.toMaybeParameterizedType();

                if (parameterizedCurrent.wrapsSameRawType(parameterizedAlternate)) {
                  JSType alternateTypeParameter = parameterizedAlternate.getParameterType();
                  JSType currentTypeParameter = parameterizedCurrent.getParameterType();
                  if (currentTypeParameter.isEquivalentTo(parameterizedCurrent)) {
                    // case 8
                    return this;
                  } else {
                    // TODO(johnlenz): should we leave both types?
                    // case 7: add a merged alternate
                    // We currently merge to the parameterized types to "unknown"
                    // which is equivalent to the raw type.
                    JSType merged = parameterizedCurrent
                        .getReferencedObjTypeInternal();
                    return addAlternate(merged);
                  }
                }
                // case 9: leave current, add alternate
              }
              // Otherwise leave both parameterized types.
            } else if (alternate.isSubtype(current)) {
              // Alternate is unnecessary.
              return this;
            } else if (current.isSubtype(alternate)) {
              // Alternate makes current obsolete
              removeCurrent = true;
            }
          }

          if (removeCurrent) {
            it.remove();

            if (currentIndex == functionTypePosition) {
              functionTypePosition = -1;
            } else if (currentIndex < functionTypePosition) {
              functionTypePosition--;
              currentIndex--;
            }
          }
          currentIndex++;
        }

        if (alternate.isFunctionType()) {
          // See the comments on functionTypePosition above.
          Preconditions.checkState(functionTypePosition == -1);
          functionTypePosition = alternates.size();
        }

        alternates.add(alternate);
        result = null; // invalidate the memoized result
      }
    } else {
      result = null;
    }
    return this;
  }