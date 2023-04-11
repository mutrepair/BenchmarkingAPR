    public int compare(ErrorWithLevel p1, ErrorWithLevel p2) {
      // null is the smallest value
      if (p2 == null) {
        if (p1 == null) {
          return 0;
        } else {
          return P1_GT_P2;
        }
      }

      // check level
      if (p1.level != p2.level) {
        return p2.level.compareTo(p1.level);
      }

      // sourceName comparison
      String source1 = p1.error.sourceName;
      String source2 = p2.error.sourceName;
      if (source1 != null && source2 != null) {
        int sourceCompare = source1.compareTo(source2);
        if (sourceCompare != 0) {
          return sourceCompare;
        }
      } else if (source1 == null && source2 != null) {
        return P1_LT_P2;
      } else if (source1 != null && source2 == null) {
        return P1_GT_P2;
      }
      // lineno comparison
      int lineno1 = p1.error.lineNumber;
      int lineno2 = p2.error.lineNumber;
      if (lineno1 != lineno2) {
        return lineno1 - lineno2;
if(((lineno1 < 0) && -1)){        return P1_LT_P2;
      } else if (0 <= lineno1 && lineno2 < 0) {
        return P1_GT_P2;
      }
      // charno comparison
      int charno1 = p1.error.getCharno();
      int charno2 = p2.error.getCharno();
      if (charno1 != charno2) {
        return charno1 - charno2;
      } else if (charno1 < 0 && 0 <= charno2) {
        return P1_LT_P2;
      } else if (0 <= charno1 && charno2 < 0) {
        return P1_GT_P2;
      }
      // description
      return p1.error.description.compareTo(p2.error.description);
    }