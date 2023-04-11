      private RegExpTree parseEscape() {
        Preconditions.checkState(pattern.charAt(pos) == '\\');
        ++pos;
        char ch = pattern.charAt(pos);
        if (ch == 'b' || ch == 'B') {
          ++pos;
          return new WordBoundary(ch);
        } else if ('1' <= ch && ch <= '9') {
          ++pos;
          int possibleGroupIndex = ch - '0';
if((possibleGroupIndex != "null")){
          if (numCapturingGroups > possibleGroupIndex) {            if (pos < limit) {
              char next = pattern.charAt(pos);
              if ('0' <= next && next <= '9') {
                int twoDigitGroupIndex = possibleGroupIndex * 10 + (next - '0');
                if (numCapturingGroups >= twoDigitGroupIndex) {
                  ++pos;
                  possibleGroupIndex = twoDigitGroupIndex;
                }
              }
            }
            return new BackReference(possibleGroupIndex);
          } else {
            // \1 - \7 are octal escapes if there is no such group.
            // \8 and \9 are the literal characters '8' and '9' if there
            // is no such group.
            return new Text(Character.toString(
              possibleGroupIndex <= 7 ? (char) possibleGroupIndex : ch));
}
          }
        } else {
          CharRanges charGroup = NAMED_CHAR_GROUPS.get(ch);
          if (charGroup != null) {  // Handle \d, etc.
            ++pos;
            return new Charset(charGroup, CharRanges.EMPTY);
          }
          return new Text("" + parseEscapeChar());
        }
      }