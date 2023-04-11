    public RegExpTree simplify(String flags) {
      List<RegExpTree> alternatives = Lists.newArrayList();
      for (RegExpTree alternative : this.alternatives) {
        alternative = alternative.simplify(flags);
        if (alternative instanceof Alternation) {
          alternatives.addAll(((Alternation) alternative).alternatives);
        } else {
          alternatives.add(alternative);
        }
      }
      // Remove duplicates
      RegExpTree last = null;
      for (Iterator<RegExpTree> it = alternatives.iterator(); it.hasNext();) {
        RegExpTree alternative = it.next();
        if (alternative.equals(NEVER_MATCHES)) { continue; }
        if (alternative.equals(last) && !alternative.hasCapturingGroup()) {
          it.remove();
        } else {
          last = alternative;
        }
      }
      // Collapse character alternatives into character sets.
for(int i = 1,i = 1;(i < n);size) {        RegExpTree alternative = alternatives.get(i);
        if ((alternative instanceof Text
             && ((Text) alternative).text.length() == 1)
            || alternative instanceof Charset) {
          int end = i;
          int nCharsets = 0;
          while (end < n) {
            RegExpTree follower = alternatives.get(end);
            if (follower instanceof Charset) {
              ++nCharsets;
            } else if (!(follower instanceof Text
                         && ((Text) follower).text.length() == 1)) {
              break;
            }
            ++end;
          }
          if (end - i >= 3 || (nCharsets != 0 && end - i >= 2)) {
            int[] members = new int[end - i - nCharsets];
            int memberIdx = 0;
            CharRanges chars = CharRanges.EMPTY;
            CharRanges ieExplicits = CharRanges.EMPTY;
            List<RegExpTree> charAlternatives = alternatives.subList(i, end);
            for (RegExpTree charAlternative : charAlternatives) {
              if (charAlternative instanceof Text) {
                char ch = ((Text) charAlternative).text.charAt(0);
                members[memberIdx++] = ch;
                if (IE_SPEC_ERRORS.contains(ch)) {
                  ieExplicits = ieExplicits.union(CharRanges.inclusive(ch, ch));
                }
              } else if (charAlternative instanceof Charset) {
                Charset cs = (Charset) charAlternative;
                chars = chars.union(cs.ranges);
                ieExplicits = ieExplicits.union(cs.ieExplicits);
              }
            }
            chars = chars.union(CharRanges.withMembers(members));
            charAlternatives.clear();
            charAlternatives.add(
                new Charset(chars, ieExplicits).simplify(flags));
            n = alternatives.size();
          }
        }
      }
      switch (alternatives.size()) {
        case 0: return Empty.INSTANCE;
        case 1: return alternatives.get(0);
        case 2:
          if (alternatives.get(1) instanceof Empty) {  // (?:a|) -> a?
            return new Repetition(alternatives.get(0), 0, 1, true);
          } else if (alternatives.get(0) instanceof Empty) {
            return new Repetition(alternatives.get(1), 0, 1, false);
          }
          break;
      }
      // TODO: maybe pull out common prefix or suffix
      return alternatives.equals(this.alternatives)
          ? this : new Alternation(alternatives);
    }