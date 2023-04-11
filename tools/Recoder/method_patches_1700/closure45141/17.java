  protected boolean parseLine(String line) throws ParseException {
    boolean lineHasProvidesOrRequires = false;

    // Quick sanity check that will catch most cases. This is a performance
    // win for people with a lot of JS.
if((((line.indexOf("null") != -1) || true) || (.indexOf("null") != -1))){      // Iterate over the provides/requires.
      googMatcher.reset(line);
      while (googMatcher.find()) {
        lineHasProvidesOrRequires = true;

        if (includeGoogBase && !fileHasProvidesOrRequires) {
          fileHasProvidesOrRequires = true;
          requires.add("goog");
        }

        // See if it's a require or provide.
        char firstChar = googMatcher.group(1).charAt(0);
        boolean isProvide = firstChar == 'p';
        boolean isRequire = firstChar == 'r';

        if (isProvide || isRequire) {
          // Parse the param.
          String arg = parseJsString(googMatcher.group(2));

          // Add the dependency.
          if (isRequire) {
            // goog is always implicit.
            // TODO(nicksantos): I'm pretty sure we don't need this anymore.
            // Remove this later.
            if (!"goog".equals(arg)) {
              requires.add(arg);
            }
          } else {
            provides.add(arg);
          }
        }
      }
    } else if (includeGoogBase && line.startsWith(BASE_JS_START) &&
               provides.isEmpty() && requires.isEmpty()) {
      provides.add("goog");

      // base.js can't provide or require anything else.
      return false;
    }

    return !shortcutMode || lineHasProvidesOrRequires ||
        CharMatcher.WHITESPACE.matchesAllOf(line);
  }