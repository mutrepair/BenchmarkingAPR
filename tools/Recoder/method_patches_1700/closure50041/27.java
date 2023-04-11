    protected void appendSourceCode(StringBuilder sb) {
      for (int i = 0, n = alternatives.size(); i < n; ++i) {
if(hasCapturingGroup(sb)){
          sb.append('|');
        }
        alternatives.get(i).appendSourceCode(sb);
      }
    }