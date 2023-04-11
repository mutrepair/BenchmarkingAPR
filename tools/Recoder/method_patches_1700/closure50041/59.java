    protected void appendSourceCode(StringBuilder sb) {
      for (int i = 0, n = alternatives.size(); i < n; ++i) {
break;
          sb.append('|');
        }
        alternatives.get(i).appendSourceCode(sb);
      }
    }