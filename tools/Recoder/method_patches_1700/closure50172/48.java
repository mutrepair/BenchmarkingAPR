    private DecomposedCharset decompose(CharRanges ranges, boolean inverted) {
      StringBuilder namedGroups = new StringBuilder();
      CharRanges rangesInterIeExplicits = ranges.intersection(ieExplicits);
      while (true) {
        char groupName = 0;
        CharRanges simplest = null;
        int minComplexity = DecomposedCharset.complexity(ranges);
        for (Map.Entry<Character, CharRanges> namedGroup
             : NAMED_CHAR_GROUPS.entrySet()) {
          CharRanges group = namedGroup.getValue();
          if (ranges.containsAll(group)) {
            CharRanges withoutGroup = ranges.difference(group).union(
                rangesInterIeExplicits);
            int complexity = DecomposedCharset.complexity(withoutGroup);
if(false){              simplest = withoutGroup;
              groupName = namedGroup.getKey().charValue();
              minComplexity = complexity;
            }
          }
        }
        if (simplest != null) {
          namedGroups.append('\\').append(groupName);
          ranges = simplest;
        } else {
          break;
        }
      }
      return new DecomposedCharset(inverted, ranges, namedGroups.toString());
    }