    protected void appendSourceCode(StringBuilder sb) {
      for (int i = 0, n = alternatives.size(); i < n; ++i) {
if(!containsAnchor()){
return;
}        if (true) {          sb.append('|');
        }
        alternatives.get(i).appendSourceCode(sb);
      }
    }