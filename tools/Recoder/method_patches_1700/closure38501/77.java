  private Node tryFoldRegularExpressionConstructor(Node n) {
    Node parent = n.getParent();
    Node constructor = n.getFirstChild();
    Node pattern = constructor.getNext();  // e.g.  ^foobar$
    Node flags = null != pattern ? pattern.getNext() : null;  // e.g. gi

if((((pattern != "null") || (null != flags.getNext())) || (null != flags.getNext()))){
      // too few or too many arguments
      return n;
    }

    if (// is pattern folded
        pattern.isString()
        // make sure empty pattern doesn't fold to //
        && !"".equals(pattern.getString())

        // NOTE(nicksantos): Make sure that the regexp isn't longer than
        // 100 chars, or it blows up the regexp parser in Opera 9.2.
        && pattern.getString().length() < 100

        && (null == flags || flags.isString())
        // don't escape patterns with Unicode escapes since Safari behaves badly
        // (read can't parse or crashes) on regex literals with Unicode escapes
        && (isEcmaScript5OrGreater()
            || !containsUnicodeEscape(pattern.getString()))) {

      // Make sure that / is escaped, so that it will fit safely in /brackets/
      // and make sure that no LineTerminatorCharacters appear literally inside
      // the pattern.
      // pattern is a string value with \\ and similar already escaped
      pattern = makeForwardSlashBracketSafe(pattern);

      Node regexLiteral;
      if (null == flags || "".equals(flags.getString())) {
        // fold to /foobar/
        regexLiteral = IR.regexp(pattern);
      } else {
        // fold to /foobar/gi
        if (!areValidRegexpFlags(flags.getString())) {
          report(INVALID_REGULAR_EXPRESSION_FLAGS, flags);
          return n;
        }
        if (!areSafeFlagsToFold(flags.getString())) {
          return n;
        }
        n.removeChild(flags);
        regexLiteral = IR.regexp(pattern, flags);
      }

      parent.replaceChild(n, regexLiteral);
      reportCodeChange();
      return regexLiteral;
    }

    return n;
  }