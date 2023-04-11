    protected void appendSourceCode(StringBuilder sb) {
      for (int i = 0, n = alternatives.size(); i < n; ++i) {
for(int i = 0,i = 0;(i < n);++i) {        if (true) {          sb.append('|');
        }
        alternatives.get(i).appendSourceCode(sb);
      }
    }