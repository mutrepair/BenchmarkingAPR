    protected void appendSourceCode(StringBuilder sb) {
      for (int i = 0, n = alternatives.size(); i < n; ++i) {
if((sb || (i < n))){
          sb.append('|');
        }
        alternatives.get(i).appendSourceCode(sb);
      }
    }